package com.davidje13.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static org.hamcrest.Matchers.instanceOf;

public class ThrowsExceptionMatcher extends TypeSafeDiagnosingMatcher<Runnable> {
	private final Matcher<? extends Exception> subMatcher;

	private ThrowsExceptionMatcher(Matcher<? extends Exception> subMatcher) {
		this.subMatcher = subMatcher;
	}

	public static ThrowsExceptionMatcher throwsException(Matcher<? extends Exception> subMatcher) {
		return new ThrowsExceptionMatcher(subMatcher);
	}

	public static ThrowsExceptionMatcher throwsException() {
		return throwsException(instanceOf(Exception.class));
	}

	@Override
	protected boolean matchesSafely(Runnable runnable, Description mismatchDescription) {
		try {
			runnable.run();
			mismatchDescription.appendText("did not throw");
			return false;
		} catch (Exception e) {
			if (!subMatcher.matches(e)) {
				mismatchDescription.appendText("threw ");
				subMatcher.describeMismatch(e, mismatchDescription);
				return false;
			}
			return true;
		}
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("a runnable throwing ");
		subMatcher.describeTo(description);
	}
}
