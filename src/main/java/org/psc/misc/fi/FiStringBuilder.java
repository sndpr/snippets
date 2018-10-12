package org.psc.misc.fi;

import java.util.stream.Stream;

public class FiStringBuilder implements StringBuilderFi {

	private StringBuilder stringBuilder;

	public FiStringBuilder() {
		stringBuilder = new StringBuilder();
	}

	public FiStringBuilder(int initialSize) {
		stringBuilder = new StringBuilder(initialSize);
	}

	@Override
	public String apply(String... strings) {
		Stream.of(strings).forEach(stringBuilder::append);
		return stringBuilder.toString();
	}

}
