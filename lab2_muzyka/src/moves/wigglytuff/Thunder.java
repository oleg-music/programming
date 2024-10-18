package moves.wigglytuff;

import ru.ifmo.se.pokemon.*;

public class Thunder extends SpecialMove{
    public Thunder(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected String describe() {
        return "наносит урон громом";
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
