package org.psc.filter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import lombok.Getter;

@Getter
public enum OutputFilterConfiguration implements FilterConfiguration<OutputFilterConfiguration, String, Integer, Integer> {
	FILTER(e -> e.contains("a"), e -> 2 * e);

	private Predicate<String> filterCondition;
	private Function<Integer, Integer> filterFunction;

	private OutputFilterConfiguration(Predicate<String> filterCondition, Function<Integer, Integer> filterFunction) {
		this.filterCondition = filterCondition;
		this.filterFunction = filterFunction;
	}

	@Override
	public List<FilterConfiguration<OutputFilterConfiguration, String, Integer, Integer>> getConfigurations() {
		return Arrays.asList(OutputFilterConfiguration.values());
	}
}
