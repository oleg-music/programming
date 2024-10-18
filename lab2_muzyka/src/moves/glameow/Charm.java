package moves.glameow;

import ru.ifmo.se.pokemon.*;

public class Charm extends StatusMove{
    public Charm(double pow, double acc) {
        super(Type.FAIRY, pow, acc);
    }

    @Override
    protected String describe() {
        return "очаровывает";
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK, -2).turns(1);
        p.addEffect(e);
    }
}