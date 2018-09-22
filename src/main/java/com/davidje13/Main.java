package com.davidje13;

import com.davidje13.digit.AsciiDisplay;
import com.davidje13.digit.Segment7;
import com.davidje13.digit.Segments;

import java.util.EnumSet;
import java.util.List;

public class Main {
	private final Segment7 segment;
	private final AsciiDisplay display;

	Main(Segment7 segment, AsciiDisplay display) {
		this.segment = segment;
		this.display = display;
	}

	String toAsciiSegments(CharSequence value) {
		List<EnumSet<Segments>> segments = segment.toSegments(value);
		List<String> lines = display.toAsciiLines(segments, " ");
		return display.flattenAsciiLines(lines);
	}

	public static void main(String[] args) {
		Main main = new Main(
				new Segment7(),
				new AsciiDisplay()
		);
		System.out.println(main.toAsciiSegments(args[0]));
	}
}
