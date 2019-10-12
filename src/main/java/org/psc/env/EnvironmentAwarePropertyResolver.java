package org.psc.env;

import java.util.Map;

public interface EnvironmentAwarePropertyResolver<T> {

    default T getProperty(){
        return getStrategy().apply(getProperties());
    }

    Map<String, T> getProperties();

    ResolutionStrategy<T> getStrategy();

    interface Builder<T> {
        Builder<T> resolutionStrategy(ResolutionStrategy<T> resolutionStrategy);

        Builder<T> property(T property, String... environmentValues);

        // TODO: dedicated exception type
        EnvironmentAwarePropertyResolver<T> build() throws Exception;
    }

    interface ResolutionStrategy<T> {
        T apply(Map<String, T> properties);
    }

}
