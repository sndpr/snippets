package org.psc.misc.sealed.minions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MinionProcessorTest {

    @Test
    void applyEffect() {
        var minionProcessor = new MinionProcessor();
        assertThat(minionProcessor.applyEffect(new Dragon())).isEqualTo("dragons get a significant attack bonus: 15.");
        assertThat(minionProcessor.applyEffect(new TokenMinion())).contains("always has 1 health");
    }
}