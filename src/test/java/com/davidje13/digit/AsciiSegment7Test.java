package com.davidje13.digit;

import org.junit.Test;

import java.util.EnumSet;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class AsciiSegment7Test {
	private final AsciiSegment7Font font = new AsciiSegment7Font();

	@Test
	public void toAsciiLines_returnsListOfLines() {
		assertThat(font.toAsciiLines(EnumSet.noneOf(Segment7.class)), contains(
				"   ",
				"   ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopBar() {
		assertThat(font.toAsciiLines(EnumSet.of(TOP)), contains(
				" _ ",
				"   ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopLeftBar() {
		assertThat(font.toAsciiLines(EnumSet.of(TL)), contains(
				"   ",
				"|  ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysTopRightBar() {
		assertThat(font.toAsciiLines(EnumSet.of(TR)), contains(
				"   ",
				"  |",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysMiddleBar() {
		assertThat(font.toAsciiLines(EnumSet.of(MID)), contains(
				"   ",
				" _ ",
				"   "
		));
	}

	@Test
	public void toAsciiLines_displaysBottomLeftBar() {
		assertThat(font.toAsciiLines(EnumSet.of(BL)), contains(
				"   ",
				"   ",
				"|  "
		));
	}

	@Test
	public void toAsciiLines_displaysBottomRightBar() {
		assertThat(font.toAsciiLines(EnumSet.of(BR)), contains(
				"   ",
				"   ",
				"  |"
		));
	}

	@Test
	public void toAsciiLines_displaysBaseBar() {
		assertThat(font.toAsciiLines(EnumSet.of(BASE)), contains(
				"   ",
				"   ",
				" _ "
		));
	}

	@Test
	public void toAsciiLines_combinesSpecifiedSegments() {
		assertThat(font.toAsciiLines(EnumSet.of(TOP, TL, TR, MID, BL, BR, BASE)), contains(
				" _ ",
				"|_|",
				"|_|"
		));
	}
}
