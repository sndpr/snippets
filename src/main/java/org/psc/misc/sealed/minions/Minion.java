package org.psc.misc.sealed.minions;

public sealed interface Minion permits SpecialMinion, Beast, Dragon {

    int getAttack();

    int getHealth();

    Effect getEffect(int attack, int health);

    @FunctionalInterface
    interface Effect {
        String apply();
    }

}
