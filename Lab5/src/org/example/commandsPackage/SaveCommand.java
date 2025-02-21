package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.collectionClasses.Person;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * Класс SaveCommand реализует команду сохранения коллекции в файл.
 * При выполнении команды данные коллекции сохраняются в XML-формате в файл "startFile.txt".
 */
public class SaveCommand implements Command {

    /**
     * Выполняет команду сохранения коллекции в файл.
     * Данные коллекции записываются в файл "startFile.txt" в формате XML.
     * Если происходит ошибка ввода-вывода, выводится стек вызовов исключения.
     */
    @Override
    public void execute() {
        HashSet<Person> personSet = CollectionManager.getPersonSet();

        try (FileWriter writer = new FileWriter("startFile.txt")) {
            writer.write("<persons>\n");

            for (Person person : personSet) {
                writer.write("    <person>\n");
                writer.write("        <id>" + person.getId() + "</id>\n");
                writer.write("        <name>" + person.getName() + "</name>\n");
                writer.write("        <coordinates>\n");
                writer.write("            <x>" + person.getCoordinates().getX() + "</x>\n");
                writer.write("            <y>" + person.getCoordinates().getY() + "</y>\n");
                writer.write("        </coordinates>\n");
                writer.write("        <creationDate>" + person.getCreationDate() + "</creationDate>\n");
                writer.write("        <height>" + person.getHeight() + "</height>\n");
                writer.write("        <birthday>" + person.getBirthday() + "</birthday>\n");
                writer.write("        <eyeColor>" + person.getEyeColor() + "</eyeColor>\n");
                writer.write("        <hairColor>" + person.getHairColor() + "</hairColor>\n");
                writer.write("        <location>\n");
                writer.write("            <x>" + person.getLocation().getX() + "</x>\n");
                writer.write("            <y>" + person.getLocation().getY() + "</y>\n");
                writer.write("            <z>" + person.getLocation().getZ() + "</z>\n");
                writer.write("            <name>" + person.getLocation().getName() + "</name>\n");
                writer.write("        </location>\n");
                writer.write("    </person>\n");
            }

            writer.write("</persons>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "save : сохранить коллекцию в файл";
    }
}

