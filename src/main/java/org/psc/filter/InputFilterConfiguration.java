package org.psc.filter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum InputFilterConfiguration
        implements FilterConfiguration<InputFilterConfiguration, String, List<String>, List<String>> {
    FILTER(e -> e.contains("a"), e -> e.stream().filter(s -> s.contains("a")).collect(Collectors.toList()));

    private Predicate<String> filterCondition;
    private Function<List<String>, List<String>> filterFunction;

    private InputFilterConfiguration(Predicate<String> filterCondition,
                                     Function<List<String>, List<String>> filterFunction) {
        this.filterCondition = filterCondition;
        this.filterFunction = filterFunction;
    }

    @Override
    public List<FilterConfiguration<InputFilterConfiguration, String, List<String>, List<String>>> getConfigurations() {
        return Arrays.asList(InputFilterConfiguration.values());
    }

    @Override
    public Predicate<String> getFilterCondition() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Function<List<String>, List<String>> getFilterFunction() {
        // TODO Auto-generated method stub
        return null;
    }
}
