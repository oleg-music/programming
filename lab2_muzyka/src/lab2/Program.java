package lab2;

import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Program {
    public static void main(String[] args) {
        Battle b = new Battle();

        Glameow glameow = new Glameow("Иван", 1);
        Purugly purugly = new Purugly("Артём", 1);
        Latias latias = new Latias("Толик", 1);

        Igglybuff igglybuff = new Igglybuff("Вова", 1);
        Jigglypuff jigglypuff = new Jigglypuff("Геннадий", 1);
        Wigglytuff wigglytuff= new Wigglytuff("Иннокентий", 1);

        b.addAlly(glameow);
        b.addAlly(purugly);
        b.addAlly(latias);b.addAlly(glameow);

        b.addFoe(igglybuff);
        b.addFoe(jigglypuff);
        b.addFoe(wigglytuff);

        b.go();
    }
}