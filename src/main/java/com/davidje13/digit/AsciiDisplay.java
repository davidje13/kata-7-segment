package com.davidje13.digit;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AsciiDisplay {
	public List<String> toAsciiLines(EnumSet<Segment7> segments) {
		return asList(
				" " + ifPresent(segments, TOP, "_") + " ",
				ifPresent(segments, TL, "|") + ifPresent(segments, MID, "_") + ifPresent(segments, TR, "|"),
				ifPresent(segments, BL, "|") + ifPresent(segments, BASE, "_") + ifPresent(segments, BR, "|")
		);
	}

	public List<String> toAsciiLines(Stream<EnumSet<Segment7>> segments, CharSequence divider) {
		List<List<String>> chars = segments
				.map(this::toAsciiLines)
				.collect(toList());
		return joinAsciiLines(chars, divider);
	}

	private String ifPresent(EnumSet<Segment7> segments, Segment7 segment, String value) {
		return segments.contains(segment) ? value : " ";
	}

	public List<String> joinAsciiLines(List<List<String>> parts, CharSequence divider) {
		if (parts.isEmpty()) {
			return emptyList();
		}

		int size = parts.get(0).size();
		if (parts.stream().anyMatch((part) -> part.size() != size)) {
			throw new IllegalArgumentException("Inconsistent character heights");
		}
		return IntStream.range(0, size)
				.mapToObj((ln) -> parts.stream()
						.map((part) -> part.get(ln))
						.collect(joining(divider)))
				.collect(toList());
	}

	public String flattenAsciiLines(List<String> lines) {
		return String.join("\n", lines);
	}
}
