package org.psc.misc.fi;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UncheckedFunctionTest {

    @Test(expected = RuntimeException.class)
    public void testUncheckedFunctionException() {
        TestFunction<String, String> testFunction = new TestFunction<>("abc");
        testFunction.apply("err");
    }

    @Test
    public void testUncheckedFunction() {
        TestFunction<Integer, String> testFunction = new TestFunction<>("abc");
        String result = testFunction.apply(0);
        assertThat(result, is("abc"));
    }

    @Test
    public void testUncheckedFunction2() {
        UncheckedFunction<Integer, Integer> testFunction = i -> i + 5;
        int result = testFunction.apply(2);
        assertThat(result, is(7));
    }

    @Test
    public void testAppendXyz(){
        UncheckedFunction<String, String> testFunction = UncheckedFunctionTest::appendXyz;
        String result = testFunction.apply("abc");
        assertThat(result, is("abcxyz"));
    }

    private static String appendXyz(String base) throws Exception{
        if (base == null){
            throw new Exception("null parameter not allowed");
        }
        return base.concat("xyz");
    }

    private static class TestFunction<T, U> implements UncheckedFunction<T, U> {

        private U u;

        TestFunction(U u) {
            this.u = u;
        }

        @Override
        public U uncheckedApply(T t) throws Exception {
            if (t instanceof String) {
                throw new Exception("String Type not allowed");
            } else {
                return u;
            }
        }
    }
}
