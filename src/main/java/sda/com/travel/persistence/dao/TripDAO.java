package sda.com.travel.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Trip;
import sda.com.travel.persistence.entity.TripDetails;
import sda.com.travel.persistence.entity.User;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TripDAO {
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public void insertTrip(Trip trip){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(trip);
        hibernateUtil.closeSessionAndTransaction();
    }

    public List<Trip> findTripByHotelName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip_by_hotel_name");
        query.setParameter("name", name);
        List<Trip> tripsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return tripsList;
    }

    public List<Trip> findTrip(Trip trip){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip");

        System.out.println(trip);

        query.setParameter("departureDate", trip.getDepartureDate().getDepartureDate());
        query.setParameter("returneDate", trip.getReturnDate().getDepartureDate());
        query.setParameter("hotelName", trip.getTripHotel().getName());
        query.setParameter("cityName", trip.getTripHotel().getCity().getName());
        query.setParameter("nrSingle", trip.getNrOfSingleRooms());
        query.setParameter("nrDouble", trip.getNrOfDoubleRooms());
        query.setParameter("nrExtraBeds", trip.getNrOfExtraBeds());
        List<Trip> tripList = query.getResultList();
        System.out.println("marime lista = "+tripList.size());
        hibernateUtil.closeSessionAndTransaction();
        return tripList;
    }

    public Trip findTripSingleResult(String hotelName){
        System.out.println("nume hotellllllllllll = "+hotelName);

        Query query = hibernateUtil.session.createNamedQuery("find_trip_by_hotel_name");
        query.setParameter("name", hotelName);
        System.out.println("nume hotel: "+ hotelName);
        Trip trip = (Trip) query.getSingleResult();
        //hibernateUtil.closeSessionAndTransaction();

        return trip;
    }

    public List<Trip> findTripsByCountries(String countryName){

        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip_by_country");
        query.setParameter("countryName", countryName);
        List<Trip> tripsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return tripsList;

    }

    public List<Trip> findTripIfIsPromoted (boolean isPromoted){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip_if_is_promoted");
        List<Trip> tripsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return tripsList;
    }

    public List<Trip> findTripByType(String type){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip_by_type");
        query.setParameter("type", type);
        List<Trip> tripsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return tripsList;
    }

    public List<Trip> findTripByDepartureFlight(Date departureFlight){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_trip_by_departureDate");
        query.setParameter("departureFlight", departureFlight);
        List<Trip> tripsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return tripsList;
    }

}
