package com.davidje13.digit;

import java.util.EnumSet;
import java.util.List;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;
import static java.util.Arrays.asList;

public class AsciiSegment7Font {
	public List<String> toAsciiLines(EnumSet<Segment7> segments) {
		return asList(
				" " + ifPresent(segments, TOP, "_") + " ",
				ifPresent(segments, TL, "|") + ifPresent(segments, MID, "_") + ifPresent(segments, TR, "|"),
				ifPresent(segments, BL, "|") + ifPresent(segments, BASE, "_") + ifPresent(segments, BR, "|")
		);
	}

	private String ifPresent(EnumSet<Segment7> segments, Segment7 segment, String value) {
		return segments.contains(segment) ? value : " ";
	}
}
