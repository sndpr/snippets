package org.psc.misc.sealed.minions;

public final class Dragon implements Minion {
    @Override
    public int getAttack() {
        return 5;
    }

    @Override
    public int getHealth() {
        return 5;
    }

    @Override
    public Effect getEffect(int attack, int health) {
        return () -> "dragons get a significant attack bonus: " + attack + ".";
    }
}
