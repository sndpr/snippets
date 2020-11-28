package org.psc.env;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultEnvironmentAwarePropertyResolverTest {

    @Test
    void testLocal() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "");
        EnvironmentAwarePropertyResolver<String> urlResolver =
                DefaultEnvironmentAwarePropertyResolver.<String>builder().defaultValue("dummyDefault")
                        .build("abc")
                        .local("dummy")
                        .prod("prod_123")
                        .build();


        String local = urlResolver.getProperty();

        assertThat(local).isEqualTo("dummyDefault");
    }

    @Test
    void testProd() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "pRoD");
        EnvironmentAwarePropertyResolver<String> urlResolver = DefaultEnvironmentAwarePropertyResolver.<String>builder()
                .defaultValue("dummyDefault")
                .build("abc")
                .local("dummy")
                .prod("prod_123")
                .build();

        String prod = urlResolver.getProperty();

        assertThat(prod).isEqualTo("prod_123");
    }

    @Test
    void testDefaultFallback() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "qa");
        EnvironmentAwarePropertyResolver<String> urlResolver = DefaultEnvironmentAwarePropertyResolver.<String>builder()
                .defaultValue("dummyDefault")
                .build("abc")
                .local("dummy")
                .prod("prod_123")
                .build();

        String prod = urlResolver.getProperty();

        assertThat(prod).isEqualTo("dummyDefault");
    }

}
