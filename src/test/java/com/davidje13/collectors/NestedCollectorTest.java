package com.davidje13.collectors;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.davidje13.collectors.NestedCollector.nested;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.summingInt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class NestedCollectorTest {
	@Test
	public void nestedCollector_usesSubCollectorToCombineValuesInGivenList() {
		Stream<List<Integer>> values = Stream.of(
				asList(1, 2, 3),
				asList(4, 5, 6),
				asList(7, 8, 9)
		);

		List<Integer> collected = values.collect(nested(summingInt((v) -> v)));

		assertThat(collected, contains(
				1 + 4 + 7,
				2 + 5 + 8,
				3 + 6 + 9
		));
	}

	@Test
	public void nestedCollector_usesSecondaryCollectorToCombineFinalValues() {
		Stream<List<Integer>> values = Stream.of(
				asList(1, 4),
				asList(2, 5),
				asList(3, 6)
		);

		double collected = values.collect(nested(
				summingInt((v) -> v),
				averagingInt((v) -> v)
		));

		assertThat(collected, equalTo(((1 + 2 + 3) + (4 + 5 + 6)) / 2.0));
	}

	@Test
	public void nestedCollector_returnsEmptyList_ifNoInputGiven() {
		Stream<List<Object>> values = Stream.empty();

		List<Long> collected = values.collect(nested(counting()));

		assertThat(collected, hasSize(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nestedCollector_throwsIfSizesVary_lower() {
		Stream<List<Integer>> values = Stream.of(
				asList(1, 2, 3),
				asList(4, 5)
		);

		values.collect(nested(counting()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nestedCollector_throwsIfSizesVary_higher() {
		Stream<List<Integer>> values = Stream.of(
				asList(1, 2),
				asList(4, 5, 6)
		);

		values.collect(nested(counting()));
	}
}
