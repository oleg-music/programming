package git.olegmusic.server;

import git.olegmusic.common.CommandRequest;
import git.olegmusic.common.CommandResponse;
import git.olegmusic.server.core.Invoker;
import git.olegmusic.server.xml.DOMReader;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Main {
    private static final int PORT = 5555;

    public static void main(String[] args) {
        try {
            // Загрузка коллекции из XML-файла
            String xml = Files.readString(Path.of("server/src/main/resources/startFile.txt"), StandardCharsets.UTF_8);
            DOMReader.createPersonsFromXMLString(xml);
            System.out.println("Коллекция загружена.");
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
        }

        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

            serverChannel.bind(new InetSocketAddress(PORT));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Сервер (NIO) запущен на порту " + PORT);

            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (key.isAcceptable()) {
                        handleAccept(serverChannel, selector);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleAccept(ServerSocketChannel serverChannel, Selector selector) throws IOException {
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(8192));
        System.out.println("Клиент подключён: " + clientChannel.getRemoteAddress());
    }

    private static void handleRead(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();

        try {
            int bytesRead = channel.read(buffer);
            if (bytesRead == -1) {
                channel.close();
                return;
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), 0, buffer.position());
            ObjectInputStream ois = new ObjectInputStream(bais);
            CommandRequest request = (CommandRequest) ois.readObject();

            CommandResponse response = Invoker.process(request);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(response);
            oos.flush();

            byte[] responseBytes = baos.toByteArray();
            channel.write(ByteBuffer.wrap(responseBytes));
            buffer.clear();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка обработки клиента: " + e.getMessage());
            try { channel.close(); } catch (IOException ignored) {}
        }
    }
}
