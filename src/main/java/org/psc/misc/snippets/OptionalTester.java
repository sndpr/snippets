package org.psc.misc.snippets;

import java.util.Optional;

public class OptionalTester {
	
	private static Optional<String> val;
	
	public static void main(String[] args) {
		val = Optional.empty();
		final String text = val.orElse("NoSuchElement");
		System.out.println(text);
	}
}
