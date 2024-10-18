package pokemons;

import moves.latias.*;
import ru.ifmo.se.pokemon.*;

public class Latias extends Pokemon {
    public Latias(String name, int level) {
        super(name, level);
        super.setType(Type.DRAGON, Type.PSYCHIC);
        super.setStats(80, 80, 90, 110, 130, 110);
        super.setMove(new ShadowBall(80, 100), new Swagger(0, 85), new DoubleTeam(0, 0),
                new ThunderWave(0, 90));
    }
}
