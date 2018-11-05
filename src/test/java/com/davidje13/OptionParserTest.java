package com.davidje13;

import com.davidje13.OptionParser.Options;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class OptionParserTest {
	private final OptionParser parser = new OptionParser();

	@Test
	public void parseOptions_combinesAllNonFlagOptions() {
		Options options = parser.parseOptions(new String[]{"a", "b"});

		assertThat(options.getMessage(), equalTo("a b"));
	}

	@Test
	public void parseOptions_doesNotIncludeFlagsInMessage() {
		Options options = parser.parseOptions(new String[]{"--size=1", "--"});

		assertThat(options.getMessage(), equalTo(""));
	}

	@Test
	public void parseOptions_recognisesOptionsAnywhereUntilDoubleDash() {
		Options options = parser.parseOptions(new String[]{"a", "--size=1", "b", "--", "c", "--size=1"});

		assertThat(options.getMessage(), equalTo("a b c --size=1"));
	}

	@Test
	public void parseOptions_providesDefaultsForFlags() {
		Options options = parser.parseOptions(new String[]{"a"});

		assertThat(options.getScaleX(), equalTo(1));
		assertThat(options.getScaleY(), equalTo(1));
		assertThat(options.isReverse(), equalTo(false));
	}

	@Test
	public void parseOptions_parsesSizeFlagWithOneValue() {
		Options options = parser.parseOptions(new String[]{"--size=2"});

		assertThat(options.getScaleX(), equalTo(2));
		assertThat(options.getScaleY(), equalTo(2));
	}

	@Test
	public void parseOptions_parsesSizeFlagWithTwoValues() {
		Options options = parser.parseOptions(new String[]{"--size=2x3"});

		assertThat(options.getScaleX(), equalTo(2));
		assertThat(options.getScaleY(), equalTo(3));
	}

	@Test
	public void parseOptions_parsesReverseFlag() {
		Options options = parser.parseOptions(new String[]{"--reverse"});

		assertThat(options.isReverse(), equalTo(true));
	}

	@Test
	public void parseOptions_ignoresOptionsAfterDoubleDash() {
		Options options = parser.parseOptions(new String[]{"--", "--size=2x3"});

		assertThat(options.getScaleX(), equalTo(1));
		assertThat(options.getScaleY(), equalTo(1));
	}
}
