package org.psc.misc.snippets;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LombokTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LombokTest.class);

    @Test
    void test() {
        DefaultPojo pojo = new DefaultPojo("defPojo", BigInteger.valueOf(512), new ArrayList<>());

        LOGGER.info(pojo.getName());
        assertEquals(pojo.getId(), BigInteger.valueOf(512));
    }

    @Test
    void test1() {
        DefaultPojo pojo = new DefaultPojo("defPojo", BigInteger.valueOf(512), new ArrayList<>());
        DefaultPojo.Inner inner = pojo.inner();

        LOGGER.info(pojo.getName());
        assertEquals(pojo.getId(), BigInteger.valueOf(512));
        assertThat(inner.getSome()).isEqualTo("defPojo: inner");
    }

}
