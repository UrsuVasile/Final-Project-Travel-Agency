package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Flight;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class FlightDAO {
    HibernateUtil hibernateUtil = new HibernateUtil();

    public Flight findFlightByFlightNumber (String flightNumber){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_fligh_by_flight_number");
        query.setParameter("flightNumber", flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return flight;
    }
}
