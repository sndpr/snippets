package org.psc.env;

import java.util.Map;

public class DefaultEnvironmentResolutionStrategy<T> implements EnvironmentAwarePropertyResolver.ResolutionStrategy<T> {

    public static final String ENVIRONMENT_PROPERTY_KEY = "environment";

    @Override
    public T apply(Map<String, T> properties) {
        String environment = System.getProperty(ENVIRONMENT_PROPERTY_KEY).toLowerCase();
        return properties.getOrDefault(environment, properties.get(""));
    }
}
