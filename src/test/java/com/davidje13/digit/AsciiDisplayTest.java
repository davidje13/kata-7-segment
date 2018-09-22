package com.davidje13.digit;

import org.junit.Test;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static com.davidje13.digit.Segments.BASE;
import static com.davidje13.digit.Segments.BL;
import static com.davidje13.digit.Segments.BR;
import static com.davidje13.digit.Segments.MID;
import static com.davidje13.digit.Segments.TL;
import static com.davidje13.digit.Segments.TOP;
import static com.davidje13.digit.Segments.TR;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class AsciiDisplayTest {
	private final AsciiDisplay display = new AsciiDisplay();

	@Test
	public void toAsciiLines_returnsListOfLines() {
		assertThat(display.toAsciiLines(EnumSet.noneOf(Segments.class)), contains(
				"   ",
				"   ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopBar() {
		assertThat(display.toAsciiLines(EnumSet.of(TOP)), contains(
				" _ ",
				"   ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopLeftBar() {
		assertThat(display.toAsciiLines(EnumSet.of(TL)), contains(
				"   ",
				"|  ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopRightBar() {
		assertThat(display.toAsciiLines(EnumSet.of(TR)), contains(
				"   ",
				"  |",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysMiddleBar() {
		assertThat(display.toAsciiLines(EnumSet.of(MID)), contains(
				"   ",
				" _ ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysBottomLeftBar() {
		assertThat(display.toAsciiLines(EnumSet.of(BL)), contains(
				"   ",
				"   ",
				"|  "
		));
	}

	@Test
	public void toAsciiLines_displaysBottomRightBar() {
		assertThat(display.toAsciiLines(EnumSet.of(BR)), contains(
				"   ",
				"   ",
				"  |"
		));
	}

	@Test
	public void toAsciiLines_displaysBaseBar() {
		assertThat(display.toAsciiLines(EnumSet.of(BASE)), contains(
				"   ",
				"   ",
				" _ "
		));
	}

	@Test
	public void toAsciiLines_combinesSpecifiedSegments() {
		assertThat(display.toAsciiLines(EnumSet.of(TOP, TL, TR, MID, BL, BR, BASE)), contains(
				" _ ",
				"|_|",
				"|_|"
		));
	}

	@Test
	public void toAsciiLines_convertsWholeSequences() {
		List<String> lines = display.toAsciiLines(Stream.of(EnumSet.of(TOP), EnumSet.of(BASE)), " ");
		assertThat(lines, contains(
				" _     ",
				"       ",
				"     _ "
		));
	}

	@Test
	public void joinAsciiLines_combinesListOfLines() {
		List<String> joined = display.joinAsciiLines(asList(
				asList("aa", "bb"),
				asList("AA", "BB"),
				asList("-endA", "-endB")
		), "");
		assertThat(joined, contains("aaAA-endA", "bbBB-endB"));
	}

	@Test
	public void joinAsciiLines_includesSpecifiedDividerOnAllLines() {
		List<String> joined = display.joinAsciiLines(asList(
				asList("aa", "bb"),
				asList("AA", "BB"),
				asList("-endA", "-endB")
		), " ");
		assertThat(joined, contains("aa AA -endA", "bb BB -endB"));
	}

	@Test
	public void joinAsciiLines_returnsEmptyList_ifNoInputGiven() {
		assertThat(display.joinAsciiLines(asList(), ""), hasSize(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void joinAsciiLines_throwsIfCharacterSizesVary_lower() {
		display.joinAsciiLines(asList(
				asList("aa", "bb"),
				asList("AA")
		), "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void joinAsciiLines_throwsIfCharacterSizesVary_higher() {
		display.joinAsciiLines(asList(
				asList("aa", "bb"),
				asList("AA", "BB", "CC")
		), "");
	}

	@Test
	public void flattenAsciiLines_returnsAllLinesInOneString() {
		String ascii = display.flattenAsciiLines(asList("AA", "BB", "CC"));
		assertThat(ascii, equalTo("AA\nBB\nCC"));
	}

	@Test
	public void flattenAsciiLines_doesNotIncludeTrailingNewline() {
		String ascii = display.flattenAsciiLines(asList());
		assertThat(ascii, equalTo(""));
	}
}
