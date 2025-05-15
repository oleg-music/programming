package git.olegmusic.client;

import git.olegmusic.client.meta.ClientCommandInfo;
import git.olegmusic.client.util.PersonCreation;
import git.olegmusic.common.CommandRequest;
import git.olegmusic.common.CommandResponse;
import git.olegmusic.common.Person;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 5555;


    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT))) {
            socketChannel.configureBlocking(true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Клиент (NIO) запущен. Введите команды:");
            Map<String, ClientCommandInfo> commandInfo = new HashMap<>();
            commandInfo.put("add", new ClientCommandInfo(true));
            commandInfo.put("show", new ClientCommandInfo(false));
            commandInfo.put("info", new ClientCommandInfo(false));

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) break;

                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String arg = parts.length > 1 ? parts[1] : null;

                ClientCommandInfo meta = commandInfo.get(command);
                if (meta == null) {
                    System.out.println("Неизвестная команда.");
                    continue;
                }

                Person person = null;
                if (meta.requiresPerson()) {
                    person = PersonCreation.createPerson();
                    if (person == null) continue;
                }

                CommandRequest request = new CommandRequest(command, arg, person);

                // Сериализация объекта
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(request);
                oos.flush();
                byte[] requestBytes = baos.toByteArray();
                socketChannel.write(ByteBuffer.wrap(requestBytes));

                // Буфер для ответа
                ByteBuffer responseBuffer = ByteBuffer.allocate(8192);
                socketChannel.read(responseBuffer);
                responseBuffer.flip();

                ByteArrayInputStream bais = new ByteArrayInputStream(responseBuffer.array(), 0, responseBuffer.limit());
                ObjectInputStream ois = new ObjectInputStream(bais);
                CommandResponse response = (CommandResponse) ois.readObject();

                System.out.println(response.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

