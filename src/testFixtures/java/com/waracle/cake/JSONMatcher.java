package com.waracle.cake;

import java.util.Objects;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JSONMatcher extends TypeSafeMatcher<String> {
  private final String expected;

  private JSONMatcher(String expected) {
    this.expected = Objects.requireNonNull(expected, "expected");
  }

  public static JSONMatcher equalToJSON(String expected) {
    return new JSONMatcher(expected);
  }

  @Override
  protected boolean matchesSafely(String actual) {
    return matches(actual, Description.NONE);
  }

  @Override
  protected void describeMismatchSafely(String actual, Description mismatchDescription) {
    matches(actual, mismatchDescription);
  }

  private boolean matches(String actual, Description mismatchDescription) {
    try {
      var result = JSONCompare.compareJSON(expected, actual, JSONCompareMode.STRICT);
      if (result.failed()) mismatchDescription.appendText(result.getMessage());
      return result.passed();
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a json string matching ").appendText(expected);
  }
}
