package org.psc.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class OpinionatedFilenameComparatorTest {

    @Test
    void compare() {
        List<String> input = List.of(
                "xxx123.pdf",
                "abc123 abc 3.txt",
                "abc123 abc 1.txt",
                "jjj (2).pdf",
                "jjj.pdf",
                "jjj (1).pdf",
                "ttt.java"
        );

        List<String> result = input.stream()
                .sorted((a, b) -> new OpinionatedFilenameComparator().compare(a, b))
                .collect(Collectors.toList());

        result.forEach(log::info);

        assertThat(result).containsExactly(
                "abc123 abc 1.txt",
                "abc123 abc 3.txt",
                "jjj.pdf",
                "jjj (1).pdf",
                "jjj (2).pdf",
                "ttt.java",
                "xxx123.pdf");
    }
}