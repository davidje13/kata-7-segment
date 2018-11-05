package com.davidje13;

import com.davidje13.digit.AsciiSegment7Font;
import com.davidje13.digit.Segment7Font;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainTest {
	private final Segment7Font font = new Segment7Font();
	private final AsciiSegment7Font asciiFont = new AsciiSegment7Font(1, 1);

	private final Main main = new Main(font, asciiFont);

	@Test
	public void toAsciiSegments_convertsTheInputStringIntoAsciiSegments() {
		String ascii = main.toAsciiSegments("12-01");

		assertThat(ascii, equalTo(
				"     _       _     \n" +
				"  |  _|  _  | |   |\n" +
				"  | |_      |_|   |"
		));
	}

	@Test
	public void fromAsciiSegments_convertsTheInputSegmentsToAsciiText() {
		String ascii = main.fromAsciiSegments(asList(
				"     _       _     ",
				"  |  _|  _  | |   |",
				"  | |_      |_|   |"
		));

		assertThat(ascii, equalTo("12-01"));
	}

	@Test
	public void fromAsciiSegments_assumesWhitespaceIfLinesAreShort() {
		String ascii = main.fromAsciiSegments(asList(
				"     _       _",
				"  |  _|  _  | |   |",
				"  | |_      |_|   |"
		));

		assertThat(ascii, equalTo("12-01"));
	}
}
