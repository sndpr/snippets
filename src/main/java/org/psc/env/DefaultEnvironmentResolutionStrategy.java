package org.psc.env;

import java.util.Map;
import java.util.function.Predicate;

public class DefaultEnvironmentResolutionStrategy<T> implements EnvironmentAwarePropertyResolver.ResolutionStrategy<T> {

    public static final String ENVIRONMENT_PROPERTY_KEY = "environment";

    private final String environment;
    private final Predicate<T> validator;
    private T resolvedValue;

    DefaultEnvironmentResolutionStrategy(Map<String, T> properties, Predicate<T> validator) {
        this.validator = validator;
        environment = System.getProperty(ENVIRONMENT_PROPERTY_KEY).toLowerCase();
        initialize(properties);
    }

    @Override
    public void initialize(Map<String, T> properties) {
        resolvedValue = properties.getOrDefault(environment, properties.get(""));
        if (!validator.test(resolvedValue)){
            // default value if value for environment is not valid
            resolvedValue = properties.get("");
        }
    }

    @Override
    public T apply() {
        return resolvedValue;
    }
}
