package org.psc.misc.sealed.minions;

public non-sealed class SpecialMinion implements Minion {
    @Override
    public int getAttack() {
        return 10;
    }

    @Override
    public int getHealth() {
        return 10;
    }

    @Override
    public Effect getEffect(int attack, int health) {
        return () -> "special minions have reduced attack: " + attack + ".";
    }
}
