package com.davidje13.digit;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.davidje13.digit.Segments.BASE;
import static com.davidje13.digit.Segments.BL;
import static com.davidje13.digit.Segments.BR;
import static com.davidje13.digit.Segments.MID;
import static com.davidje13.digit.Segments.TL;
import static com.davidje13.digit.Segments.TOP;
import static com.davidje13.digit.Segments.TR;

public class Segment7 {
	private static final EnumSet<Segments> BLANK = EnumSet.noneOf(Segments.class);
	private static final Map<Character, EnumSet<Segments>> CHAR_LOOKUP = new HashMap<>();

	static {
		CHAR_LOOKUP.put('0', EnumSet.of(TOP, TL, TR, BL, BR, BASE));
		CHAR_LOOKUP.put('1', EnumSet.of(TR, BR));
		CHAR_LOOKUP.put('2', EnumSet.of(TOP, TR, MID, BL, BASE));
		CHAR_LOOKUP.put('3', EnumSet.of(TOP, TR, MID, BR, BASE));
		CHAR_LOOKUP.put('4', EnumSet.of(TOP, TL, TR, MID, BR));
		CHAR_LOOKUP.put('5', EnumSet.of(TOP, TL, MID, BR, BASE));
		CHAR_LOOKUP.put('6', EnumSet.of(TOP, TL, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('7', EnumSet.of(TOP, TR, BR));
		CHAR_LOOKUP.put('8', EnumSet.of(TOP, TL, TR, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('9', EnumSet.of(TOP, TL, TR, MID, BR, BASE));
		CHAR_LOOKUP.put('-', EnumSet.of(MID));
	}

	public EnumSet<Segments> toSegments(char c) {
		return CHAR_LOOKUP.getOrDefault(c, BLANK);
	}

	public Stream<EnumSet<Segments>> toSegments(CharSequence value) {
		return value.chars()
				.mapToObj((c) -> this.toSegments((char) c));
	}
}
