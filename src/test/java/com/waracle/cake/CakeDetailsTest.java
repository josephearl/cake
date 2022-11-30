package com.waracle.cake;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URI;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class CakeDetailsTest {
  @Test
  void constructorShouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(() -> new CakeDetails(null, LEMON_CAKE_DESCRIPTION, LEMON_CAKE_IMAGE))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("title");
  }

  @Test
  void constructorShouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(() -> new CakeDetails(LEMON_CAKE_TITLE, null, LEMON_CAKE_IMAGE))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("description");
  }

  @Test
  void constructorShouldThrowExceptionWhenImageIsNull() {
    assertThatThrownBy(() -> new CakeDetails(LEMON_CAKE_TITLE, LEMON_CAKE_DESCRIPTION, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("image");
  }

  @Test
  void equalsAndHashCodeShouldSatisfyContract() {
    EqualsVerifier.forClass(CakeDetails.class)
        .suppress(Warning.NULL_FIELDS)
        .suppress(Warning.NONFINAL_FIELDS)
        .withPrefabValues(
            URI.class, URI.create("http://example.com"), URI.create("https://example.com"))
        .verify();
  }
}
