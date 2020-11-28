package org.psc.sort;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * Sort file names with a substring of format <code>[opening parenthesis][number][closing parenthesis]</code> e.g.
 * <code>my file (1).txt</code> after files without such an substring (e.g. <code>my file.txt</code>).
 */
public class OpinionatedFilenameComparator implements Comparator<String> {

    private static final Pattern FILE_INDEX_PATTERN = Pattern.compile("\\(\\d+\\)");

    @Override
    public int compare(String first, String second) {
        String firstExtension = substringAfterLast(first, ".");
        String firstWithoutExtension = substringBeforeLast(first, ".");
        List<String> tokenizedFirst = Arrays.stream(split(firstWithoutExtension, " "))
                .collect(toList());
        tokenizedFirst.add(firstExtension);

        String secondExtension = substringAfterLast(second, ".");
        String secondWithoutExtension = substringBeforeLast(second, ".");
        List<String> tokenizedSecond = Arrays.stream(split(secondWithoutExtension, " "))
                .collect(toList());
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
