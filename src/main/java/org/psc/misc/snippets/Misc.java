package org.psc.misc.snippets;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.StatUtils;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Slf4j
public class Misc {

    public <T> void processArray(T[] array) {
        for (T elem : array) {
            log.info(String.valueOf(elem.hashCode()));
        }
    }

    public static double constantGeometricMean() {
        double[] values = {1.0, 1.2, 2.3, 7.5, 8};
        double geometricMean = StatUtils.geometricMean(values);
        log.info("geometricMean of {} = {}", Arrays.stream(values).mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]")), geometricMean);
        return geometricMean;
    }

    public int inlineRecord() {
        record Customer(int id, String firstname, String lastname, OptionalInt score) {

            private Customer(int id, String firstName, String lastname) {
                this(id, firstName, lastname, OptionalInt.of(0));
            }

        }

        var person = new Customer(1, "John", "Doe");
        return person.score.orElse(500);
    }

}
