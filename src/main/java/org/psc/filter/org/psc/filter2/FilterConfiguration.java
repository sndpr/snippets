package org.psc.filter.org.psc.filter2;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FilterConfiguration<P, I, O> {
	List<FilterConfiguration<P, I, O>> getConfigurations();

	Predicate<P> getFilterCondition();

	Function<I, O> getFilterFunction();
}
