package pokemons;

import moves.igglybuff.*;
import ru.ifmo.se.pokemon.*;

public class Igglybuff extends Pokemon {
    public Igglybuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(90, 30, 15, 40, 20, 15);
        super.setMove(new Swagger(0, 85), new ShadowBall(80, 100));
    }
}
