package org.psc.filter;

import java.util.Optional;

public class FilterApplicator<T extends Enum<T>> {

	public <O, P, I> Optional<O> filter(FilterConfiguration<T, P, I, O> configCollection, P predicateParam, I input) {
		O alteredInput = null;
		for (FilterConfiguration<T, P, I, O> config : configCollection.getConfigurations()) {
			if (config.getFilterCondition().test(predicateParam)) {
				alteredInput = config.getFilterFunction().apply(input);
			}
		}
		return Optional.ofNullable(alteredInput);
	}
}
