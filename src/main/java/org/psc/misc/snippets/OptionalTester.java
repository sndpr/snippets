package org.psc.misc.snippets;

import java.util.Optional;

public class OptionalTester {

    public static void main(String[] args) {
        Optional<String> valString = Optional.empty();
        final String text = valString.orElse("NoSuchElement");
        System.out.println(text);
    }
}
