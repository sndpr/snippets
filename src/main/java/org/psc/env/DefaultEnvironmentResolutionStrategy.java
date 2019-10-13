package org.psc.env;

import java.util.Map;

public class DefaultEnvironmentResolutionStrategy<T> implements EnvironmentAwarePropertyResolver.ResolutionStrategy<T> {

    public static final String ENVIRONMENT_PROPERTY_KEY = "environment";

    private final String environment;
    private T resolvedValue;

    DefaultEnvironmentResolutionStrategy(Map<String, T> properties) {
        environment = System.getProperty(ENVIRONMENT_PROPERTY_KEY).toLowerCase();
        initialize(properties);
    }

    @Override
    public void initialize(Map<String, T> properties) {
        resolvedValue = properties.getOrDefault(environment, properties.get(""));
    }

    @Override
    public T apply() {
        return resolvedValue;
    }
}
