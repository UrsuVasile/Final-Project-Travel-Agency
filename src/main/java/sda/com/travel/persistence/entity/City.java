package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "find_city_by_name", query = "select c from City c where c.name=:name"),
        @NamedQuery(name = "delete_city", query = "delete City c where c.name=:name "),
        @NamedQuery(name = "find_city_by_name_and_continent_name", query = "select c from City c where c.name =:cityName and c.country.countryName=:countryName"),
        @NamedQuery(name = "count_citys", query = "select  count(*) from City c where c.name=:name")
})

@Table(name = "citys")
@Entity
public class City {

    private static final String CITY_SEQUENCE = "city_id_sequence";
    private static final String CITY_GENERATOR = "city_generator";

    @Id
    @SequenceGenerator(name = "CITY_GENERATOR", sequenceName = CITY_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CITY_GENERATOR)
    private int id;

    @Column(name = "city_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "city")
    private List<Hotel> hotelsList;

    public List<Hotel> getHotelsList() {
        return hotelsList;
    }

    public void setHotelsList(List<Hotel> hotelsList) {
        this.hotelsList = hotelsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
