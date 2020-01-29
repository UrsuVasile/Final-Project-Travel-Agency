package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Trip;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TripDAO {
    HibernateUtil hibernateUtil = new HibernateUtil();

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

}
