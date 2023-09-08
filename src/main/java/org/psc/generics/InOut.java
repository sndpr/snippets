package org.psc.generics;

import java.util.List;

public interface InOut<T> {

    List<T> getItems();

    void processItems(List<T> items);

}
