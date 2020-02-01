package sda.com.travel.frontend.dto;


import java.sql.Date;

public class FlightDTO {

    private String flightNumber;
    private Date departureDate;
    private int totalNrOfSeets;
    private int availableSeets;
    private int flightPrice;
    private AirportDTO airportDTO;

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

    public AirportDTO getAirportDTO() {
        return airportDTO;
    }

    public void setAirportDTO(AirportDTO airportDTO) {
        this.airportDTO = airportDTO;
    }
}
