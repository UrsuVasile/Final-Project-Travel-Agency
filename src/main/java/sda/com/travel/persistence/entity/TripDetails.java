package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Table(name = "trips_details")
@Entity
public class TripDetails {

    private static final String TRIPDETAILS_SEQUENCE = "tripdetails_id_sequence";
    private static final String TRIPDETAILS_GENERATOR = "tripdetails_generator";

    @Id
    @SequenceGenerator(name = "TRIPDETAILS_GENERATOR", sequenceName = TRIPDETAILS_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TRIPDETAILS_GENERATOR)
    private int id;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToMany(mappedBy = "tripDetailsSet")
    private Set<User> userSet;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
