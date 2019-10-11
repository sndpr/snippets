package org.psc.env;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DefaultEnvironmentAwarePropertyResolver<T> implements EnvironmentAwarePropertyResolver<T>  {

    private final Map<String, T> properties;

    private final EnvironmentResolutionStrategy<T> resolutionStrategy;

    @Override
    public Map<String, T> getProperties() {
        return properties;
    }

    @Override
    public EnvironmentResolutionStrategy<T> getStrategy() {
        return resolutionStrategy;
    }
}
