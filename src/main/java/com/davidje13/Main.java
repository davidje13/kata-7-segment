package com.davidje13;

import com.davidje13.OptionParser.Options;
import com.davidje13.digit.AsciiSegment7Font;
import com.davidje13.digit.Segment7Font;

import java.util.List;

import static com.davidje13.collectors.NestedCollector.nested;
import static java.util.stream.Collectors.joining;

public class Main {
	private final Segment7Font font;
	private final AsciiSegment7Font asciiFont;

	Main(Segment7Font font, AsciiSegment7Font asciiFont) {
		this.font = font;
		this.asciiFont = asciiFont;
	}

	String toAsciiSegments(CharSequence value) {
		List<String> lines = value.chars()
				.mapToObj(font::toSegments)
				.map(asciiFont::toAsciiLines)
				.collect(nested(joining(" ")));
		return String.join("\n", lines);
	}

	public static void main(String[] args) {
		Options options = new OptionParser().parseOptions(args);

		Main main = new Main(
				new Segment7Font(),
				new AsciiSegment7Font(options.getScaleX(), options.getScaleY())
		);

		System.out.println(main.toAsciiSegments(options.getMessage()));
	}
}
