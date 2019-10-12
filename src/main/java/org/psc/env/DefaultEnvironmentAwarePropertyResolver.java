package org.psc.env;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.util.Map;

@Data
@With
@RequiredArgsConstructor
public class DefaultEnvironmentAwarePropertyResolver<T> implements EnvironmentAwarePropertyResolver<T> {

    private final Map<String, T> properties;

    private final EnvironmentResolutionStrategy<T> resolutionStrategy;

    private final DefaultEnvironmentAwarePropertyResolverBuilder<T> builder;

    @Override
    public Map<String, T> getProperties() {
        return properties;
    }

    @Override
    public EnvironmentResolutionStrategy<T> getStrategy() {
        return resolutionStrategy;
    }

    public static <T> DefaultEnvironmentAwarePropertyResolverBuilder<T> builder(){
        return new DefaultEnvironmentAwarePropertyResolverBuilder<>();
    }


}
