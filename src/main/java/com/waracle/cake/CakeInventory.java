package com.waracle.cake;

import java.util.List;
import java.util.Optional;

public interface CakeInventory {
  List<Cake> getCakes();

  Optional<Cake> getCake(long id);

  Cake addCake(CakeDetails details);

  Cake modifyCake(Cake cake);

  long removeCakes();

  boolean removeCake(long id);
}
