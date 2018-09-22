package com.davidje13;

import com.davidje13.digit.AsciiDisplay;
import com.davidje13.digit.Segment7Font;

import java.util.List;

public class Main {
	private final Segment7Font font;
	private final AsciiDisplay display;

	Main(Segment7Font font, AsciiDisplay display) {
		this.font = font;
		this.display = display;
	}

	String toAsciiSegments(CharSequence value) {
		List<String> lines = display.toAsciiLines(font.toSegments(value), " ");
		return display.flattenAsciiLines(lines);
	}

	public static void main(String[] args) {
		Main main = new Main(
				new Segment7Font(),
				new AsciiDisplay()
		);
		String value = (args.length > 0) ? args[0] : "";
		System.out.println(main.toAsciiSegments(value));
	}
}
