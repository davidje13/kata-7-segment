package com.davidje13.digit;

import java.util.ArrayList;
import java.util.Collection;
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

	public int guessSegmentWidth(List<String> lines, int spacing) {
		int height = lines.size();

		if (height < 3 || (height % 2) == 0) {
			throw new IllegalArgumentException("Invalid segment height");
		}

		int width = getLinesWidth(lines);

		for (int cw = 3; cw <= width + 1; ++ cw) {
			if (validateSegmentWidth(lines, cw, spacing)) {
				return cw;
			}
		}
		throw new IllegalArgumentException("No valid segments detected");
	}

	public int countSegments(List<String> lines, int cw, int spacing) {
		return (getLinesWidth(lines) + spacing + cw) / (cw + spacing);
	}

	public EnumSet<Segment7> fromAsciiLines(List<String> lines) {
		int height = lines.size();

		if (height < 3 || (height % 2) == 0) {
			throw new IllegalArgumentException("Invalid segment height");
		}

		int width = getLinesWidth(lines);

		if (width < 3) {
			throw new IllegalArgumentException("Invalid segment width");
		}

		int scaleX = width - 2;
		int scaleY = (height - 1) / 2;

		Collection<Segment7> segments = new ArrayList<>();
		if (isLit(lines, 1, 0)) {
			segments.add(TOP);
		}
		if (isLit(lines, 0, scaleY)) {
			segments.add(TL);
		}
		if (isLit(lines, scaleX + 1, scaleY)) {
			segments.add(TR);
		}
		if (isLit(lines, 1, scaleY)) {
			segments.add(MID);
		}
		if (isLit(lines, 0, scaleY * 2)) {
			segments.add(BL);
		}
		if (isLit(lines, scaleX + 1, scaleY * 2)) {
			segments.add(BR);
		}
		if (isLit(lines, 1, scaleY * 2)) {
			segments.add(BASE);
		}
		return EnumSet.copyOf(segments);
	}

	private boolean validateSegmentWidth(List<String> lines, int cw, int spacing) {
		int height = lines.size();
		int scaleY = (height - 1) / 2;

		Collection<Integer> voidLines = new ArrayList<>();
		for (int n = 0; n < scaleY - 1; ++ n) {
			voidLines.add(n + 1);
			voidLines.add(scaleY + n + 1);
		}

		int n = countSegments(lines, cw, spacing);
		for (int i = 0; i < n; ++ i) {
			int p = i * (cw + spacing);
			for (int j = 0; j < spacing; ++ j) {
				int p2 = p + cw + j;
				for (int y = 0; y < height; ++ y) {
					if (isLit(lines, p2, y)) {
						return false;
					}
				}
			}
			for (int j = 0; j < cw - 2; ++ j) {
				int p2 = p + 1 + j;
				for (int y : voidLines) {
					if (isLit(lines, p2, y)) {
						return false;
					}
				}
			}
			if (isLit(lines, p, 0)) {
				return false;
			}
			if (isLit(lines, p + cw - 1, 0)) {
				return false;
			}
		}
		return true;
	}

	private int getLinesWidth(List<String> lines) {
		return lines.stream()
				.mapToInt(String::length)
				.max().orElse(0);
	}

	private boolean isLit(List<String> lines, int x, int y) {
		String ln = lines.get(y);
		return x < ln.length() && ln.charAt(x) != ' ';
	}
}
