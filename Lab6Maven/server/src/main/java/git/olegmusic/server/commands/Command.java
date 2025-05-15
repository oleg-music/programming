package git.olegmusic.server.commands;

import git.olegmusic.common.Person;

public interface Command {
    void setArgument(String argument);
    void setPerson(Person person);
    String execute(); // теперь возвращает результат в виде строки
    String descr();
}
