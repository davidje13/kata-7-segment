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
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class Segment7FontTest {
	private final Segment7Font font = new Segment7Font();

	@Test
	public void toSegments_returnsSegmentsForDigits() {
		assertThat(font.toSegments('0'), containsInAnyOrder(TOP, TL, TR, BL, BR, BASE));
		assertThat(font.toSegments('1'), containsInAnyOrder(TR, BR));
		assertThat(font.toSegments('2'), containsInAnyOrder(TOP, TR, MID, BL, BASE));
		assertThat(font.toSegments('3'), containsInAnyOrder(TOP, TR, MID, BR, BASE));
		assertThat(font.toSegments('4'), containsInAnyOrder(TL, TR, MID, BR));
		assertThat(font.toSegments('5'), containsInAnyOrder(TOP, TL, MID, BR, BASE));
		assertThat(font.toSegments('6'), containsInAnyOrder(TOP, TL, MID, BL, BR, BASE));
		assertThat(font.toSegments('7'), containsInAnyOrder(TOP, TR, BR));
		assertThat(font.toSegments('8'), containsInAnyOrder(TOP, TL, TR, MID, BL, BR, BASE));
		assertThat(font.toSegments('9'), containsInAnyOrder(TOP, TL, TR, MID, BR, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsForLetters() {
		assertThat(font.toSegments('a'), containsInAnyOrder(TOP, TL, TR, MID, BL, BR));
		assertThat(font.toSegments('b'), containsInAnyOrder(TL, MID, BL, BR, BASE));
		assertThat(font.toSegments('c'), containsInAnyOrder(MID, BL, BASE));
		assertThat(font.toSegments('d'), containsInAnyOrder(TR, MID, BL, BR, BASE));
		assertThat(font.toSegments('e'), containsInAnyOrder(TOP, TL, MID, BL, BASE));
		assertThat(font.toSegments('f'), containsInAnyOrder(TOP, TL, MID, BL));
		assertThat(font.toSegments('g'), containsInAnyOrder(TOP, TL, BL, BR, BASE));
		assertThat(font.toSegments('h'), containsInAnyOrder(TL, MID, BL, BR));
		assertThat(font.toSegments('i'), containsInAnyOrder(BR));
		assertThat(font.toSegments('j'), containsInAnyOrder(TR, BR, BASE));
		assertThat(font.toSegments('k'), containsInAnyOrder(TL, TR, MID, BL, BR));
		assertThat(font.toSegments('l'), containsInAnyOrder(TL, BL, BASE));
		assertThat(font.toSegments('m'), containsInAnyOrder(TOP, TL, TR, BL, BR));
		assertThat(font.toSegments('n'), containsInAnyOrder(MID, BL, BR));
		assertThat(font.toSegments('o'), containsInAnyOrder(MID, BL, BR, BASE));
		assertThat(font.toSegments('p'), containsInAnyOrder(TOP, TL, TR, MID, BL));
		assertThat(font.toSegments('q'), containsInAnyOrder(TOP, TL, TR, MID, BR));
		assertThat(font.toSegments('r'), containsInAnyOrder(MID, BL));
		assertThat(font.toSegments('s'), containsInAnyOrder(TOP, TL, MID, BR, BASE));
		assertThat(font.toSegments('t'), containsInAnyOrder(TL, MID, BL, BASE));
		assertThat(font.toSegments('u'), containsInAnyOrder(TL, TR, BL, BR, BASE));
		assertThat(font.toSegments('v'), containsInAnyOrder(BL, BR, BASE));
		assertThat(font.toSegments('w'), containsInAnyOrder(TL, TR, BL, BR, BASE));
		assertThat(font.toSegments('x'), containsInAnyOrder(TL, TR, MID, BL, BR));
		assertThat(font.toSegments('y'), containsInAnyOrder(TL, TR, MID, BR, BASE));
		assertThat(font.toSegments('z'), containsInAnyOrder(TOP, TR, MID, BL, BASE));
	}

	@Test
	public void toSegments_isCaseInsensitive() {
		assertThat(font.toSegments('A'), containsInAnyOrder(TOP, TL, TR, MID, BL, BR));
	}

	@Test
	public void toSegments_returnsSegmentsForPunctuation() {
		assertThat(font.toSegments('^'), containsInAnyOrder(TOP));
		assertThat(font.toSegments('-'), containsInAnyOrder(MID));
		assertThat(font.toSegments('_'), containsInAnyOrder(BASE));
		assertThat(font.toSegments('='), containsInAnyOrder(MID, BASE));
	}

	@Test
	public void toSegments_returnsSegmentsForSpace() {
		assertThat(font.toSegments(' '), hasSize(0));
	}

	@Test
	public void toSegments_returnsBlankForUnknownCharacters() {
		assertThat(font.toSegments('@'), hasSize(0));
	}

	@Test
	public void fromSegments_returnsAsciiForSegments() {
		assertThat(font.fromSegments(EnumSet.of(TR, BR)), equalTo('1'));
	}

	@Test
	public void fromSegments_returnsSpaceForBlank() {
		assertThat(font.fromSegments(EnumSet.noneOf(Segment7.class)), equalTo(' '));
	}

	@Test
	public void fromSegments_returnsFirstRegisteredValueIfDuplicatesAreFound() {
		assertThat(font.fromSegments(EnumSet.of(TOP, TR, MID, BL, BASE)), equalTo('2'));
	}

	@Test
	public void fromSegments_returnsReplacementCharacterForUnknownSegments() {
		assertThat(font.fromSegments(EnumSet.of(BL)), equalTo('\uFFFD'));
	}
}
