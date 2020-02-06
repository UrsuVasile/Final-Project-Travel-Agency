package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "find_airport_by_name", query = "select a from Airport a where a.name=:name")
})

@Table(name = "airports")
@Entity
public class Airport {

    private static final String AIRPORT_SEQUENCE = "airport_id_sequence";
    private static final String AIRPORT_GENERATOR = "airport_generator";

    @Id
    @SequenceGenerator(name = "AIRPORT_GENERATOR", sequenceName = AIRPORT_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AIRPORT_GENERATOR)
    private int id;

    @Column(name = "airport_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id")
    private City cityAirports;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "airport")
    private Set<Flight> flightsList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCityAirports() {
        return cityAirports;
    }

    public void setCityAirports(City cityAirports) {
        this.cityAirports = cityAirports;
    }

    public Set<Flight> getFlightsList() {
        return flightsList;
    }

    public void setFlightsList(Set<Flight> flightsList) {
        this.flightsList = flightsList;
    }
}
