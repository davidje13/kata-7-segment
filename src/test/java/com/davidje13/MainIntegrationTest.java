package com.davidje13;

import com.davidje13.digit.AsciiDisplay;
import com.davidje13.digit.Segment7;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainIntegrationTest {
	private final Segment7 segment = new Segment7();
	private final AsciiDisplay display = new AsciiDisplay();

	private final Main main = new Main(segment, display);

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
	public void main_convertsTheFirstArgumentToAscii_andPrintsToStdOut() {
		String out = getStdOutFrom(() -> Main.main(new String[]{"123"}));

		assertThat(out, equalTo(
				"     _   _ \n" +
				"  |  _|  _|\n" +
				"  | |_   _|\n"
		));
	}

	@Test
	public void main_printsOnlyNewline_ifNoInputGiven() {
		String out = getStdOutFrom(() -> Main.main(new String[]{}));

		assertThat(out, equalTo("\n"));
	}

	private String getStdOutFrom(Runnable runnable) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream stdout = System.out;

		try {
			System.setOut(new PrintStream(out));
			runnable.run();
			return out.toString();
		} finally {
			System.setOut(stdout);
		}
	}
}
