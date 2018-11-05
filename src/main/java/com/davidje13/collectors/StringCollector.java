package com.davidje13.collectors;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Collections.emptySet;

public class StringCollector implements Collector<Character, StringBuilder, String> {
	public static StringCollector toCharacterString() {
		return new StringCollector();
	}

	@Override
	public Supplier<StringBuilder> supplier() {
		return StringBuilder::new;
	}

	@Override
	public BiConsumer<StringBuilder, Character> accumulator() {
		return StringBuilder::append;
	}

	@Override
	public BinaryOperator<StringBuilder> combiner() {
		return StringBuilder::append;
	}

	@Override
	public Function<StringBuilder, String> finisher() {
		return StringBuilder::toString;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return emptySet();
	}
}
