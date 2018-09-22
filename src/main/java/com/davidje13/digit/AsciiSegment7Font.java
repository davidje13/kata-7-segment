package com.davidje13.digit;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;

public class AsciiSegment7Font {
	private final int scaleX;
	private final int scaleY;

	public AsciiSegment7Font(int scaleX, int scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public List<String> toAsciiLines(EnumSet<Segment7> segments) {
		String topRep = ifPresent(segments, TL, "|") + rep(" ", scaleX) + ifPresent(segments, TR, "|");
		String baseRep = ifPresent(segments, BL, "|") + rep(" ", scaleX) + ifPresent(segments, BR, "|");

		List<String> result = new ArrayList<>();
		result.add(" " + rep(ifPresent(segments, TOP, "_"), scaleX) + " ");
		for (int i = 0; i < scaleY - 1; ++ i) {
			result.add(topRep);
		}
		result.add(ifPresent(segments, TL, "|") + rep(ifPresent(segments, MID, "_"), scaleX) + ifPresent(segments, TR, "|"));
		for (int i = 0; i < scaleY - 1; ++ i) {
			result.add(baseRep);
		}
		result.add(ifPresent(segments, BL, "|") + rep(ifPresent(segments, BASE, "_"), scaleX) + ifPresent(segments, BR, "|"));
		return result;
	}

	private String ifPresent(EnumSet<Segment7> segments, Segment7 segment, String value) {
		return segments.contains(segment) ? value : " ";
	}

	private String rep(String part, int quantity) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < quantity; ++ i) {
			builder.append(part);
		}
		return builder.toString();
	}
}
