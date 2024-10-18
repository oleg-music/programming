package moves.jigglypuff;

import ru.ifmo.se.pokemon.*;

public class BodySlam extends PhysicalMove{
    public BodySlam(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        return "наносит удар телом";
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().chance(0.3).condition(Status.PARALYZE).turns(1);
        p.addEffect(e);
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        super.applyOppDamage(p, damage);
    }
}