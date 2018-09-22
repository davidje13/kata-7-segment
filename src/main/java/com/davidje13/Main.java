package com.davidje13;

import com.davidje13.digit.AsciiDisplay;
import com.davidje13.digit.Segment7;
import com.davidje13.digit.Segments;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	private final Segment7 segment;
	private final AsciiDisplay display;

	Main(Segment7 segment, AsciiDisplay display) {
		this.segment = segment;
		this.display = display;
	}

	String toAsciiSegments(CharSequence value) {
		Stream<EnumSet<Segments>> segments = segment.toSegments(value);
		List<String> lines = display.toAsciiLines(segments, " ");
		return display.flattenAsciiLines(lines);
	}

	public static void main(String[] args) {
		Main main = new Main(
				new Segment7(),
				new AsciiDisplay()
		);
		String value = (args.length > 0) ? args[0] : "";
		System.out.println(main.toAsciiSegments(value));
	}
}
