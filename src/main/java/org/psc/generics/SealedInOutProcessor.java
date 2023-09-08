package org.psc.generics;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class SealedInOutProcessor {

    private final List<SealedInOut<?>> inOuts;

    void processInOuts() {
        inOuts.forEach(SealedInOut::run);
        inOuts.stream()
                .filter(it -> it instanceof SealedInOut.Complex<?>)
                .forEach(it -> ((SealedInOut.Complex<?>) it).run(LocalDateTime.now()));
    }

}
