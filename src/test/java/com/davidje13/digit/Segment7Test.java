package com.davidje13.digit;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.EnumSet;

import static com.davidje13.digit.Segments.BASE;
import static com.davidje13.digit.Segments.BL;
import static com.davidje13.digit.Segments.BR;
import static com.davidje13.digit.Segments.MID;
import static com.davidje13.digit.Segments.TL;
import static com.davidje13.digit.Segments.TOP;
import static com.davidje13.digit.Segments.TR;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

public class Segment7Test {
	private final Segment7 segment = new Segment7();

	@Test
	public void toSegments_returnsSegmentsFor0() {
		assertThat(segment.toSegments('0'), containsInAnyOrder(TOP, TL, TR, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor1() {
		assertThat(segment.toSegments('1'), containsInAnyOrder(TR, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor2() {
		assertThat(segment.toSegments('2'), containsInAnyOrder(TOP, TR, MID, BL, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor3() {
		assertThat(segment.toSegments('3'), containsInAnyOrder(TOP, TR, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor4() {
		assertThat(segment.toSegments('4'), containsInAnyOrder(TOP, TL, TR, MID, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor5() {
		assertThat(segment.toSegments('5'), containsInAnyOrder(TOP, TL, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor6() {
		assertThat(segment.toSegments('6'), containsInAnyOrder(TOP, TL, MID, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor7() {
		assertThat(segment.toSegments('7'), containsInAnyOrder(TOP, TR, BR));
	}

	@Test
	public void toSegments_returnsSegmentsFor8() {
		assertThat(segment.toSegments('8'), containsInAnyOrder(TOP, TL, TR, MID, BL, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsFor9() {
		assertThat(segment.toSegments('9'), containsInAnyOrder(TOP, TL, TR, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsForMinus() {
		assertThat(segment.toSegments('-'), containsInAnyOrder(MID));
	}

	@Test
	public void toSegments_returnsSegmentsForSpace() {
		assertThat(segment.toSegments(' '), hasSize(0));
	}

	@Test
	public void toSegments_convertsWholeStrings() {
		assertThat(segment.toSegments("01").collect(toList()), Matchers.<EnumSet<?>>contains(
				segment.toSegments('0'),
				segment.toSegments('1')
		));
	}
}
