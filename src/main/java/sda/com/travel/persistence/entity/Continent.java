package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "find_continent", query = "select c from Continent c where c.name=:name")
})

@Table(name = "continents")
@Entity
public class Continent {

    private static final String CONTINENT_SEQUENCE = "continent_id_sequence";
    private static final String CONTINENT_GENERATOR = "continent_generator";

    @Id
    @SequenceGenerator(name = "CONTINENT_GENERATOR", sequenceName = CONTINENT_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CONTINENT_GENERATOR)
    private int id;

    @Column(name = "continent_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "continent")
    private List<Country> countriesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
