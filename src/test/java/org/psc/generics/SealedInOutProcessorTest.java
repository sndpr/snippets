package org.psc.generics;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

class SealedInOutProcessorTest {

    @Test
    void shouldProcessInOuts() {
        var inOutProcessor = new SealedInOutProcessor(List.of(
                new IntSealedInOut(),
                new StringSealedInOut(),
                new IntSealedInOut(5)));
        inOutProcessor.processInOuts();
    }

    static class IntSealedInOut extends SealedInOut.Simple<Integer> {
        private final int modifier;

        IntSealedInOut(int modifier) {
            this.modifier = modifier;
        }

        IntSealedInOut() {
            this(1);
        }

        @Override
        public List<Integer> getItems() {
            return Stream.of(1, 1, 1, 2, 2, 8977878).map(it -> it * modifier).toList();
        }

        @Override
        public void processItems(List<Integer> items) {
            items.forEach(item -> System.out.printf("Int: %s%n", item));
        }
    }

    static class StringSealedInOut extends SealedInOut.Complex<String> {

        @Override
        public List<String> getItems() {
            return List.of("always", "dumb", "forever", "stupid");
        }

        @Override
        public void processItems(List<String> items) {
            items.forEach(item -> System.out.printf("String: %s%n", item));
        }

        @Override
        public List<String> getPartialItems(LocalDateTime from) {
            return List.of(from.toString());
        }

        @Override
        public void processPartialItems(List<String> items) {
            processItems(items);
        }

    }


}