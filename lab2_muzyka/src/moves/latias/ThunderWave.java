package moves.latias;

import ru.ifmo.se.pokemon.*;

public class ThunderWave extends StatusMove {
    public ThunderWave(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }

    @Override
    protected String describe() {
        return "парализует, используя громовую волну,";
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        Effect.paralyze(p);
    }
}