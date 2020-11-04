package dev.kameshs;

import dev.kameshs.fruit.data.Fruit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fruit")
public class FruitEntity extends Fruit {
  @Id @GeneratedValue public Long id;
}
