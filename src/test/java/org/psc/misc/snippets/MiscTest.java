package org.psc.misc.snippets;

import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MiscTest {

    @Test
    void testConstantGeometricMean() {
        assertThat(Misc.constantGeometricMean()).isCloseTo(2.77, Offset.offset(0.1));
    }

    @Test
    void testInlineRecord() {
        var misc = new Misc();
        assertThat(misc.inlineRecord()).isEqualTo(0);
    }

    @Test
    void assignmentExpression() {
        var misc = new Misc();
        Pair<Integer, Integer> intIntPair = misc.assignmentExpression();
        assertThat(intIntPair)
                .matches(it -> it.getLeft() == 2)
                .matches(it -> it.getLeft().intValue() == it.getRight().intValue());
    }
}