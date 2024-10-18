package pokemons;

import moves.wigglytuff.*;
import moves.jigglypuff.*;
import moves.igglybuff.*;
import ru.ifmo.se.pokemon.*;

public class Wigglytuff extends Jigglypuff {
    public Wigglytuff(String name, int level) {
        super(name, level);
        super.setType(Type.NORMAL, Type.FAIRY);
        super.setStats(140, 70, 45, 85, 50, 45);
        super.setMove(new Swagger(0, 85), new ShadowBall(80, 100), new BodySlam(85, 100),
                new Thunder(110, 70));
    }
}
