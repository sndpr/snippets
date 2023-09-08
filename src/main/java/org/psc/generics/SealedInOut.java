package org.psc.generics;

import java.time.LocalDateTime;
import java.util.List;

public sealed interface SealedInOut<T> permits SealedInOut.Simple, SealedInOut.Complex {

    List<T> getItems();

    void processItems(List<T> items);

    void run();

    abstract non-sealed class Simple<T> implements SealedInOut<T> {

        // prevent manual override of run
        @Override
        public final void run() {
            processItems(getItems());
        }
    }

    abstract non-sealed class Complex<T> implements SealedInOut<T> {

        public abstract List<T> getPartialItems(LocalDateTime from);

        public abstract void processPartialItems(List<T> items);

        @Override
        public final void run() {
            processItems(getItems());
        }

        // prevent manual override of run
        public final void run(LocalDateTime from) {
            processPartialItems(getPartialItems(from));
        }
    }
}
