package org.example.Utils;

/**
 * Класс XMLConverter предоставляет методы для работы с XML-данными.
 * Включает метод для извлечения значения тега из XML-строки.
 */
public class XMLConverter {

    /**
     * Извлекает значение указанного тега из XML-строки.
     * Если указан родительский тег, поиск значения тега происходит внутри этого родительского тега.
     *
     * @param personXml     XML-строка, из которой извлекается значение.
     * @param tagName       Название тега, значение которого нужно извлечь.
     * @param parentTagName Название родительского тега (необязательный параметр).
     *                      Если указан, поиск значения тега происходит внутри этого родительского тега.
     * @return Значение указанного тега. Если тег не найден, возвращает пустую строку.
     */
    public static String getTegValue(String personXml, String tagName, String parentTagName) {
        if (parentTagName != "") {
            int startIndex = personXml.indexOf("<" + parentTagName + ">", 0);
            int endIndex = personXml.indexOf("</" + parentTagName + ">", 0) + 3 + parentTagName.length();
            personXml = personXml.substring(startIndex, endIndex);
        }

        int startIndex = personXml.indexOf("<" + tagName + ">", 0) + 2 + tagName.length();
        int endIndex = personXml.indexOf("</" + tagName + ">", 0);

        return personXml.substring(startIndex, endIndex);
    }
}

