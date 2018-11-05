package com.davidje13;

import com.davidje13.testutil.IntegrationTestUtils.Output;
import org.junit.Test;

import static com.davidje13.testutil.IntegrationTestUtils.getOutputFrom;
import static com.davidje13.testutil.IntegrationTestUtils.setStdInContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainIntegrationTest {
	@Test
	public void main_convertsTheFirstArgumentToAscii_andPrintsToStdOut() {
		Output output = getOutputFrom(() -> Main.main(new String[]{"123"}));

		assertThat(output.out, equalTo(
				"     _   _ \n" +
				"  |  _|  _|\n" +
				"  | |_   _|\n"
		));
		assertThat(output.err, equalTo(""));
	}

	@Test
	public void main_joinsAllArgumentsWithSpaces() {
		Output output = getOutputFrom(() -> Main.main(new String[]{"1", "3"}));

		assertThat(output.out, equalTo(
				"         _ \n" +
				"  |      _|\n" +
				"  |      _|\n"
		));
	}

	@Test
	public void main_usesFlagsToConfigureSize() {
		Output output = getOutputFrom(() -> Main.main(new String[]{"--size=3x2", "8"}));

		assertThat(output.out, equalTo(
				" ___ \n" +
				"|   |\n" +
				"|___|\n" +
				"|   |\n" +
				"|___|\n"
		));
	}

	@Test
	public void main_supportsLetters() {
		Output output = getOutputFrom(() -> Main.main(new String[]{"hi"}));

		assertThat(output.out, equalTo(
				"       \n" +
				"|_     \n" +
				"| |   |\n"
		));
	}

	@Test
	public void main_printsOnlyNewline_ifNoInputGiven() {
		Output output = getOutputFrom(() -> Main.main(new String[]{}));

		assertThat(output.out, equalTo("\n"));
	}

	@Test
	public void main_reversesFromStdInIfRequested() {
		setStdInContent(
				"     _   _ \n" +
				"  |  _|  _|\n" +
				"  | |_   _|\n"
		);
		Output output = getOutputFrom(() -> Main.main(new String[]{"--reverse"}));

		assertThat(output.out, equalTo("123\n"));
		assertThat(output.err, equalTo(""));
	}

	@Test
	public void main_reverse_ignoresBlankLines() {
		setStdInContent(
				"\n" +
				"     _   _ \n" +
				"  |  _|  _|\n" +
				"  | |_   _|"
		);
		Output output = getOutputFrom(() -> Main.main(new String[]{"--reverse"}));

		assertThat(output.out, equalTo("123\n"));
		assertThat(output.err, equalTo(""));
	}

	@Test
	public void main_reverse_allowsMissingFinalNewline() {
		setStdInContent(
				"     _   _ \n" +
				"  |  _|  _|\n" +
				"  | |_   _|"
		);
		Output output = getOutputFrom(() -> Main.main(new String[]{"--reverse"}));

		assertThat(output.out, equalTo("123\n"));
		assertThat(output.err, equalTo(""));
	}
}
