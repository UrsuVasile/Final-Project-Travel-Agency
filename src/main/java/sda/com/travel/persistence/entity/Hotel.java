package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "delete_hotel_by_name", query = "delete Hotel h where h.name=:name "),
        @NamedQuery(name = "find_hotel_by_name", query = "select h from Hotel h where h.name=:name"),
        @NamedQuery(name = "count_hotel", query = "select count(*) from Hotel h where h.name=:name"),
        @NamedQuery(name = "find_hotel_by_name_and_city_name", query = "select h from Hotel h where h.name=:hotelName and h.city.name=:cityName")
})

@Table(name = "hotels")
@Entity
public class Hotel {

    private static final String HOTEL_SEQUENCE = "hotel_id_sequence";
    private static final String HOTEL_GENERATOR = "hotel_generator";

    @Id
    @SequenceGenerator(name = "HOTEL_GENERATOR", sequenceName = HOTEL_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = HOTEL_GENERATOR)
    private int id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_description")
    private String description;

    @Column(name = "standard")
    private int standard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
