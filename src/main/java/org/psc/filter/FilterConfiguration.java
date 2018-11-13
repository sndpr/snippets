package org.psc.filter;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FilterConfiguration<T extends Enum<T>, P, I, O> {
	List<FilterConfiguration<T, P, I, O>> getConfigurations();

	Predicate<P> getFilterCondition();

	Function<I, O> getFilterFunction();
}
