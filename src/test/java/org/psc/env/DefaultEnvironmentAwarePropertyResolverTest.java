package org.psc.env;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultEnvironmentAwarePropertyResolverTest {

    @Test
    public void testLocal() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "");
        EnvironmentAwarePropertyResolver<String> urlResolver =
                DefaultEnvironmentAwarePropertyResolver.<String>builder().defaultValue("dummyDefault")
                        .build("abc")
                        .local("dummy")
                        .prod("prod_123")
                        .build();


        String local = urlResolver.getProperty();

        assertThat(local, is("dummyDefault"));
    }

    @Test
    public void testProd() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "pRoD");
        EnvironmentAwarePropertyResolver<String> urlResolver = DefaultEnvironmentAwarePropertyResolver.<String>builder()
                .defaultValue("dummyDefault")
                .build("abc")
                .local("dummy")
                .prod("prod_123")
                .build();

        String prod = urlResolver.getProperty();

        assertThat(prod, is("prod_123"));
    }

    @Test
    public void testDefaultFallback() throws Exception {
        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "qa");
        EnvironmentAwarePropertyResolver<String> urlResolver = DefaultEnvironmentAwarePropertyResolver.<String>builder()
                .defaultValue("dummyDefault")
                .build("abc")
                .local("dummy")
                .prod("prod_123")
                .build();

        String prod = urlResolver.getProperty();

        assertThat(prod, is("dummyDefault"));
    }

}
