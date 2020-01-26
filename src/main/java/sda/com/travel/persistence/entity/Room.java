package sda.com.travel.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "find_rooms", query = "select r from Room r where r.hotel.name=:name")
})

@Table(name = "rooms")
@Entity
public class Room {

    private static final String ROOM_SEQUENCE = "room_id_sequence";
    private static final String ROOM_GENERATOR = "room_generator";

    @Id
    @SequenceGenerator(name = "ROOM_GENERATOR", sequenceName = ROOM_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROOM_GENERATOR)
    private int id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "no_of_double_rooms")
    private int nrOfDoubleRooms;

    @Column(name = "no_of_single_rooms")
    private int nrOfSingleRooms;

    @Column(name = "no_of_extra_beds")
    private int nrOfExtraBeds;

    @Column(name = "price_for_double_room")
    private int priceForDoubleRoom;

    @Column(name = "price_for_single_room")
    private int priceForSingleRoom;

    @Column(name = "price_for_extra_bed")
    private int priceForExtraBed;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

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

}
