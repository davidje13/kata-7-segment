package com.davidje13;

import com.davidje13.OptionParser.Options;
import com.davidje13.digit.AsciiSegment7Font;
import com.davidje13.digit.Segment7Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.davidje13.collectors.NestedCollector.nested;
import static com.davidje13.collectors.StreamCollector.toStream;
import static com.davidje13.collectors.StringCollector.toCharacterString;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {
	private final Segment7Font font;
	private final AsciiSegment7Font asciiFont;

	Main(Segment7Font font, AsciiSegment7Font asciiFont) {
		this.font = font;
		this.asciiFont = asciiFont;
	}

	String toAsciiSegments(CharSequence value) {
		return value.chars()
				.mapToObj(font::toSegments)
				.map(asciiFont::toAsciiLines)
				.collect(nested(joining(" "), joining("\n")));
	}

	String fromAsciiSegments(List<String> lines) {
		int spacing = 1;
		int cw = asciiFont.guessSegmentWidth(lines, spacing);
		int segCount = asciiFont.countSegments(lines, cw, spacing);

		return lines.stream()
				.map((ln) -> divideLine(ln, cw, spacing, segCount))
				.collect(nested(toList(), toStream()))
				.map(asciiFont::fromAsciiLines)
				.map(font::fromSegments)
				.collect(toCharacterString());
	}

	public static void main(String[] args) {
		Options options = new OptionParser().parseOptions(args);

		Main main = new Main(
				new Segment7Font(),
				new AsciiSegment7Font(options.getScaleX(), options.getScaleY())
		);

		if (options.isReverse()) {
			try {
				List<String> lines = readNonEmptyLines(System.in);
				System.out.println(main.fromAsciiSegments(lines));
			} catch(IOException e) {
				System.err.println("Failed to read input: " + e.getMessage());
			}
		} else {
			System.out.println(main.toAsciiSegments(options.getMessage()));
		}
	}

	private List<String> divideLine(String ln, int cw, int spacing, int count) {
		int length = ln.length();

		List<String> result = new ArrayList<>();
		for (int i = 0; i < count; ++ i) {
			int p = i * (cw + spacing);
			if (p >= length) {
				result.add("");
			} else if (p + cw > length) {
				result.add(ln.substring(p));
			} else {
				result.add(ln.substring(p, p + cw));
			}
		}
		return result;
	}

	private static List<String> readNonEmptyLines(InputStream stream) throws IOException {
		try (
				Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
				BufferedReader bufferedReader = new BufferedReader(reader)
		) {
			return bufferedReader.lines()
					.filter((ln) -> !ln.isEmpty())
					.collect(toList());
		}
	}
}
