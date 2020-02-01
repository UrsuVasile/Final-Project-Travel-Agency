package sda.com.travel.frontend.dto;

import java.util.Set;

public class UserDTO {
    private String firsName;
    private String lastName;
    private String email;
    private String password;
    private int totalAmount;
    private Set<TripDetailsDTO> tripDetailsDTOSet;

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

    public Set<TripDetailsDTO> getTripDetailsDTOSet() {
        return tripDetailsDTOSet;
    }

    public void setTripDetailsDTOSet(Set<TripDetailsDTO> tripDetailsDTOSet) {
        this.tripDetailsDTOSet = tripDetailsDTOSet;
    }
}
