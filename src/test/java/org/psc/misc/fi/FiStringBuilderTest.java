package org.psc.misc.fi;



import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class FiStringBuilderTest {

    @Test
    void testFiStringBuilder() {
        String concatenatedString = new FiStringBuilder().apply("asdasd", ", ", "sdadsad", ": qwewqeqw");
        assertNotNull(concatenatedString);
        log.info(concatenatedString);
    }
}
