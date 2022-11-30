package com.waracle.cake;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class CakeTest {
  @Test
  void constructorShouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(() -> new Cake(1L, null, LEMON_CAKE_DESCRIPTION, LEMON_CAKE_IMAGE))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("title");
  }

  @Test
  void constructorShouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(() -> new Cake(1L, LEMON_CAKE_TITLE, null, LEMON_CAKE_IMAGE))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("description");
  }

  @Test
  void constructorShouldThrowExceptionWhenImageIsNull() {
    assertThatThrownBy(() -> new Cake(1L, LEMON_CAKE_TITLE, LEMON_CAKE_DESCRIPTION, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("image");
  }

  @Test
  void fromShouldReturnNewCakeCopiedFromDetails() {
    var id = 1L;
    var details = lemonCakeDetails();

    var cake = Cake.from(id, details);

    assertThat(cake).isEqualTo(lemonCake(id));
  }

  @Test
  void fromShouldReturnThrowExceptionWhenDetailsIsNull() {
    assertThatThrownBy(() -> Cake.from(1L, null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("details");
  }

  @Test
  void withDetailsShouldReturnCakeWithUpdatedInformation() {
    var details = lemonCakeDetails();
    var id = 1L;
    var cake = chocolateCake(id);

    var updatedCake = cake.withDetails(details);

    assertThat(updatedCake).isEqualTo(lemonCake(id));
  }

  @Test
  void withDetailsShouldThrowExceptionWhenDetailsIsNull() {
    assertThatThrownBy(() -> lemonCake(1L).withDetails(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("details");
  }

  @Test
  void equalsAndHashCodeShouldSatisfyContract() {
    EqualsVerifier.forClass(Cake.class).suppress(Warning.NULL_FIELDS).verify();
  }
}
