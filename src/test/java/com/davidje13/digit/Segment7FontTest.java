package com.davidje13.digit;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.EnumSet;

import static com.davidje13.digit.Segment7.BASE;
import static com.davidje13.digit.Segment7.BL;
import static com.davidje13.digit.Segment7.BR;
import static com.davidje13.digit.Segment7.MID;
import static com.davidje13.digit.Segment7.TL;
import static com.davidje13.digit.Segment7.TOP;
import static com.davidje13.digit.Segment7.TR;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class Segment7FontTest {
	private final Segment7Font font = new Segment7Font();

	@Test
	public void toSegments_returnsSegmentsFor0() {
		assertThat(font.toSegments('0'), containsInAnyOrder(TOP, TL, TR, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor1() {
		assertThat(font.toSegments('1'), containsInAnyOrder(TR, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor2() {
		assertThat(font.toSegments('2'), containsInAnyOrder(TOP, TR, MID, BL, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor3() {
		assertThat(font.toSegments('3'), containsInAnyOrder(TOP, TR, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor4() {
		assertThat(font.toSegments('4'), containsInAnyOrder(TOP, TL, TR, MID, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor5() {
		assertThat(font.toSegments('5'), containsInAnyOrder(TOP, TL, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor6() {
		assertThat(font.toSegments('6'), containsInAnyOrder(TOP, TL, MID, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor7() {
		assertThat(font.toSegments('7'), containsInAnyOrder(TOP, TR, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor8() {
		assertThat(font.toSegments('8'), containsInAnyOrder(TOP, TL, TR, MID, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor9() {
		assertThat(font.toSegments('9'), containsInAnyOrder(TOP, TL, TR, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsForMinus() {
		assertThat(font.toSegments('-'), containsInAnyOrder(MID));
	}

	@Test
	public void toSegments_returnsSegmentsForSpace() {
		assertThat(font.toSegments(' '), hasSize(0));
	}

	@Test
	public void toSegments_convertsWholeStrings() {
		assertThat(font.toSegments("01").collect(toList()), Matchers.<EnumSet<?>>contains(
				font.toSegments('0'),
				font.toSegments('1')
		));
	}
}
