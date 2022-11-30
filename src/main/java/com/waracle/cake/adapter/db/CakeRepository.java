package com.waracle.cake.adapter.db;

import com.waracle.cake.Cake;
import com.waracle.cake.CakeDetails;
import com.waracle.cake.CakeInventory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CakeRepository implements CakeInventory, PanacheRepository<CakeRecord> {
  @Override
  public List<Cake> getCakes() {
    return streamAll(Sort.ascending("id")).map(CakeRecord::toCake).toList();
  }

  @Override
  public Optional<Cake> getCake(long id) {
    return findByIdOptional(id).map(CakeRecord::toCake);
  }

  @Override
  public Cake addCake(CakeDetails details) {
    var entity = CakeRecord.from(details);
    persist(entity);
    return entity.toCake();
  }

  @Override
  public Cake modifyCake(Cake cake) {
    var record = getEntityManager().merge(CakeRecord.from(cake));
    persist(record);
    return record.toCake();
  }

  @Override
  public long removeCakes() {
    return deleteAll();
  }

  @Override
  public boolean removeCake(long id) {
    return deleteById(id);
  }
}
