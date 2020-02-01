package sda.com.travel.frontend.dto;

import java.util.Set;

public class TripDetailsDTO {

    private int amount;
    private TripDTO tripDTO;
    private Set<UserDTO> userDTOSet;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    public Set<UserDTO> getUserDTOSet() {
        return userDTOSet;
    }

    public void setUserDTOSet(Set<UserDTO> userDTOSet) {
        this.userDTOSet = userDTOSet;
    }
}
