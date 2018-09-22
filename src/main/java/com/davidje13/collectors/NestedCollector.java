package com.davidje13.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

public class NestedCollector<T, A, I, R> implements Collector<List<? extends T>, List<A>, R> {
	private final Collector<T, A, I> subCollector;
	private final Collector<? super I, ?, R> secondaryCollector;

	public static <T, I, R> NestedCollector<T, ?, ?, R> nested(
			Collector<T, ?, I> subCollector,
			Collector<? super I, ?, R> secondaryCollector
	) {
		return new NestedCollector<>(subCollector, secondaryCollector);
	}

	public static <T, R> NestedCollector<T, ?, ?, List<R>> nested(
			Collector<T, ?, R> subCollector
	) {
		return nested(subCollector, toList());
	}

	private NestedCollector(
			Collector<T, A, I> subCollector,
			Collector<? super I, ?, R> secondaryCollector
	) {
		this.subCollector = subCollector;
		this.secondaryCollector = secondaryCollector;
	}

	@Override
	public Supplier<List<A>> supplier() {
		return ArrayList::new;
	}

	private void accumulate(List<A> acc, List<? extends T> element) {
		if (acc.isEmpty() && !element.isEmpty()) {
			Supplier<A> subSupplier = subCollector.supplier();
			for (int i = 0; i < element.size(); ++ i) {
				acc.add(subSupplier.get());
			}
		}
		if (acc.size() != element.size()) {
			throw new IllegalArgumentException("Mismatched collection sizes");
		}
		BiConsumer<A, T> subAccumulator = subCollector.accumulator();
		for (int i = 0; i < element.size(); ++ i) {
			subAccumulator.accept(acc.get(i), element.get(i));
		}
	}

	@Override
	public BiConsumer<List<A>, List<? extends T>> accumulator() {
		return this::accumulate;
	}

	private List<A> combine(List<A> acc1, List<A> acc2) {
		if (acc2.isEmpty()) {
			return acc1;
		}
		if (acc1.isEmpty()) {
			return acc2;
		}
		if (acc1.size() != acc2.size()) {
			throw new IllegalArgumentException("Mismatched collection sizes");
		}
		BinaryOperator<A> subCombiner = subCollector.combiner();
		for (int i = 0; i < acc1.size(); ++ i) {
			acc1.set(i, subCombiner.apply(acc1.get(i), acc2.get(i)));
		}
		return acc1;
	}

	@Override
	public BinaryOperator<List<A>> combiner() {
		return this::combine;
	}

	private R finish(List<A> acc) {
		return acc.stream()
				.map(subCollector.finisher())
				.collect(secondaryCollector);
	}

	@Override
	public Function<List<A>, R> finisher() {
		return this::finish;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return emptySet();
	}
}
