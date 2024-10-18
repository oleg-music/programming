package moves.latias;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }

    @Override
    protected String describe() {
        return "использует DoubleTeam";
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.SPEED, 1).turns(1);
        p.addEffect(e);
    }
}
