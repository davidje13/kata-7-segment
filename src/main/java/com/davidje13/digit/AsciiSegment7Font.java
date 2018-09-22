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
import static java.util.Collections.nCopies;

public class AsciiSegment7Font {
	private final int scaleY;

	private final String horiz_on;
	private final String horiz_space;

	public AsciiSegment7Font(int scaleX, int scaleY) {
		this.scaleY = scaleY;
		this.horiz_on = String.join("", nCopies(scaleX, "_"));
		this.horiz_space = String.join("", nCopies(scaleX, " "));
	}

	public List<String> toAsciiLines(EnumSet<Segment7> segments) {
		String top  = segments.contains(TOP)  ? horiz_on : horiz_space;
		String tl   = segments.contains(TL)   ? "|" : " ";
		String tr   = segments.contains(TR)   ? "|" : " ";
		String mid  = segments.contains(MID)  ? horiz_on : horiz_space;
		String bl   = segments.contains(BL)   ? "|" : " ";
		String br   = segments.contains(BR)   ? "|" : " ";
		String base = segments.contains(BASE) ? horiz_on : horiz_space;

		List<String> result = new ArrayList<>();
		result.add(" " + top + " ");

		for (int i = 0; i < scaleY - 1; ++ i) {
			result.add(tl + horiz_space + tr);
		}

		result.add(tl + mid + tr);

		for (int i = 0; i < scaleY - 1; ++ i) {
			result.add(bl + horiz_space + br);
		}

		result.add(bl + base + br);
		return result;
	}
}
