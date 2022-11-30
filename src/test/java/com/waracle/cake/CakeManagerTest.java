package com.waracle.cake;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CakeManagerTest {
  @Mock CakeInventory inventory;
  CakeManager manager;

  @BeforeEach
  void setUp() {
    manager = new CakeManager(inventory);
  }

  @Test
  void constructorShouldThrowExceptionWhenInventoryIsNull() {
    assertThatThrownBy(() -> new CakeManager(null))
        .isInstanceOf(NullPointerException.class)
        .hasMessageContaining("inventory");
  }

  @Test
  void readCakesShouldReturnCakesFromInventory() {
    var storedCakes = List.of(lemonCake(1L), chocolateCake(2L));
    when(inventory.getCakes()).thenReturn(storedCakes);

    var cakes = manager.readCakes();

    assertThat(cakes).isEqualTo(storedCakes);
  }

  @Test
  void deleteCakesShouldRemoveCakesFromInventoryAndReturnTheNumberRemoved() {
    var deletedCakes = 2L;
    when(inventory.removeCakes()).thenReturn(deletedCakes);

    var cake = manager.deleteCakes();

    assertThat(cake).isEqualTo(deletedCakes);
  }

  @Test
  void readCakeShouldReturnPresentWithCakeFromInventory() {
    var id = 1L;
    var storedCake = lemonCake(id);
    when(inventory.getCake(id)).thenReturn(Optional.of(storedCake));

    var cake = manager.readCake(id);

    assertThat(cake).isPresent().hasValue(storedCake);
  }

  @Test
  void readCakeShouldReturnEmptyIfCakeNotInInventory() {
    var id = 1L;
    when(inventory.getCake(id)).thenReturn(Optional.empty());

    var cake = manager.readCake(id);

    assertThat(cake).isEmpty();
  }

  @Test
  void createCakeShouldAddCakeToInventoryAndReturnIt() {
    var newCakeDetails = chocolateCakeDetails();
    var createdCake = chocolateCake(1L);
    when(inventory.addCake(newCakeDetails)).thenReturn(createdCake);

    var cake = manager.createCake(newCakeDetails);

    assertThat(cake).isEqualTo(createdCake);
  }

  @Test
  void updateCakeShouldGetCakeFromAndModifyCakeInInventoryAndReturnIt() {
    var id = 1L;
    var storedCake = lemonCake(id);
    var updatedDetails = chocolateCakeDetails();
    var updatedCake = chocolateCake(id);
    when(inventory.getCake(id)).thenReturn(Optional.of(storedCake));
    when(inventory.modifyCake(updatedCake)).thenReturn(updatedCake);

    var cake = manager.updateCake(id, updatedDetails);

    assertThat(cake).isPresent().hasValue(updatedCake);
  }

  @Test
  void updateCakeShouldGetCakeFromInventoryAndReturnEmptyIfCakeNotInInventory() {
    var id = 1L;
    when(inventory.getCake(id)).thenReturn(Optional.empty());

    var cake = manager.updateCake(id, lemonCakeDetails());

    assertThat(cake).isEmpty();
  }

  @Test
  void deleteCakeShouldRemoveCakeFromInventoryAndReturnWhetherItWasRemoved() {
    var id = 1L;
    when(inventory.removeCake(id)).thenReturn(true);

    var deleted = manager.deleteCake(id);

    assertThat(deleted).isTrue();
  }
}
