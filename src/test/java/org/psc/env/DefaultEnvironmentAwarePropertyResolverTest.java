package org.psc.env;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultEnvironmentAwarePropertyResolverTest {

    @Test
    public void test() {
        DefaultEnvironmentAwarePropertyResolver<String> urlResolver =
                DefaultEnvironmentAwarePropertyResolver.<String>builder().build("abc")
                        .qa("qqqq")
                        .local("dummy")
                        .prod("prod_123")
                        .build();

        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "");
        String local = urlResolver.getProperty();

        assertThat(local, is("dummy"));

        System.setProperty(DefaultEnvironmentResolutionStrategy.ENVIRONMENT_PROPERTY_KEY, "prod");
        String prod = urlResolver.getProperty();

        assertThat(prod, is("prod_123"));
    }

}
