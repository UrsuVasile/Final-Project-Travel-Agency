package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "find_user_by_email", query = "select s from User s where s.email=:email")
})

@Table(name = "users")
@Entity
public class User {

    private static final String USER_SEQUENCE = "user_id_sequence";
    private static final String USER_GENERATOR = "user_generator";

    @Id
    @SequenceGenerator(name = "USER_GENERATOR", sequenceName = USER_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_GENERATOR)
    private int id;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToMany(mappedBy = "userSet")
    private Set<TripDetails> tripDetailsSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<TripDetails> getTripDetailsSet() {
        return tripDetailsSet;
    }

    public void setTripDetailsSet(Set<TripDetails> tripDetailsSet) {
        this.tripDetailsSet = tripDetailsSet;
    }
}
