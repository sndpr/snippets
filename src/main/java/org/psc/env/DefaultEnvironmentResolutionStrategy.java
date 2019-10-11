package org.psc.env;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.BiFunction;

@NoArgsConstructor
public class DefaultEnvironmentResolutionStrategy<T> implements EnvironmentAwarePropertyResolver.EnvironmentResolutionStrategy<T> {

    public static final String ENVIRONMENT_PROPERTY_KEY = "environment";

    @Override
    public T apply(Map<String, T> properties) {
        String environment = System.getProperty(ENVIRONMENT_PROPERTY_KEY);
        T resolvedProperty;

        if (StringUtils.equalsIgnoreCase(environment, "prod")) {
            resolvedProperty = resolveChain("prod", properties, "PROD", (key, props) -> props.get(key));
        } else if (StringUtils.equalsAnyIgnoreCase(environment, "fach", "qa")) {
            resolvedProperty = resolveChain("fach", properties, "FACH",
                    (key, props) -> resolveChain(key, properties, "qa",
                            (key1, props1) -> resolveChain(key1, props1, "QA", (key2, props2) -> props2.get(key2))));
        } else if (StringUtils.equalsAnyIgnoreCase(environment, "test", "build")) {
            resolvedProperty = resolveChain("test", properties, "TEST",
                    (key, props) -> resolveChain(key, properties, "build",
                            (key1, props1) -> resolveChain(key1, props1, "BUILD", (key2, props2) -> props2.get(key2))));
        } else {
            resolvedProperty = properties.get("local");
        }

        return resolvedProperty;
    }

    private T resolveChain(String key, Map<String, T> properties, String nextKey,
                           BiFunction<String, Map<String, T>, T> next) {
//        T resolvedProperty = properties.computeIfAbsent(key, next.apply(properties));
        T resolvedProperty = properties.get(key);
        if (resolvedProperty == null) {
            resolvedProperty = next.apply(nextKey, properties);
        }

        return resolvedProperty;
    }

}
