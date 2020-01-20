package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "find_country_by_name", query = "select c from Country c where c.countryName=:countryName")
})

@Table(name = "countrys")
@Entity
public class Country {

    private static final String COUNTRY_SEQUENCE = "country_id_sequence";
    private static final String COUNTRY_GENERATOR = "country_generator";

    @Id
    @SequenceGenerator(name = "COUNTRY_GENERATOR", sequenceName = COUNTRY_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COUNTRY_GENERATOR)
    private int id;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "country")
    private List<City> citiesList;

    public List<City> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<City> citiesList) {
        this.citiesList = citiesList;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
