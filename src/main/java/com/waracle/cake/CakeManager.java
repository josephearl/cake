package com.waracle.cake;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CakeManager {
  @NonNull private final CakeInventory inventory;

  public List<Cake> readCakes() {
    return inventory.getCakes();
  }

  public Cake createCake(CakeDetails details) {
    return inventory.addCake(details);
  }

  public long deleteCakes() {
    return inventory.removeCakes();
  }

  public Optional<Cake> readCake(long id) {
    return inventory.getCake(id);
  }

  public Optional<Cake> updateCake(long id, CakeDetails details) {
    return inventory
        .getCake(id)
        .map((cake) -> cake.withDetails(details))
        .map(inventory::modifyCake);
  }

  public boolean deleteCake(long id) {
    return inventory.removeCake(id);
  }
}
