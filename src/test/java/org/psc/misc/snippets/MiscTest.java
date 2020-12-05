package org.psc.misc.snippets;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MiscTest {

    @Test
    void doSomething() {
        assertThat(Misc.constantGeometricMean()).isCloseTo(2.77, Offset.offset(0.1));
    }
}