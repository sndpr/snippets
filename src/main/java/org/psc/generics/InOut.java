package org.psc.generics;

import java.time.LocalDateTime;
import java.util.List;

public interface InOut<T> {

    List<T> getItems();

    void processItems(List<T> items);

    default void run() {
        processItems(getItems());
    }

    List<T> getPartialItems(LocalDateTime from);

    void processPartialItems(List<T> items);

    default void run(LocalDateTime from) {
        processPartialItems(getPartialItems(from));
    }


}
