package org.psc.env;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultEnvironmentAwarePropertyResolverTest {

    @Test
    public void test() throws Exception {
        EnvironmentAwarePropertyResolver<String> urlResolver =
                DefaultEnvironmentAwarePropertyResolver.<String>builder().defaultValue("dummy")
                        .build("abc")
                        .local("dummy")
                        .prod("prod_123")
                        .build();

        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "");
        String local = urlResolver.getProperty();

        assertThat(local, is("dummy"));

        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "pRoD");
        String prod = urlResolver.getProperty();

        assertThat(prod, is("prod_123"));

        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "qa");
        String fallbackToLocal = urlResolver.getProperty();

        assertThat(fallbackToLocal, is("dummy"));

    }

}
