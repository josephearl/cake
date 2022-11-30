package com.waracle.cake.adapter.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGeneratorHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CakeIdGeneratorTest {
  CakeIdGenerator generator = new CakeIdGenerator();
  @Mock SharedSessionContractImplementor session;

  @Test
  void generateShouldReturnIdOfRecordWhenItHasAnId() {
    var record = new CakeRecord();
    record.setId(1L);

    assertThat(generator.generate(session, record)).isEqualTo(1L);
  }

  @Test
  void generateShouldReturnUniqueIdIndicatorWhenRecordDoesNotHaveAnId() {
    var record = new CakeRecord();

    assertThat(generator.generate(session, record))
        .isEqualTo(IdentifierGeneratorHelper.POST_INSERT_INDICATOR);
  }
}
