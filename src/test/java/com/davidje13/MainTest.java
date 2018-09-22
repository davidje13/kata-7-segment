package com.davidje13;

import com.davidje13.digit.AsciiDisplay;
import com.davidje13.digit.Segment7Font;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainTest {
	private final Segment7Font font = new Segment7Font();
	private final AsciiDisplay display = new AsciiDisplay();

	private final Main main = new Main(font, display);

	@Test
	public void toAsciiSegments_convertsTheInputStringIntoAsciiSegments() {
		String ascii = main.toAsciiSegments("12-01");

		assertThat(ascii, equalTo(
				"     _       _     \n" +
				"  |  _|  _  | |   |\n" +
				"  | |_      |_|   |"
		));
	}
}
