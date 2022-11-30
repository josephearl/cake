package com.waracle.cake;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public abstract class CakeInventoryTest {
  protected abstract CakeInventory inventory();

  @Test
  public void getCakesShouldReturnAllCakes() {
    var cake1 = inventory().addCake(lemonCakeDetails());
    var cake2 = inventory().addCake(chocolateCakeDetails());

    var cakes = inventory().getCakes();

    assertThat(cakes).hasSize(2).containsExactly(lemonCake(cake1.id()), chocolateCake(cake2.id()));
  }

  @Test
  public void getCakeShouldReturnPresentWithCakeWhenCakeWithIdExists() {
    var storedCake = inventory().addCake(lemonCakeDetails());

    var cake = inventory().getCake(storedCake.id());

    assertThat(cake).isPresent().hasValue(lemonCake(storedCake.id()));
  }

  @Test
  public void getCakeShouldReturnEmptyWhenCakeWithIdDoesNotExist() {
    var cake = inventory().getCake(1L);

    assertThat(cake).isEmpty();
  }

  @Test
  public void addCakeReturnInsertedCakeWithUniqueId() {
    var cake1 = inventory().addCake(lemonCakeDetails());
    var cake2 = inventory().addCake(lemonCakeDetails());

    assertThat(cake1.id()).isGreaterThan(0L);
    assertThat(cake1).isEqualTo(lemonCake(cake1.id()));
    assertThat(cake2.id()).isGreaterThan(cake1.id());
    assertThat(cake2).isEqualTo(lemonCake(cake2.id()));
    assertThat(inventory().getCakes())
        .hasSize(2)
        .containsExactly(lemonCake(cake1.id()), lemonCake(cake2.id()));
  }

  @Test
  public void modifyCakeWithIdShouldReturnUpdatedCakeWhenCakeWithIdExists() {
    var cake = inventory().addCake(lemonCakeDetails());

    var modifiedCake = inventory().modifyCake(chocolateCake(cake.id()));

    assertThat(modifiedCake).isEqualTo(chocolateCake(cake.id()));
    assertThat(inventory().getCakes()).hasSize(1).containsExactly(chocolateCake(cake.id()));
  }

  @Test
  public void modifyCakeWithIdShouldReturnInsertedCakeWhenCakeWithIdDoesNotExist() {
    var storedCake = inventory().addCake(lemonCakeDetails());
    var id = storedCake.id() + 1L;

    var cake = inventory().modifyCake(chocolateCake(id));

    assertThat(cake).isEqualTo(chocolateCake(id));
    assertThat(inventory().getCakes())
        .hasSize(2)
        .containsExactly(lemonCake(storedCake.id()), chocolateCake(id));
  }

  @Test
  public void removeCakesShouldRemoveAllRows() {
    inventory().addCake(lemonCakeDetails());
    inventory().addCake(chocolateCakeDetails());

    var removed = inventory().removeCakes();

    assertThat(removed).isEqualTo(2L);
    assertThat(inventory().getCakes()).isEmpty();
  }

  @Test
  public void removeCakeShouldReturnTrueAndRemoveCakeWhenCakeWithIdExists() {
    var cake1 = inventory().addCake(lemonCakeDetails());
    var cake2 = inventory().addCake(chocolateCakeDetails());

    var removed = inventory().removeCake(cake1.id());

    assertThat(removed).isTrue();
    assertThat(inventory().getCakes()).hasSize(1).containsExactly(chocolateCake(cake2.id()));
  }

  @Test
  public void removeCakeShouldReturnFalseWhenRowWithIdDoesNotExist() {
    var cake = inventory().addCake(lemonCakeDetails());

    var removed = inventory().removeCake(cake.id() + 1L);

    assertThat(removed).isFalse();
    assertThat(inventory().getCakes()).hasSize(1).containsExactly(lemonCake(cake.id()));
  }
}
