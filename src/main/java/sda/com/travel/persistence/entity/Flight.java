package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "find_fligh_by_flight_number", query = "select f from Flight f where f.flightNumber=:flightNumber"),
        @NamedQuery(name = "count_flights", query = "select count(*) from Flight f where f.flightNumber=:flightNumber")
})

@Table(name = "flights")
@Entity
public class Flight {

    private static final String FLIGHT_SEQUENCE = "flight_id_sequence";
    private static final String FLIGHT_GENERATOR = "flight_generator";

    @Id
    @SequenceGenerator(name = "FLIGHT_GENERATOR", sequenceName = FLIGHT_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = FLIGHT_GENERATOR)
    private int id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "total_no_of_seets")
    private int totalNrOfSeets;

    @Column(name = "available_seets")
    private int availableSeets;

    @Column(name = "flight_price")
    private int flightPrice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "returnDate")
    private Trip tripReturn;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "departureDate")
    private Trip tripDeparture;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getTotalNrOfSeets() {
        return totalNrOfSeets;
    }

    public void setTotalNrOfSeets(int totalNrOfSeets) {
        this.totalNrOfSeets = totalNrOfSeets;
    }

    public int getAvailableSeets() {
        return availableSeets;
    }

    public void setAvailableSeets(int availableSeets) {
        this.availableSeets = availableSeets;
    }

    public int getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(int flightPrice) {
        this.flightPrice = flightPrice;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Trip getTripReturn() {
        return tripReturn;
    }

    public void setTripReturn(Trip tripReturn) {
        this.tripReturn = tripReturn;
    }

    public Trip getTripDeparture() {
        return tripDeparture;
    }

    public void setTripDeparture(Trip tripDeparture) {
        this.tripDeparture = tripDeparture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id &&
                totalNrOfSeets == flight.totalNrOfSeets &&
                availableSeets == flight.availableSeets &&
                flightPrice == flight.flightPrice &&
                Objects.equals(flightNumber, flight.flightNumber) &&
                Objects.equals(departureDate, flight.departureDate) &&
                Objects.equals(airport, flight.airport) &&
                Objects.equals(tripReturn, flight.tripReturn) &&
                Objects.equals(tripDeparture, flight.tripDeparture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightNumber, departureDate, totalNrOfSeets, availableSeets, flightPrice, airport, tripReturn, tripDeparture);
    }
}
