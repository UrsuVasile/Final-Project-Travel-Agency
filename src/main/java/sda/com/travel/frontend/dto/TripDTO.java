package sda.com.travel.frontend.dto;

import sda.com.travel.persistence.entity.TripDetails;

import java.util.Set;

public class TripDTO {

    private String type;
    private boolean promoted;
    private int nrOfDoubleRooms;
    private int nrOfSingleRooms;
    private int nrOfExtraBeds;
    private HotelDTO hotelDTO;
    private FlightDTO departureDate;
    private FlightDTO returnDate;
    private Set<TripDetailsDTO> tripDetailsDTOSet;

    public Set<TripDetailsDTO> getTripDetailsDTOSet() {
        return tripDetailsDTOSet;
    }

    public void setTripDetailsDTOSet(Set<TripDetailsDTO> tripDetailsDTOSet) {
        this.tripDetailsDTOSet = tripDetailsDTOSet;
    }

    public FlightDTO getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(FlightDTO departureDate) {
        this.departureDate = departureDate;
    }

    public FlightDTO getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(FlightDTO returnDate) {
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

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }
}
