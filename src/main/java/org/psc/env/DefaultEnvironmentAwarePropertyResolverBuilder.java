package org.psc.env;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
public class DefaultEnvironmentAwarePropertyResolverBuilder<T> implements EnvironmentAwarePropertyResolver.Builder<T> {

    private T defaultValue;
    private T local;
    private T build;
    private T qa;
    private T prod;

    private EnvironmentAwarePropertyResolver.ResolutionStrategy<T> resolutionStrategy;

    private Map<String, T> properties = new ConcurrentHashMap<>();

    @Override
    public EnvironmentAwarePropertyResolver.Builder<T> resolutionStrategy(
            EnvironmentAwarePropertyResolver.ResolutionStrategy<T> resolutionStrategy) {
        this.resolutionStrategy = resolutionStrategy;
        return this;
    }

    @Override
    public EnvironmentAwarePropertyResolver.Builder<T> property(T property, String... environmentValues) {
        for (String environmentValue : environmentValues) {
            properties.put(environmentValue.toLowerCase(), property);
        }
        return this;
    }

    @Override
    public DefaultEnvironmentAwarePropertyResolver<T> build() throws Exception {
        if (defaultValue == null) {
            // TODO: dedicated exception
            throw new Exception("default value must not be null");
        }
        properties.put("", defaultValue);

        if (local != null) {
            properties.put("local", local);
        }

        if (build != null) {
            properties.put("test", build);
            properties.put("build", build);
        }

        if (qa != null) {
            properties.put("fach", qa);
            properties.put("qa", qa);
        }

        if (prod != null) {
            properties.put("prod", prod);
        }

        return new DefaultEnvironmentAwarePropertyResolver<>(properties,
                resolutionStrategy == null ? new DefaultEnvironmentResolutionStrategy<>() : resolutionStrategy, this);
    }

    public DefaultEnvironmentAwarePropertyResolverBuilder<T> defaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public DefaultEnvironmentAwarePropertyResolverBuilder<T> local(T local) {
        this.local = local;
        return this;
    }

    public DefaultEnvironmentAwarePropertyResolverBuilder<T> build(T build) {
        this.build = build;
        return this;
    }

    public DefaultEnvironmentAwarePropertyResolverBuilder<T> qa(T qa) {
        this.qa = qa;
        return this;
    }

    public DefaultEnvironmentAwarePropertyResolverBuilder<T> prod(T prod) {
        this.prod = prod;
        return this;
    }
}
