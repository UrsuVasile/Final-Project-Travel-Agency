package sda.com.travel.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.*;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TripDetailsDAO {
    HibernateUtil hibernateUtil = new HibernateUtil();

    @Autowired
    UserDAO userDAO;
    @Autowired
    TripDAO tripDAO;
    @Autowired
    FlightDAO flightDAO;

    public void insertTripDetails(TripDetails tripDetails) {
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(tripDetails);
        hibernateUtil.closeSessionAndTransaction();
    }

    public void buyTrip(Trip trip) throws Exception {
        hibernateUtil.openSessionAndTransaction();
        //System.out.println("nr zboooooorrrrrrr "+trip.getDepartureDate().getFlightNumber());
        System.out.println("nume hotellllllllllllll: "+trip.getTripHotel().getName());
        Trip trip2 = tripDAO.findTripSingleResult(trip.getTripHotel().getName());
        String emailUser = "user@yahoo.com";
        Query findUser = hibernateUtil.session.createNamedQuery("find_user_by_email");
        findUser.setParameter("email", emailUser);
        User user = (User) findUser.getSingleResult();
        TripDetails tripDetails = new TripDetails();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);

        tripDetails.setUserSet(userSet);

        tripDetails.setAmount(user.getTotalAmount());
        tripDetails.setTrip(trip2);

        hibernateUtil.session.persist(tripDetails);

        //calculez nr de persoane care vor participa in calatorie
        int nrOfPersons = (trip.getNrOfDoubleRooms()+2)+(trip.getNrOfSingleRooms()+1)+(trip.getNrOfExtraBeds()+1);

        //updatez Flight din Baza de date
        Flight departureDate = new Flight();
        departureDate.setFlightNumber(trip.getDepartureDate().getFlightNumber());
        departureDate.setAvailableSeets(trip.getDepartureDate().getAvailableSeets());
        departureDate.setDepartureDate(trip.getDepartureDate().getDepartureDate());
//        Airport airport = new Airport();
//        airport.setName(trip.getDepartureDate().getAirport().getName());
//        City city = new City();
//        city.setName(trip.getDepartureDate().getAirport().getCityAirports().getName());
//        airport.setCityAirports(city);
//        departureDate.setAirport(airport);


        flightDAO.updateFlight(departureDate, nrOfPersons);
        //flightDAO.updateFlight(trip.getReturnDate(), nrOfPersons);

        hibernateUtil.closeSessionAndTransaction();

    }

}
