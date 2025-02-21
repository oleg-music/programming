package org.example.commandsPackage;

import org.example.Utils.CollectionManager;
import org.example.Invoker;
import org.example.Utils.Reader;
import org.example.collectionClasses.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс UpdateCommand реализует команду обновления элемента коллекции по его id.
 * При выполнении команды пользователь вводит новые данные для объекта Person,
 * и элемент с указанным id обновляется в коллекции.
 */
public class UpdateCommand implements Command {

    /**
     * Выполняет команду обновления элемента коллекции по его id.
     * Если элемент с указанным id не существует, выводится сообщение об ошибке.
     * Иначе пользователь вводит новые данные для объекта Person, и элемент обновляется.
     */
    @Override
    public void execute() {
        Integer id = Integer.valueOf(Invoker.getArgument());

        Person person = CollectionManager.getPersonById(id);

        if (person == null) {
            System.out.println("Объекта класса Person с данным id не существует.");
        } else {
            String name = null; // Поле не может быть null, Строка не может быть пустой
            Coordinates coordinates = null; // Поле не может быть null
            Long height = null; // Поле может быть null, Значение поля должно быть больше 0
            java.time.ZonedDateTime birthday = null; // Поле не может быть null
            EyeColor eyeColor = null; // Поле может быть null
            HairColor hairColor = null; // Поле не может быть null
            Location location = null; // Поле может быть null
            boolean is_correct = false;

            System.out.print("Введите имя: ");
            while (!is_correct) {
                name = Reader.nextLine();
                if (name.trim() != "") {
                    is_correct = true;
                } else {
                    System.out.print("Поле не может быть пустым, введите имя еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите координаты в формате 'x y': ");
            while (!is_correct) {
                String stringCoordinates = Reader.nextLine();
                if (stringCoordinates.trim() != "") {
                    try {
                        String[] enteredCoordinates = stringCoordinates.split(" ");
                        Long x = Long.valueOf(enteredCoordinates[0]);
                        Float y = Float.valueOf(enteredCoordinates[1]);
                        if (y <= -679) {
                            System.out.print("Значение координаты 'y' у поля coordinates должно быть больше -679." +
                                    " Введите координаты еще раз: ");
                        } else {
                            coordinates = new Coordinates(x, y);
                            is_correct = true;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.print("Неверный формат. Введите координаты в формате 'x y': ");
                    } catch (NumberFormatException e) {
                        System.out.print("Неверный формат. Введите координаты в формате 'x y', где x и y - числа: ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите координаты в формате 'x y': ");
                }
            }

            is_correct = false;
            System.out.print("Введите рост: ");
            while (!is_correct) {
                String stringHeight = Reader.nextLine();
                try {
                    if (stringHeight == "") {
                        height = null;
                        is_correct = true;
                    } else {
                        height = Long.valueOf(stringHeight);
                        if (height > 0) {
                            is_correct = true;
                        } else {
                            System.out.print("Рост должен быть больше 0, введите рост еще раз: ");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат. Введите число: ");
                }
            }

            is_correct = false;
            System.out.print("Введите дату рождения в формате dd.MM.yyyy (например, 01.01.2001): ");
            while (!is_correct) {
                String stringBirthday = Reader.nextLine();
                if (stringBirthday.trim() != "") {
                    try {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        LocalDate localDate = LocalDate.parse(stringBirthday, dateFormatter);
                        birthday = localDate.atStartOfDay(ZoneId.systemDefault());
                        is_correct = true;
                    } catch (DateTimeParseException e) {
                        System.out.print("Неверный формат. Введите дату рождения в формате dd.MM.yyyy " +
                                "(например, 01.01.2001): ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите дату рождения еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите цвет глаз, любой из: ");
            for (EyeColor value : EyeColor.values()) {
                System.out.print(value + " ");
            }
            while (!is_correct) {
                String stringEyeColor = Reader.nextLine();
                try {
                    if (stringEyeColor == "") {
                        eyeColor = null;
                        is_correct = true;
                    } else {
                        eyeColor = EyeColor.valueOf(stringEyeColor);
                        is_correct = true;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.print("Такого цвета глаз не существует. Введите цвет глаз еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите цвет волос, любой из: ");
            for (HairColor value : HairColor.values()) {
                System.out.print(value + " ");
            }
            while (!is_correct) {
                String stringHairColor = Reader.nextLine();
                if (stringHairColor.trim() != "") {
                    try {
                        hairColor = HairColor.valueOf(stringHairColor);
                        is_correct = true;
                    } catch (IllegalArgumentException e) {
                        System.out.print("Такого цвета волос не существует. Введите цвет волос еще раз: ");
                    }
                } else {
                    System.out.print("Поле не может быть пустым. Введите цвет волос еще раз: ");
                }
            }

            is_correct = false;
            System.out.print("Введите координаты и название локации в формате 'x y z nameOfLocation': ");
            while (!is_correct) {
                String stringLocation = Reader.nextLine();
                try {
                    if (stringLocation == "") {
                        location = null;
                        is_correct = true;
                    } else {
                        String[] enteredLocation = stringLocation.split(" ");
                        float x = Float.parseFloat(enteredLocation[0]);
                        Integer y = Integer.valueOf(enteredLocation[1]);
                        Float z = Float.valueOf(enteredLocation[2]);
                        String nameOfLocation = enteredLocation[3];
                        location = new Location(x, y, z, nameOfLocation);
                        is_correct = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Неверный формат. Введите координаты и название локации в формате" +
                            " 'x y z nameOfLocation': ");
                } catch (NumberFormatException e) {
                    System.out.print("Неверный формат. Введите координаты и название локации в формате" +
                            " 'x y z nameOfLocation', где x, y, z - числа, а nameOfLocation - строка: ");
                }
            }

            person.setName(name);
            person.setCoordinates(coordinates);
            person.setHeight(height);
            person.setBirthday(birthday);
            person.setEyeColor(eyeColor);
            person.setHairColor(hairColor);
            person.setLocation(location);
        }
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}