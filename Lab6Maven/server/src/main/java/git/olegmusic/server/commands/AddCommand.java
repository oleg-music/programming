package git.olegmusic.server.commands;

import git.olegmusic.common.Person;
import git.olegmusic.server.utils.CollectionManager;

public class AddCommand implements Command {
    private Person person;

    @Override
    public void setArgument(String argument) {
        // add не использует аргумент строки
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String execute() {
        if (person == null) {
            return "Ошибка: объект Person не передан.";
        }
        CollectionManager.add(person);
        return "Объект успешно добавлен в коллекцию.";
    }

    @Override
    public String descr() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}

