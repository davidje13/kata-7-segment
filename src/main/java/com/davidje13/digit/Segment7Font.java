package com.davidje13.digit;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;

public class Segment7Font {
	private static final EnumSet<Segment7> BLANK = EnumSet.noneOf(Segment7.class);
	private static final Map<Character, EnumSet<Segment7>> CHAR_LOOKUP = new HashMap<>();
	private static final Map<EnumSet<Segment7>, Character> REVERSE_LOOKUP = new HashMap<>();

	static {
		CHAR_LOOKUP.put(' ', BLANK);
		CHAR_LOOKUP.put('0', EnumSet.of(TOP, TL, TR, BL, BR, BASE));
		CHAR_LOOKUP.put('1', EnumSet.of(TR, BR));
		CHAR_LOOKUP.put('2', EnumSet.of(TOP, TR, MID, BL, BASE));
		CHAR_LOOKUP.put('3', EnumSet.of(TOP, TR, MID, BR, BASE));
		CHAR_LOOKUP.put('4', EnumSet.of(TL, TR, MID, BR));
		CHAR_LOOKUP.put('5', EnumSet.of(TOP, TL, MID, BR, BASE));
		CHAR_LOOKUP.put('6', EnumSet.of(TOP, TL, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('7', EnumSet.of(TOP, TR, BR));
		CHAR_LOOKUP.put('8', EnumSet.of(TOP, TL, TR, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('9', EnumSet.of(TOP, TL, TR, MID, BR, BASE));
		CHAR_LOOKUP.put('^', EnumSet.of(TOP));
		CHAR_LOOKUP.put('-', EnumSet.of(MID));
		CHAR_LOOKUP.put('_', EnumSet.of(BASE));
		CHAR_LOOKUP.put('=', EnumSet.of(MID, BASE));
		CHAR_LOOKUP.put('a', EnumSet.of(TOP, TL, TR, MID, BL, BR));
		CHAR_LOOKUP.put('b', EnumSet.of(TL, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('c', EnumSet.of(MID, BL, BASE));
		CHAR_LOOKUP.put('d', EnumSet.of(TR, MID, BL, BR, BASE));
		CHAR_LOOKUP.put('e', EnumSet.of(TOP, TL, MID, BL, BASE));
		CHAR_LOOKUP.put('f', EnumSet.of(TOP, TL, MID, BL));
		CHAR_LOOKUP.put('g', EnumSet.of(TOP, TL, BL, BR, BASE));
		CHAR_LOOKUP.put('h', EnumSet.of(TL, MID, BL, BR));
		CHAR_LOOKUP.put('i', EnumSet.of(BR));
		CHAR_LOOKUP.put('j', EnumSet.of(TR, BR, BASE));
		CHAR_LOOKUP.put('k', EnumSet.of(TL, TR, MID, BL, BR));
		CHAR_LOOKUP.put('l', EnumSet.of(TL, BL, BASE));
		CHAR_LOOKUP.put('m', EnumSet.of(TOP, TL, TR, BL, BR));
		CHAR_LOOKUP.put('n', EnumSet.of(MID, BL, BR));
		CHAR_LOOKUP.put('o', EnumSet.of(MID, BL, BR, BASE));
		CHAR_LOOKUP.put('p', EnumSet.of(TOP, TL, TR, MID, BL));
		CHAR_LOOKUP.put('q', EnumSet.of(TOP, TL, TR, MID, BR));
		CHAR_LOOKUP.put('r', EnumSet.of(MID, BL));
		CHAR_LOOKUP.put('s', EnumSet.of(TOP, TL, MID, BR, BASE));
		CHAR_LOOKUP.put('t', EnumSet.of(TL, MID, BL, BASE));
		CHAR_LOOKUP.put('u', EnumSet.of(TL, TR, BL, BR, BASE));
		CHAR_LOOKUP.put('v', EnumSet.of(BL, BR, BASE));
		CHAR_LOOKUP.put('w', EnumSet.of(TL, TR, BL, BR, BASE));
		CHAR_LOOKUP.put('x', EnumSet.of(TL, TR, MID, BL, BR));
		CHAR_LOOKUP.put('y', EnumSet.of(TL, TR, MID, BR, BASE));
		CHAR_LOOKUP.put('z', EnumSet.of(TOP, TR, MID, BL, BASE));

		CHAR_LOOKUP.forEach((c, s) -> REVERSE_LOOKUP.putIfAbsent(s, c));
	}

	public EnumSet<Segment7> toSegments(char c) {
		return CHAR_LOOKUP.getOrDefault(Character.toLowerCase(c), BLANK);
	}

	public EnumSet<Segment7> toSegments(int c) {
		return toSegments((char) c);
	}

	public char fromSegments(EnumSet<Segment7> segments) {
		return REVERSE_LOOKUP.getOrDefault(segments, '\uFFFD');
	}
}
