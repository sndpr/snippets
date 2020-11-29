package org.psc.misc.sealed.minions;

public final class Beast implements Minion {
    @Override
    public int getAttack() {
        return 6;
    }

    @Override
    public int getHealth() {
        return 4;
    }

    @Override
    public Effect getEffect(int attack, int health) {
        return () -> "beasts get an attack bonus: " + attack + ".";
    }
}
