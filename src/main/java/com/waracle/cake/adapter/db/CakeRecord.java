package com.waracle.cake.adapter.db;

import com.waracle.cake.Cake;
import com.waracle.cake.CakeDetails;
import com.waracle.cake.CakeMeta;
import com.waracle.cake.Url;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cakes")
@Getter
@Setter
@ToString
public class CakeRecord {
  @Id
  @GeneratedValue(generator = "cake-id")
  @GenericGenerator(name = "cake-id", strategy = "com.waracle.cake.adapter.db.CakeIdGenerator")
  private Long id;

  private String title;
  private String description;
  private String image;

  public static CakeRecord from(CakeDetails details) {
    return from((CakeMeta) details);
  }

  public static CakeRecord from(Cake cake) {
    var record = from((CakeMeta) cake);
    record.setId(cake.id());
    return record;
  }

  private static CakeRecord from(CakeMeta meta) {
    var record = new CakeRecord();
    record.setTitle(meta.title());
    record.setDescription(meta.description());
    record.setImage(meta.image().toString());
    return record;
  }

  public Cake toCake() {
    return new Cake(id, title, description, Url.parse(image));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    // Accept subclasses for Hibernate proxies
    if (!(o instanceof CakeRecord record)) return false;
    return id != null && Objects.equals(id, record.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
