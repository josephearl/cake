package com.waracle.cake.adapter;

import com.waracle.cake.CakeManager;
import com.waracle.cake.adapter.db.CakeRepository;
import javax.enterprise.context.ApplicationScoped;

class CakeProducers {
  @ApplicationScoped
  CakeManager manager(CakeRepository repository) {
    return new CakeManager(repository);
  }
}
