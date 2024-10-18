package pokemons;

import moves.igglybuff.*;
import moves.jigglypuff.*;
import ru.ifmo.se.pokemon.*;

public class Jigglypuff extends Igglybuff {
    public Jigglypuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(115, 45, 20, 45, 25, 20);
        super.setMove(new Swagger(0, 85), new ShadowBall(80, 100), new BodySlam(85, 100));
    }
}