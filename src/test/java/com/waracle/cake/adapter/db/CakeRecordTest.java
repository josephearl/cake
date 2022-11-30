package com.waracle.cake.adapter.db;

import static com.waracle.cake.TestCakes.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.waracle.cake.Url;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

class CakeRecordTest {
  @Test
  void fromShouldReturnRecordCopiedFromDetails() {
    var details = lemonCakeDetails();

    var record = CakeRecord.from(details);

    assertThat(record.getId()).isNull();
    assertThat(record.getTitle()).isEqualTo(details.title());
    assertThat(record.getDescription()).isEqualTo(details.description());
    assertThat(record.getImage()).isEqualTo(details.image().toString());
  }

  @Test
  void fromShouldReturnRecordCopiedFromCake() {
    var id = 1L;
    var cake = lemonCake(id);

    var record = CakeRecord.from(cake);

    assertThat(record.getId()).isEqualTo(id);
    assertThat(record.getTitle()).isEqualTo(cake.title());
    assertThat(record.getDescription()).isEqualTo(cake.description());
    assertThat(record.getImage()).isEqualTo(cake.image().toString());
  }

  @Test
  void toCakeShouldReturnCakeCopiedFromRecord() {
    var record = new CakeRecord();
    record.setId(1L);
    record.setTitle(LEMON_CAKE_TITLE);
    record.setDescription(LEMON_CAKE_DESCRIPTION);
    record.setImage(LEMON_CAKE_IMAGE_URL);

    var cake = record.toCake();

    assertThat(cake.id()).isEqualTo(record.getId());
    assertThat(cake.title()).isEqualTo(record.getTitle());
    assertThat(cake.description()).isEqualTo(record.getDescription());
    assertThat(cake.image()).isEqualTo(Url.parse(record.getImage()));
  }

  @Test
  void equalsAndHashCodeShouldSatisfyContract() {
    EqualsVerifier.forClass(CakeRecord.class)
        .suppress(Warning.STRICT_HASHCODE)
        .suppress(Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY)
        .verify();
  }
}
