package dev.kameshs;

import java.util.List;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Fruit extends PanacheEntity {

    public String name;
    public String season;


    public static List<Fruit> fruitsBySeason(String season) {
        return find("season", season).list();
    }
}
