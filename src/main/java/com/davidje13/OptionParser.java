package com.davidje13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

class OptionParser {
	private static final Map<String, BiConsumer<Options, String>> OPTION_PARSERS = new HashMap<>();

	static {
		OPTION_PARSERS.put("size", (options, value) -> {
			String[] size = value.split("x");
			if (size.length == 1) {
				options.scaleX = readNatural(size[0]);
				options.scaleY = readNatural(size[0]);
			} else {
				options.scaleX = readNatural(size[0]);
				options.scaleY = readNatural(size[1]);
			}
		});
		OPTION_PARSERS.put("reverse", (options, value) -> options.reverse = true);
	}

	Options parseOptions(String[] args) {
		Options options = new Options();

		boolean optionsEnded = false;
		for (String arg : args) {
			if (!optionsEnded && arg.startsWith("--")) {
				if (arg.equals("--")) {
					optionsEnded = true;
					continue;
				}
				String[] parts = arg.substring(2).split("=", 2);
				BiConsumer<Options, String> parser = OPTION_PARSERS.get(parts[0]);
				if (parser == null) {
					throw new IllegalArgumentException("Unknown flag " + arg);
				}
				parser.accept(options, parts.length > 1 ? parts[1] : "");
			} else {
				options.messages.add(arg);
			}
		}
		return options;
	}

	static class Options {
		private int scaleX = 1;
		private int scaleY = 1;
		private boolean reverse = false;
		private final List<String> messages = new ArrayList<>();

		int getScaleX() {
			return scaleX;
		}

		int getScaleY() {
			return scaleY;
		}

		boolean isReverse() {
			return reverse;
		}

		String getMessage() {
			return String.join(" ", messages);
		}
	}

	private static int readNatural(String value) {
		int n = Integer.parseInt(value);
		if (n <= 0) {
			throw new IllegalArgumentException("Invalid value: " + value);
		}
		return n;
	}
}
