package org.example;

import org.example.Utils.PersonCreation;
import org.example.Utils.Reader;

/**
 * Главный класс приложения, который запускает программу и управляет основным потоком выполнения.
 * Инициализирует начальные данные из файла, создает объекты Person и обрабатывает команды пользователя.
 */
public class Main {

    /**
     * Объект Invoker, который отвечает за выполнение команд.
     */
    private static final Invoker invoker = new Invoker();

    /**
     * Точка входа в программу.
     * Загружает начальные данные из файла, создает объекты Person и переходит в режим обработки команд пользователя.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        String fileName = "startFile.txt";

        String filInString = Reader.readFileInString(fileName);

        if (filInString != "") {
            PersonCreation.createPersonsFromXMLString(filInString);
        }

//         Person p1 = CollectionManager.getPersonById(1);
//         Person p2 = CollectionManager.getPersonById(2);
//         Person p3 = CollectionManager.getPersonById(3);
//         ArrayList<Person> persons = new ArrayList<>();
//         persons.add(p1);
//         persons.add(p2);
//         persons.add(p3);
//         System.out.println(persons);
//         Collections.sort(persons);
//         System.out.println(persons);

        while (Reader.hasNext()) {
            String line = Reader.nextLine();
            if (line.trim() != "") {
                invoker.invoke(line);
            }
        }
    }
}