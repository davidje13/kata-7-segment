package com.davidje13.collectors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;

public class StreamCollector<T> implements Collector<T, List<T>, Stream<T>> {
	public static <T> StreamCollector<T> toStream() {
		return new StreamCollector<>();
	}

	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (a, b) -> {
			a.addAll(b);
			return a;
		};
	}

	@Override
	public Function<List<T>, Stream<T>> finisher() {
		return Collection::stream;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return emptySet();
	}
}
