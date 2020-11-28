package org.psc.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ToStringMappingStoreTest {

    @Test
    void testMappings() {
        var toStringMappingStore = new ToStringMappingStore();

        assertThat(toStringMappingStore.getMapping(String.class).apply("Hello")).isEqualTo("'Hello'");
        assertThat(toStringMappingStore.getMapping(Integer.class).apply(5161)).isEqualTo("5161");
    }

}