package org.psc.sort;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OpinionatedFilenameComparator implements Comparator<String> {

    private static final Pattern FILE_INDEX_PATTERN = Pattern.compile("\\(\\d+\\)");

    @Override
    public int compare(String first, String second) {
        String firstExtension = StringUtils.substringAfterLast(first, ".");
        String firstWithoutExtension = StringUtils.substringBeforeLast(first, ".");
        List<String> tokenizedFirst = Stream.of(StringUtils.split(firstWithoutExtension, " "))
                .collect(Collectors.toList());
        tokenizedFirst.add(firstExtension);

        String secondExtension = StringUtils.substringAfterLast(second, ".");
        String secondWithoutExtension = StringUtils.substringBeforeLast(second, ".");
        List<String> tokenizedSecond = Stream.of(StringUtils.split(secondWithoutExtension, " "))
                .collect(Collectors.toList());
        tokenizedSecond.add(secondExtension);

        FilenameIndexConfiguration firstConfiguration = findFilenameIndex(tokenizedFirst);
        FilenameIndexConfiguration secondConfiguration = findFilenameIndex(tokenizedSecond);

        int upperBoundary =
                Math.min(firstConfiguration.normalizedTokens.size(), secondConfiguration.normalizedTokens.size());
        int compare = 0;
        for (int i = 0; i < upperBoundary && compare == 0; i++) {
            compare = String.CASE_INSENSITIVE_ORDER.compare(firstConfiguration.normalizedTokens.get(i),
                    secondConfiguration.normalizedTokens.get(i));
        }

        if (compare == 0) {
            compare = Integer.compare(firstConfiguration.filenameIndex, secondConfiguration.filenameIndex);
        }

        return compare;
    }

    private FilenameIndexConfiguration findFilenameIndex(List<String> tokens) {
        int filenameIndex = 0;
        List<String> normalizedTokens = new ArrayList<>(tokens);

        for (int i = normalizedTokens.size() - 1; i >= 0; i--) {
            String testSequence = normalizedTokens.get(i);
            if (FILE_INDEX_PATTERN.matcher(testSequence).matches()) {
                filenameIndex = Integer.parseInt(StringUtils.strip(testSequence, "()"));
                normalizedTokens.remove(i);
                break;
            }
        }

        return FilenameIndexConfiguration.builder()
                .filenameIndex(filenameIndex)
                .normalizedTokens(normalizedTokens)
                .build();
    }

    @Data
    @Builder
    private static class FilenameIndexConfiguration {
        private List<String> normalizedTokens;
        private int filenameIndex;
    }
}
