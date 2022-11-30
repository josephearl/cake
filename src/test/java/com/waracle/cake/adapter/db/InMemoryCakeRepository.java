package com.waracle.cake.adapter.db;

import com.waracle.cake.Cake;
import com.waracle.cake.CakeDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// To mock CakeInventory in a @QuarkusTest the inventory needs to be assignable to the real bean
// CakeRepository
public class InMemoryCakeRepository extends CakeRepository {
  private final Map<Long, Cake> cakes = new HashMap<>();
  private long idSequence = 1;

  @Override
  public List<Cake> getCakes() {
    return List.copyOf(cakes.values());
  }

  @Override
  public Optional<Cake> getCake(long id) {
    return Optional.ofNullable(cakes.get(id));
  }

  @Override
  public Cake addCake(CakeDetails details) {
    var id = idSequence++;
    var cake = Cake.from(id, details);
    cakes.put(cake.id(), cake);
    return cake;
  }

  @Override
  public Cake modifyCake(Cake cake) {
    cakes.put(cake.id(), cake);
    return cake;
  }

  @Override
  public long removeCakes() {
    int count = cakes.size();
    cakes.clear();
    return count;
  }

  @Override
  public boolean removeCake(long id) {
    return cakes.remove(id) != null;
  }
}
