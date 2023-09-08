package org.psc.generics;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class InOutProcessor {

    private final List<InOut<?>> inOuts;

    void processInOuts() {
        inOuts.forEach(InOut::run);
    }

}
