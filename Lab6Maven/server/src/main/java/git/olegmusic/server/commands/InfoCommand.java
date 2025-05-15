package git.olegmusic.server.commands;

import git.olegmusic.common.Person;
import git.olegmusic.server.utils.CollectionManager;

public class InfoCommand implements Command {

    @Override
    public void setArgument(String argument) {
        // Не требуется, но должен быть по контракту
    }

    @Override
    public void setPerson(Person person) {
        // Не требуется, но должен быть по контракту
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        sb.append("Тип коллекции: ").append(CollectionManager.getPersonSet().getClass()).append("\n");
        sb.append("Время инициализации: ").append(CollectionManager.getInitializationTime()).append("\n");
        sb.append("Количество элементов: ").append(CollectionManager.getPersonSet().size());
        return sb.toString();
    }

    @Override
    public String descr() {
        return "info : вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
}