package org.psc.env;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
public class DefaultEnvironmentAwarePropertyResolverBuilder<T> implements EnvironmentAwarePropertyResolver.Builder<T> {

    private EnvironmentAwarePropertyResolver.EnvironmentResolutionStrategy<T> resolutionStrategy;

    private Map<String, T> properties = new ConcurrentHashMap<>();

    @Override
    public EnvironmentAwarePropertyResolver.Builder<T> resolutionStrategy(
            EnvironmentAwarePropertyResolver.EnvironmentResolutionStrategy<T> resolutionStrategy) {
        this.resolutionStrategy = resolutionStrategy;
        return this;
    }

    @Override
    public EnvironmentAwarePropertyResolver.Builder<T> property(T property, String... environmentValues) {
        for(String environmentValue: environmentValues){
            properties.put(environmentValue.toLowerCase(), property);
        }
        return this;
    }

    @Override
    public EnvironmentAwarePropertyResolver<T> build() {
        return new DefaultEnvironmentAwarePropertyResolver<>(properties, resolutionStrategy);
    }
}
