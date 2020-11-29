package org.psc.misc.sealed.minions;

public class MinionProcessor {

    public String applyEffect(Minion minion){
        int attack;
        if (minion instanceof Beast beast){
            attack = beast.getAttack() * 2;
        } else if (minion instanceof Dragon dragon){
            attack = dragon.getAttack() * 3;
        } else if(minion instanceof SpecialMinion specialMinion){
            attack = Math.max(specialMinion.getAttack() - 1, 0);
        } else {
            throw new IllegalArgumentException("given minion type not supported");
        }
        return minion.getEffect(attack, minion.getHealth()).apply();
    }

}
