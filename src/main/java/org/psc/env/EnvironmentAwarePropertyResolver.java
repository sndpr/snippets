package org.psc.env;

import java.util.Map;

public interface EnvironmentAwarePropertyResolver<T> {

    default T getProperty(){
        return getStrategy().apply(getProperties());
    }

    Map<String, T> getProperties();

    EnvironmentResolutionStrategy<T> getStrategy();

    default Builder<T> builder(){
        return new DefaultEnvironmentAwarePropertyResolverBuilder<>();
    }

    interface Builder<T> {
        Builder<T> resolutionStrategy(EnvironmentResolutionStrategy<T> resolutionStrategy);

        Builder<T> property(T property, String... environmentValues);

        Builder<T> properties(Map<String, T> properties);

        EnvironmentAwarePropertyResolver<T> build();
    }

    interface EnvironmentResolutionStrategy<T> {
        T apply(Map<String, T> properties);
    }


}
