package sda.com.travel.persistence.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "find_trip_by_hotel_name", query = "select t from Trip t where t.tripHotel.name=:name")
})

@Table(name = "trips")
@Entity
public class Trip {

    private static final String TRIP_SEQUENCE = "trip_id_sequence";
    private static final String TRIP_GENERATOR = "trip_generator";

    @Id
    @SequenceGenerator(name = "TRIP_GENERATOR", sequenceName = TRIP_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TRIP_GENERATOR)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_date")
    private Flight departureDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "return_date")
    private Flight returnDate;

    @Column(name = "trip_type")
    private String type;

    @Column(name = "promoted")
    private boolean promoted;

    @Column(name = "no_of_double_rooms")
    private int nrOfDoubleRooms;

    @Column(name = "no_of_single_rooms")
    private int nrOfSingleRooms;

    @Column(name = "no_of_extra_beds")
    private int nrOfExtraBeds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel tripHotel;


    public Flight getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Flight departureDate) {
        this.departureDate = departureDate;
    }

    public Flight getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Flight returnDate) {
        this.returnDate = returnDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    public int getNrOfDoubleRooms() {
        return nrOfDoubleRooms;
    }

    public void setNrOfDoubleRooms(int nrOfDoubleRooms) {
        this.nrOfDoubleRooms = nrOfDoubleRooms;
    }

    public int getNrOfSingleRooms() {
        return nrOfSingleRooms;
    }

    public void setNrOfSingleRooms(int nrOfSingleRooms) {
        this.nrOfSingleRooms = nrOfSingleRooms;
    }

    public int getNrOfExtraBeds() {
        return nrOfExtraBeds;
    }

    public void setNrOfExtraBeds(int nrOfExtraBeds) {
        this.nrOfExtraBeds = nrOfExtraBeds;
    }

    public Hotel getTripHotel() {
        return tripHotel;
    }

    public void setTripHotel(Hotel tripHotel) {
        this.tripHotel = tripHotel;
    }
}
