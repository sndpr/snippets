package org.psc.generics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

class InOutProcessorTest {

    @Test
    void shouldProcessInOuts() {
        var inOutProcessor = new InOutProcessor(List.of(new IntInOut(), new StringInOut(), new IntInOut(5)));
        inOutProcessor.processInOuts();
    }

    static class IntInOut implements InOut<Integer> {

        private final int modifier;

        IntInOut(int modifier) {
            this.modifier = modifier;
        }

        IntInOut() {
            this(1);
        }

        @Override
        public List<Integer> getItems() {
            return Stream.of(1, 2, 3, 4, 5).map(it -> it * modifier).toList();
        }

        @Override
        public void processItems(List<Integer> items) {
            items.forEach(it -> System.out.printf("Int: %s%n", it));
        }

    }

    static class StringInOut implements InOut<String> {

        @Override
        public List<String> getItems() {
            return List.of("hello", "world");
        }

        @Override
        public void processItems(List<String> items) {
            items.forEach(it -> System.out.printf("String: %s%n", it));
        }

    }

}