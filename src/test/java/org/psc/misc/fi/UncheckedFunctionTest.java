package org.psc.misc.fi;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UncheckedFunctionTest {

    @Test
    void testUncheckedFunctionException() {
        assertThrows(RuntimeException.class, () -> {
            TestFunction<String, String> testFunction = new TestFunction<>("abc");
            testFunction.apply("err");
        });
    }

    @Test
    void testUncheckedFunction() {
        TestFunction<Integer, String> testFunction = new TestFunction<>("abc");
        String result = testFunction.apply(0);
        assertThat(result).isEqualTo("abc");
    }

    @Test
    void testUncheckedFunction2() {
        UncheckedFunction<Integer, Integer> testFunction = i -> i + 5;
        int result = testFunction.apply(2);
        assertThat(result).isEqualTo(7);
    }

    @Test
    void testAppendXyz(){
        UncheckedFunction<String, String> testFunction = UncheckedFunctionTest::appendXyz;
        String result = testFunction.apply("abc");
        assertThat(result).isEqualTo("abcxyz");
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
