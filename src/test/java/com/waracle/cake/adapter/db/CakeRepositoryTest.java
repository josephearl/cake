package com.waracle.cake.adapter.db;

import com.waracle.cake.CakeInventory;
import com.waracle.cake.CakeInventoryTest;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;

@QuarkusTest
@TestTransaction
class CakeRepositoryTest extends CakeInventoryTest {
  @Inject CakeRepository repository;

  @Override
  protected CakeInventory inventory() {
    return repository;
  }
}
