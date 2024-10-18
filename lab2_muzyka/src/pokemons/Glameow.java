package pokemons;

import moves.glameow.*;
import ru.ifmo.se.pokemon.*;

public class Glameow extends Pokemon {
    public Glameow(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL);
        super.setStats(49, 55, 42, 42, 37, 85);
        super.setMove(new Swagger(0, 85), new Scratch(40, 100), new Charm(0, 100));
    }
}