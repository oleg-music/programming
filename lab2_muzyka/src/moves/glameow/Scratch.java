package moves.glameow;

import ru.ifmo.se.pokemon.*;

public class Scratch extends PhysicalMove {
    public Scratch(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        return "царапает";
    }

    @Override
    protected void applyOppDamage(Pokemon p, double damage) {
        super.applyOppDamage(p, damage);
    }
}

