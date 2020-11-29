package org.psc.misc.sealed.minions;

public class TokenMinion extends SpecialMinion {

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public Effect getEffect(int attack, int health) {
        return () -> super.getEffect(attack, health).apply() + " A token minion always has 1 health.";
    }
}
