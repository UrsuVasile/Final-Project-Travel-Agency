package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Airport;
import sda.com.travel.persistence.entity.City;
import sda.com.travel.persistence.entity.Flight;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

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

    public void updateFlight(Flight flight, int nrOfPersons) throws Exception {
        hibernateUtil.openSessionAndTransaction();
        System.out.println("numarul de zborrrrrrrrr "+flight.getFlightNumber());
        Query query = hibernateUtil.session.createNamedQuery("count_flights");
        query.setParameter("flightNumber", flight.getFlightNumber());

        Long nrOfFlights = (Long) query.getSingleResult();

        System.out.println("nr zboruri "+nrOfFlights);
        if(nrOfFlights == 1){
            Query query1 = hibernateUtil.session.createNamedQuery("find_fligh_by_flight_number");
            query1.setParameter("flightNumber", flight.getFlightNumber());

            Flight flightFromDB = (Flight) query1.getSingleResult();

            flightFromDB.setFlightPrice(flight.getFlightPrice());
            flightFromDB.setFlightNumber(flight.getFlightNumber());

            flightFromDB.setAvailableSeets(flightFromDB.getAvailableSeets()-nrOfPersons);
            flightFromDB.setTotalNrOfSeets(flight.getTotalNrOfSeets());
            flightFromDB.setDepartureDate(flight.getDepartureDate());

//            Airport airport = new Airport();
//            airport.setName(flight.getAirport().getName());
//            City city = new City();
//            city.setName(flight.getAirport().getCityAirports().getName());
//            airport.setCityAirports(city);
//            flightFromDB.setAirport(airport);
            hibernateUtil.session.persist(flightFromDB);
        }else throw new Exception();
    }
}
