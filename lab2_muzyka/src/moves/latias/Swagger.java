package moves.latias;

import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
    public Swagger(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        return "сбивает с толку";
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        p.confuse();
        Effect e = new Effect().stat(Stat.ATTACK, 2).turns(1);
        p.addEffect(e);
    }
}
