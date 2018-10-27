package org.psc.fi;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.psc.misc.fi.FiStringBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FiStringBuilderTest {

    @Test
    public void testFiStringBuilder() {
        String concatenatedString = new FiStringBuilder().apply("asdasd", ", ", "sdadsad", ": qwewqeqw");
        assertNotNull(concatenatedString);
        log.info(concatenatedString);
    }
}
