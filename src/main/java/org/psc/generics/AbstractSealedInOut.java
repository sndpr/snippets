package org.psc.generics;

import java.time.LocalDateTime;
import java.util.List;

public abstract sealed class AbstractSealedInOut<T> permits AbstractSealedInOut.Simple, AbstractSealedInOut.Complex {

    public abstract List<T> getItems();

    public abstract void processItems(List<T> items);

    public final void run(){
        processItems(getItems());
    }

    abstract non-sealed class Simple<T> extends AbstractSealedInOut<T> {
    }

    abstract non-sealed class Complex<T> extends AbstractSealedInOut<T> {

        public abstract List<T> getPartialItems(LocalDateTime from);

        public abstract void processPartialItems(List<T> items);

        public final void run(LocalDateTime from) {
            processPartialItems(getPartialItems(from));
        }
    }
}
