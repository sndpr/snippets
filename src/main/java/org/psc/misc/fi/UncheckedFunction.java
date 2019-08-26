package org.psc.misc.fi;

import java.util.function.Function;

@FunctionalInterface
public interface UncheckedFunction<T, U> extends Function<T, U> {

    @Override
    default U apply(T t) {
        try {
            return uncheckedApply(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    U uncheckedApply(T t) throws Exception;

}
