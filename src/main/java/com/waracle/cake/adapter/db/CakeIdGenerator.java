package com.waracle.cake.adapter.db;

import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

public class CakeIdGenerator extends IdentityGenerator {
  @Override
  public Serializable generate(SharedSessionContractImplementor s, Object obj) {
    var record = (CakeRecord) obj;
    // Don't generate a new ID if there already is one
    if (record.getId() != null) return record.getId();
    return super.generate(s, obj);
  }
}
