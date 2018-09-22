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

public class NestedCollector<T, A, R> implements Collector<List<? extends T>, List<A>, List<R>> {
	private final Collector<T, A, R> subCollector;

	public static <T, R> NestedCollector<T, ?, R> nested(Collector<T, ?, R> subCollector) {
		return new NestedCollector<>(subCollector);
	}

	private NestedCollector(Collector<T, A, R> subCollector) {
		this.subCollector = subCollector;
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

	private List<R> finish(List<A> acc) {
		return acc.stream()
				.map(subCollector.finisher())
				.collect(toList());
	}

	@Override
	public Function<List<A>, List<R>> finisher() {
		return this::finish;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return emptySet();
	}
}
