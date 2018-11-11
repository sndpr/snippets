package org.psc.misc.snippets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Misc {

    public <T> void processArray(T[] array) {
        for (T elem : array) {
            log.info(String.valueOf(elem.hashCode()));
        }
    }

}
