package org.psc.env;

import java.util.Map;

public class DefaultEnvironmentResolutionStrategy<T> implements EnvironmentAwarePropertyResolver.ResolutionStrategy<T> {

    public static final String ENVIRONMENT_PROPERTY_KEY = "environment";

    private final String environment;

    DefaultEnvironmentResolutionStrategy() {
        environment = System.getProperty(ENVIRONMENT_PROPERTY_KEY).toLowerCase();
    }

    @Override
    public T apply(Map<String, T> properties) {
        return properties.getOrDefault(environment, properties.get(""));
    }
}
