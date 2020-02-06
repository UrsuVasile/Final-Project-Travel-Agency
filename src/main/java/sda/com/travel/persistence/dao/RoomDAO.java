package sda.com.travel.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Hotel;
import sda.com.travel.persistence.entity.Room;
import sda.com.travel.persistence.entity.TripDetails;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class RoomDAO {

    HibernateUtil hibernateUtil = HibernateUtil.getInstance();
    @Autowired
    HotelDAO hotelDAO;

    public Room findRoomsByHotelName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_rooms");
        query.setParameter("name", name);
        Room room = (Room) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return room;
    }

    public void updateRooms(TripDetails tripDetails){
        Query query = hibernateUtil.session.createNamedQuery("count_rooms");
        query.setParameter("hotelName", tripDetails.getTrip().getTripHotel().getName());
        Long countRooms = (Long) query.getSingleResult();

        if (countRooms == 1){
            Query findRoomsInDB = hibernateUtil.session.createNamedQuery("find_rooms").setParameter("name", tripDetails.getTrip().getTripHotel().getName());
            Room room = (Room) findRoomsInDB.getSingleResult();

            room.setId(room.getId());
            room.setFromDate(room.getFromDate());
            room.setToDate(room.getToDate());
            room.setPriceForSingleRoom(room.getPriceForSingleRoom());
            room.setPriceForExtraBed(room.getPriceForExtraBed());
            room.setPriceForDoubleRoom(room.getPriceForDoubleRoom());
            room.setNrOfDoubleRooms(room.getNrOfDoubleRooms()-tripDetails.getTrip().getNrOfDoubleRooms());
            room.setNrOfExtraBeds(room.getNrOfExtraBeds()-tripDetails.getTrip().getNrOfExtraBeds());
            room.setNrOfSingleRooms(room.getNrOfSingleRooms()-tripDetails.getTrip().getNrOfSingleRooms());

            Hotel hotel = hotelDAO.findHotelByNameWithSingleResult(tripDetails.getTrip().getTripHotel().getName());
            room.setHotel(hotel);

            hibernateUtil.session.persist(room);
        }else {
            try {
                throw new Exception("Error! countRooms !=1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
