package org.psc.env;

import java.util.Map;
import java.util.function.Predicate;

public interface EnvironmentAwarePropertyResolver<T> {

    default T getProperty(){
        return getStrategy().apply();
    }

    ResolutionStrategy<T> getStrategy();

    interface Builder<T> {
        Builder<T> resolutionStrategy(ResolutionStrategy<T> resolutionStrategy);

        Builder<T> property(T property, String... environmentValues);

        Builder<T> propertyValueValidator(Predicate<T> propertyValueValidator);

        // TODO: dedicated exception type
        EnvironmentAwarePropertyResolver<T> build() throws Exception;
    }

    interface ResolutionStrategy<T> {

        void initialize(Map<String, T> properties);

        T apply();
    }

}
