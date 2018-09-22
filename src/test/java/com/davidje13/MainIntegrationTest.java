package com.davidje13;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainIntegrationTest {
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
	public void main_joinsAllArgumentsWithSpaces() {
		String out = getStdOutFrom(() -> Main.main(new String[]{"1", "3"}));

		assertThat(out, equalTo(
				"         _ \n" +
				"  |      _|\n" +
				"  |      _|\n"
		));
	}

	@Test
	public void main_usesFlagsToConfigureSize() {
		String out = getStdOutFrom(() -> Main.main(new String[]{"--size=3x2", "8"}));

		assertThat(out, equalTo(
				" ___ \n" +
				"|   |\n" +
				"|___|\n" +
				"|   |\n" +
				"|___|\n"
		));
	}

	@Test
	public void main_supportsLetters() {
		String out = getStdOutFrom(() -> Main.main(new String[]{"hi"}));

		assertThat(out, equalTo(
				"       \n" +
				"|_     \n" +
				"| |   |\n"
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
