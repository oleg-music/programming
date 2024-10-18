package pokemons;

import moves.glameow.*;
import moves.purugly.*;
import ru.ifmo.se.pokemon.*;

public class Purugly extends Glameow {
    public Purugly(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL);
        super.setStats(71, 82, 64, 64, 59, 112);
        super.setMove(new Swagger(0, 85), new Scratch(40, 100), new Charm(0, 100),
                new BodySlam(85, 100));
    }
}