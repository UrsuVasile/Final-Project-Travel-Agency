package sda.com.travel.frontend.dto;

import java.util.Date;

public class RoomDTO {

    private Date fromDate;
    private Date toDate;
    private int nrOfDoubleRooms;
    private int nrOfSingleRooms;
    private int nrOfExtraBeds;
    private int priceForDoubleRoom;
    private int priceForSingleRoom;
    private int priceForExtraBed;
    private HotelDTO hotelDTO;


    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public int getPriceForDoubleRoom() {
        return priceForDoubleRoom;
    }

    public void setPriceForDoubleRoom(int priceForDoubleRoom) {
        this.priceForDoubleRoom = priceForDoubleRoom;
    }

    public int getPriceForSingleRoom() {
        return priceForSingleRoom;
    }

    public void setPriceForSingleRoom(int priceForSingleRoom) {
        this.priceForSingleRoom = priceForSingleRoom;
    }

    public int getPriceForExtraBed() {
        return priceForExtraBed;
    }

    public void setPriceForExtraBed(int priceForExtraBed) {
        this.priceForExtraBed = priceForExtraBed;
    }

//    public HotelDTO getHotelDTO() {
//        return hotelDTO;
//    }
//
//    public void setHotelDTO(HotelDTO hotelDTO) {
//        this.hotelDTO = hotelDTO;
//    }
}
