package com.waracle.cake.adapter.db;

import com.waracle.cake.CakeInventory;
import com.waracle.cake.CakeInventoryTest;

class InMemoryCakeRepositoryTest extends CakeInventoryTest {
  InMemoryCakeRepository inventory = new InMemoryCakeRepository();

  @Override
  protected CakeInventory inventory() {
    return inventory;
  }
}
