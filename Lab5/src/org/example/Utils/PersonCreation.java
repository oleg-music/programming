package org.example.Utils;

import org.example.collectionClasses.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.example.Utils.XMLConverter.getTegValue;

/**
 * Класс PersonCreation отвечает за создание объектов Person.
 * Предоставляет методы для создания объектов Person из XML-строки и через консольный ввод.
 */
public class PersonCreation {

    /**
     * Создает объекты Person из XML-строки и добавляет их в коллекцию.
     * Если данные в XML некорректны, выводит сообщение об ошибке.
     *
     * @param fileInString XML-строка, содержащая данные для создания объектов Person.
     */
    public static void createPersonsFromXMLString(String fileInString) {
        int index = 0;
        boolean isFileCorrect = true;

        while ((index = fileInString.indexOf("<person>", index)) != -1) {
            int endIndex = fileInString.indexOf("</person>", index);
            String personXml = fileInString.substring(index, endIndex + "</person>".length());
            String name; // Поле не может быть null, Строка не может быть пустой
            Coordinates coordinates; // Поле не может быть null
            Long height; // Поле может быть null, Значение поля должно быть больше 0
            java.time.ZonedDateTime birthday; // Поле не может быть null
            EyeColor eyeColor; // Поле может быть null
            HairColor hairColor; // Поле не может быть null
            Location location;

            name = getTegValue(personXml, "name", "");
            if (name == "") {
                System.out.println("Поле 'name' не может быть пустым. Введите имя корректно.");
                isFileCorrect = false;
                break;
            }

            try {
                Long x = Long.valueOf(getTegValue(personXml, "x", "coordinates"));
                Float y = Float.valueOf(getTegValue(personXml, "y", "coordinates"));
                if (y <= -679) {
                    System.out.println("Значение координаты 'y' у поля coordinates должно быть больше -679.");
                    isFileCorrect = false;
                    break;
                }
                coordinates = new Coordinates(x, y);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат поля 'coordinates'.");
                isFileCorrect = false;
                break;
            }

            try {
                String heightString = getTegValue(personXml, "height", "height");
                if (heightString.trim() == "") {
                    height = null;
                } else {
                    height = Long.valueOf(getTegValue(personXml, "height", ""));
                    if (height <= 0) {
                        System.out.println("Рост должен быть больше 0");
                        isFileCorrect = false;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат поля 'height'.");
                isFileCorrect = false;
                break;
            }

            try {
                birthday = ZonedDateTime.parse(getTegValue(personXml, "birthday", ""));
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат поля 'birthday'.");
                isFileCorrect = false;
                break;
            }

            try {
                String eyeColorString = getTegValue(personXml, "eyeColor", "");
                if (eyeColorString.trim() == "") {
                    eyeColor = null;
                } else {
                    eyeColor = EyeColor.valueOf(eyeColorString);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный формат поля 'eyeColor'.");
                isFileCorrect = false;
                break;
            }

            try {
                hairColor = HairColor.valueOf(getTegValue(personXml, "hairColor", ""));
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный формат поля 'hairColor'.");
                isFileCorrect = false;
                break;
            }

            try {
                if (getTegValue(personXml, "location", "").trim() == "") {
                    location = null;
                } else {
                    float x = Float.parseFloat(getTegValue(personXml, "x", "location"));
                    Integer y = Integer.valueOf(getTegValue(personXml, "y", "location"));
                    Float z = Float.valueOf(getTegValue(personXml, "z", "location"));
                    String locationName = getTegValue(personXml, "name", "location");
                    if (locationName == "") {
                        System.out.println("Поле 'name' класса location не может быть пустым. Введите название корректно.");
                        isFileCorrect = false;
                        break;
                    }
                    location = new Location(x, y, z, locationName);
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат поля 'location'.");
                isFileCorrect = false;
                break;
            }

            Person person = new Person(name, coordinates, height, birthday, eyeColor, hairColor, location);
            CollectionManager.add(person);
            index = endIndex + "</person>".length();
        }
        if (!isFileCorrect) {
            System.out.println("Данные из файла некорректны.");
        }
    }

    /**
     * Создает объект Person через консольный ввод.
     * Запрашивает у пользователя данные для каждого поля объекта Person.
     *
     * @return Созданный объект Person.
     */
    public static Person createPersonFromConsol() {
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
        for (
                EyeColor value : EyeColor.values()) {
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
        for (
                HairColor value : HairColor.values()) {
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
        return new Person(name, coordinates, height, birthday, eyeColor, hairColor, location);
    }
}
