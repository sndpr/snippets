package org.psc.misc.snippets;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LombokTester {
    private static final Logger LOGGER = LoggerFactory.getLogger(LombokTester.class);

    @Test
    public void test() {
        DefaultPojo pojo = new DefaultPojo("defPojo", BigInteger.valueOf(512), new ArrayList<String>());

        LOGGER.info(pojo.getName());
        assertEquals(pojo.getId(), BigInteger.valueOf(512));

    }

}
