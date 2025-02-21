package org.example.commandsPackage;

import org.example.Invoker;
import org.example.Utils.Reader;

import java.util.ArrayList;

/**
 * Класс ExecuteScriptCommand реализует команду выполнения скрипта из указанного файла.
 * Скрипт содержит команды, которые выполняются последовательно.
 * Команда предотвращает рекурсивное выполнение скриптов.
 */
public class ExecuteScriptCommand implements Command {

    /**
     * Выполняет команду выполнения скрипта из указанного файла.
     * Если скрипт уже выполняется, выводит сообщение о рекурсии и завершает выполнение.
     * Иначе считывает команды из файла и выполняет их последовательно.
     */
    @Override
    public void execute() {
        String fileName = Invoker.getArgument();

        if (Invoker.isScriptExecuting(fileName)) {
            System.out.println("Обнаружена рекурсия: скрипт " + fileName + " уже выполняется.");
            return;
        }

        Invoker.addExecutingScript(fileName);

        ArrayList<String> scriptStrings = Reader.readFileInStrings(fileName);

        Invoker invoker = new Invoker();
        for (String string : scriptStrings) {
            invoker.invoke(string);
        }

        Invoker.removeExecutingScript(fileName);
    }

    /**
     * Возвращает описание команды.
     *
     * @return Строка с описанием команды.
     */
    @Override
    public String descr() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла." +
                " В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}