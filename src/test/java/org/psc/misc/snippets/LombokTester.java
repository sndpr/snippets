package org.psc.misc.snippets;


import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LombokTester {
    private static final Logger LOGGER = LoggerFactory.getLogger(LombokTester.class);

    @Test
    void test() {
        DefaultPojo pojo = new DefaultPojo("defPojo", BigInteger.valueOf(512), new ArrayList<String>());

        LOGGER.info(pojo.getName());
        assertEquals(pojo.getId(), BigInteger.valueOf(512));
    }

}
