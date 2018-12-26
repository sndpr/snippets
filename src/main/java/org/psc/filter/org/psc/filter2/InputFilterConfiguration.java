package org.psc.filter.org.psc.filter2;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
public enum InputFilterConfiguration
		implements FilterConfiguration<String, List<String>, List<String>> {
	FILTER(e -> e.contains("a"), e -> e.stream().filter(s -> s.contains("a")).collect(Collectors.toList()));

	private Predicate<String> filterCondition;
	private Function<List<String>, List<String>> filterFunction;

	private InputFilterConfiguration(Predicate<String> filterCondition,
			Function<List<String>, List<String>> filterFunction) {
		this.filterCondition = filterCondition;
		this.filterFunction = filterFunction;
	}

	@Override
	public List<FilterConfiguration<String, List<String>, List<String>>> getConfigurations() {
		return Arrays.asList(InputFilterConfiguration.values());
	}
}
