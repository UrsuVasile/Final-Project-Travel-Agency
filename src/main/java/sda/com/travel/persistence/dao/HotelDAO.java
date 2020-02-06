package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Hotel;
import sda.com.travel.persistence.entity.Room;
import sda.com.travel.persistence.entity.TripDetails;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {

    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public void insertHotel(Hotel hotel) {
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(hotel);
        hibernateUtil.closeSessionAndTransaction();
    }

    public void deleteHotel(String name) {
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("delete_hotel_by_name");
        query.setParameter("name", name);
        query.executeUpdate();
        hibernateUtil.closeSessionAndTransaction();
    }

    public List<Hotel> findHotelByName(String name) {
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_hotel_by_name");
        query.setParameter("name", name);
        List<Hotel> hotelsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return hotelsList;
    }

    public void updateHotel(Hotel hotel) throws Exception {
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("count_hotel");
        query.setParameter("name", hotel.getName());
        Long nrOfHotels = (Long) query.getSingleResult();

        if (nrOfHotels == 1){
            Query query1 = hibernateUtil.session.createNamedQuery("find_hotel_by_name");
            query1.setParameter("name", hotel.getName());

            Hotel hotel1 = (Hotel) query1.getSingleResult();

            hotel1.setName(hotel.getName());
            hotel1.setDescription(hotel.getDescription());
            hotel1.setStandard(hotel.getStandard());
            hotel1.setCity(hotel.getCity());
            Room room = new Room();
            room.setToDate(hotel.getRoom().getToDate());
            room.setPriceForSingleRoom(hotel.getRoom().getPriceForSingleRoom());
            room.setPriceForExtraBed(hotel.getRoom().getPriceForExtraBed());
            room.setPriceForDoubleRoom(hotel.getRoom().getPriceForDoubleRoom());
            room.setNrOfSingleRooms(hotel.getRoom().getNrOfSingleRooms());
            room.setNrOfExtraBeds(hotel.getRoom().getNrOfExtraBeds());
            room.setNrOfDoubleRooms(hotel.getRoom().getNrOfDoubleRooms());
            room.setFromDate(hotel.getRoom().getFromDate());
            room.setHotel(hotel1);
            hotel1.setRoom(room);

            hibernateUtil.session.persist(hotel1);
        }else throw new Exception();
        hibernateUtil.closeSessionAndTransaction();
    }

    public List<Hotel> findHotelByNameAndCityName(Hotel hotel){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_hotel_by_name_and_city_name");
        query.setParameter("hotelName", hotel.getName());
        query.setParameter("cityName", hotel.getCity().getName());
        List<Hotel> hotelsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return hotelsList;
    }

    public Hotel findHotelByNameWithSingleResult(String name){
        //hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_hotel_by_name");
        query.setParameter("name", name);
        Hotel hotel = (Hotel) query.getSingleResult();
        //hibernateUtil.closeSessionAndTransaction();
        return hotel;
    }

    public void updateRomsFromHotel(TripDetails tripDetails) {

        Query query = hibernateUtil.session.createNamedQuery("count_hotel");
        query.setParameter("name", tripDetails.getTrip().getTripHotel().getName());
        Long nrOfHotels = (Long) query.getSingleResult();

        if (nrOfHotels == 1){
            Query query1 = hibernateUtil.session.createNamedQuery("find_hotel_by_name");
            query1.setParameter("name", tripDetails.getTrip().getTripHotel().getName());
            Hotel hotel1 = (Hotel) query1.getSingleResult();

            hotel1.setName(hotel1.getName());
            hotel1.setDescription(hotel1.getDescription());
            hotel1.setStandard(hotel1.getStandard());
            hotel1.setCity(hotel1.getCity());
            hotel1.setId(hotel1.getId());

            Room room = new Room();
            room.setToDate(tripDetails.getTrip().getTripHotel().getRoom().getToDate());
            room.setPriceForSingleRoom(tripDetails.getTrip().getTripHotel().getRoom().getPriceForSingleRoom());
            room.setPriceForExtraBed(tripDetails.getTrip().getTripHotel().getRoom().getPriceForExtraBed());
            room.setPriceForDoubleRoom(tripDetails.getTrip().getTripHotel().getRoom().getPriceForDoubleRoom());
            room.setNrOfSingleRooms(hotel1.getRoom().getNrOfSingleRooms()-tripDetails.getTrip().getTripHotel().getRoom().getNrOfSingleRooms());
            room.setNrOfExtraBeds(hotel1.getRoom().getNrOfExtraBeds()-tripDetails.getTrip().getNrOfExtraBeds());
            room.setNrOfDoubleRooms(hotel1.getRoom().getNrOfDoubleRooms()-tripDetails.getTrip().getNrOfDoubleRooms());
            room.setFromDate(tripDetails.getTrip().getTripHotel().getRoom().getFromDate());
            room.setId(hotel1.getRoom().getId());
//            room.setHotel(hotel1);
            hotel1.setRoom(room);

            hibernateUtil.session.persist(hotel1);
        }else try {
            throw new Exception("Error! nrOfHotels !=1");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
