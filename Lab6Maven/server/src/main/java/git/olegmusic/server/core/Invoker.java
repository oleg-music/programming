package git.olegmusic.server.core;

import git.olegmusic.common.CommandRequest;
import git.olegmusic.common.CommandResponse;
import git.olegmusic.server.commands.*;

import java.util.HashMap;

public class Invoker {
    private static final HashMap<String, Command> commands = new HashMap<>();

    static {
//        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
//        commands.put("update", new UpdateCommand());
//        commands.put("remove_by_id", new RemoveByIdCommand());
//        commands.put("clear", new ClearCommand());
//        commands.put("save", new SaveCommand());
//        commands.put("exit", new ExitCommand());
//        commands.put("count_greater_than_birthday", new CountGreaterThanBirthdayCommand());
//        commands.put("history", new HistoryCommand());
//        commands.put("print_unique_eye_color", new PrintUniqueEyeColorCommand());
//        commands.put("print_field_descending_birthday", new PrintFieldDescendingBirthdayCommand());
//        commands.put("remove_greater", new RemoveGreaterCommand());
//        commands.put("remove_lower", new RemoveLowerCommand());
//        commands.put("execute_script", new ExecuteScriptCommand());
    }

    public static CommandResponse process(CommandRequest request) {
        String commandName = request.getCommandName();
        Command command = commands.get(commandName);

        if (command == null) {
            return new CommandResponse("Неизвестная команда: " + commandName);
        }

        try {
            // Передача аргумента и объекта Person — если нужно
            command.setArgument(request.getArgument());
            command.setPerson(request.getPersonData());

            String result = command.execute();
            return new CommandResponse(result);
        } catch (Exception e) {
            return new CommandResponse("Ошибка выполнения команды: " + e.getMessage());
        }
    }
}
