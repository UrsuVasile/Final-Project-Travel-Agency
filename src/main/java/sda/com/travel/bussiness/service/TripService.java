package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.*;
import sda.com.travel.persistence.dao.*;
import sda.com.travel.persistence.entity.*;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TripService {

    @Autowired
    TripDAO tripDAO;
    @Autowired
    FlightDAO flightDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    UserDAO userDao;
    @Autowired
    TripDetailsDAO tripDetailsDAO;
    @Autowired
    RoomDAO roomDAO;
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    //HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setNrOfDoubleRooms(tripDTO.getNrOfDoubleRooms());
        trip.setNrOfExtraBeds(tripDTO.getNrOfExtraBeds());
        trip.setNrOfSingleRooms(tripDTO.getNrOfSingleRooms());
        trip.setPromoted(tripDTO.isPromoted());
        trip.setType(tripDTO.getType());
        Flight flight = flightDAO.findFlightByFlightNumber(tripDTO.getDepartureFlightDTO().getFlightNumber());
        trip.setDepartureDate(flight);
        Flight flight1 = flightDAO.findFlightByFlightNumber(tripDTO.getReturnFlightDTO().getFlightNumber());
        trip.setReturnDate(flight1);
        Hotel hotel = hotelDAO.findHotelByNameWithSingleResult(tripDTO.getHotelDTO().getName());
        trip.setTripHotel(hotel);

        tripDAO.insertTrip(trip);
    }

    public List<TripDTO> findTripByHotelName(String name) {
        List<Trip> tripsList = tripDAO.findTripByHotelName(name);
        List<TripDTO> tripDTOSList = new ArrayList<>();

        for (Trip t : tripsList) {
            TripDTO tripDTO = new TripDTO();

            FlightDTO departureDate = new FlightDTO();
            departureDate.setDepartureDate(t.getDepartureDate().getDepartureDate());
            departureDate.setFlightPrice(t.getDepartureDate().getFlightPrice());
            departureDate.setAvailableSeets(t.getDepartureDate().getAvailableSeets());


            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(t.getDepartureDate().getAirport().getName());
            CityDTO cityDTO1 = new CityDTO();
            cityDTO1.setName(t.getDepartureDate().getAirport().getCityAirports().getName());

            CountryDTO countryDTO3 = new CountryDTO();
            countryDTO3.setCountryName(t.getDepartureDate().getAirport().getCityAirports().getCountry().getCountryName());
            cityDTO1.setCountryDTO(countryDTO3);
            airportDTO.setCityDTO(cityDTO1);
            departureDate.setAirportDTO(airportDTO);
            tripDTO.setDepartureFlightDTO(departureDate);

            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setName(t.getTripHotel().getName());
            hotelDTO.setDescription(t.getTripHotel().getDescription());
            hotelDTO.setStandard(t.getTripHotel().getStandard());
            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(t.getTripHotel().getCity().getName());
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(t.getTripHotel().getCity().getCountry().getCountryName());
            cityDTO.setCountryDTO(countryDTO);
            hotelDTO.setCityDTO(cityDTO);

            tripDTO.setHotelDTO(hotelDTO);
            tripDTO.setNrOfDoubleRooms(t.getNrOfDoubleRooms());
            tripDTO.setNrOfExtraBeds(t.getNrOfExtraBeds());
            tripDTO.setNrOfSingleRooms(t.getNrOfSingleRooms());
            tripDTO.setPromoted(t.isPromoted());
            tripDTO.setType(t.getType());

            FlightDTO returnDate = new FlightDTO();
            returnDate.setDepartureDate(t.getReturnDate().getDepartureDate());
            returnDate.setAvailableSeets(t.getReturnDate().getAvailableSeets());
            returnDate.setFlightPrice(t.getReturnDate().getFlightPrice());

            AirportDTO airportDTO1 = new AirportDTO();
            airportDTO1.setName(t.getReturnDate().getAirport().getName());

            CityDTO cityDTO2 = new CityDTO();
            cityDTO2.setName(t.getReturnDate().getAirport().getCityAirports().getName());

            CountryDTO countryDTO1 = new CountryDTO();
            countryDTO1.setCountryName(t.getReturnDate().getAirport().getCityAirports().getCountry().getCountryName());
            cityDTO2.setCountryDTO(countryDTO1);
            airportDTO1.setCityDTO(cityDTO2);
            returnDate.setAirportDTO(airportDTO1);
            tripDTO.setDepartureFlightDTO(departureDate);

            tripDTO.setReturnFlightDTO(returnDate);

            tripDTOSList.add(tripDTO);
        }
        return tripDTOSList;
    }

    public List<TripDTO> findTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setNrOfExtraBeds(tripDTO.getNrOfExtraBeds());
        trip.setNrOfDoubleRooms(tripDTO.getNrOfDoubleRooms());
        trip.setNrOfSingleRooms(tripDTO.getNrOfSingleRooms());

        City city = new City();
        city.setName(tripDTO.getHotelDTO().getCityDTO().getName());
        Hotel hotel = new Hotel();
        hotel.setName(tripDTO.getHotelDTO().getName());
        hotel.setCity(city);

        trip.setTripHotel(hotel);

        Flight departureDate = new Flight();
        departureDate.setDepartureDate(tripDTO.getDepartureFlightDTO().getDepartureDate());
        departureDate.setFlightNumber(tripDTO.getDepartureFlightDTO().getFlightNumber());
        trip.setDepartureDate(departureDate);

        Flight returnDate = new Flight();
        returnDate.setDepartureDate(tripDTO.getReturnFlightDTO().getDepartureDate());
        returnDate.setFlightNumber(tripDTO.getReturnFlightDTO().getFlightNumber());
        trip.setReturnDate(returnDate);

        List<Trip> tripsList = tripDAO.findTrip(trip);
        List<TripDTO> tripDTOList = new ArrayList<>();

        //System.out.println("zborrrrrrrrrrrrrrrrrrrrrr "+tripsList.get(0).getDepartureDate().getAvailableSeets());
        for (Trip t : tripsList) {

            TripDTO tripDTO1 = new TripDTO();
            tripDTO1.setType(t.getType());
            tripDTO1.setPromoted(t.isPromoted());
            tripDTO1.setNrOfDoubleRooms(t.getNrOfDoubleRooms());
            tripDTO1.setNrOfSingleRooms(t.getNrOfSingleRooms());
            tripDTO1.setNrOfExtraBeds(t.getNrOfExtraBeds());

            HotelDTO hotelDTO = new HotelDTO();
            hotelDTO.setStandard(t.getTripHotel().getStandard());
            hotelDTO.setDescription(t.getTripHotel().getDescription());
            hotelDTO.setName(t.getTripHotel().getName());

            CityDTO cityDTO = new CityDTO();
            cityDTO.setName(t.getTripHotel().getCity().getName());

            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountryName(t.getTripHotel().getCity().getCountry().getCountryName());
            cityDTO.setCountryDTO(countryDTO);
            hotelDTO.setCityDTO(cityDTO);
            tripDTO1.setHotelDTO(hotelDTO);

            FlightDTO departureDate1 = new FlightDTO();
            departureDate1.setAvailableSeets(t.getDepartureDate().getAvailableSeets());
            departureDate1.setFlightPrice(t.getDepartureDate().getFlightPrice());
            departureDate1.setDepartureDate(t.getDepartureDate().getDepartureDate());
            departureDate1.setFlightNumber(t.getDepartureDate().getFlightNumber());

            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(t.getDepartureDate().getAirport().getName());
            departureDate1.setAirportDTO(airportDTO);
            tripDTO1.setDepartureFlightDTO(departureDate1);


            FlightDTO returnDate1 = new FlightDTO();
            returnDate1.setAvailableSeets(t.getReturnDate().getAvailableSeets());
            returnDate1.setFlightPrice(t.getReturnDate().getFlightPrice());
            returnDate1.setDepartureDate(t.getReturnDate().getDepartureDate());
            returnDate1.setFlightNumber(t.getReturnDate().getFlightNumber());
            AirportDTO airportDTO1 = new AirportDTO();
            airportDTO.setName(t.getReturnDate().getAirport().getName());
            returnDate1.setAirportDTO(airportDTO1);
            tripDTO1.setReturnFlightDTO(returnDate1);

            tripDTOList.add(tripDTO1);
        }
        return tripDTOList;
    }

    public String buyTrip(TripDTO tripDTO) throws Exception {
        //deschid sesiunea si tranzactia
        hibernateUtil.openSessionAndTransaction();
        //transfer datele din TripDTO in Trip
        Trip trip = transferTripDTOFieldsInTripObject(tripDTO);

        //calculez nr de persoane care vor participa in calatorie
        int nrOfPersons = ((trip.getNrOfDoubleRooms() + 2) + (trip.getNrOfSingleRooms() + 1) + (trip.getNrOfExtraBeds() + 1)) / 2;

        //calculez costul total al calatoriei
        Trip purchasedTrip = tripDAO.findTripSingleResult(tripDTO.getHotelDTO().getName());
        int totalAmount = (purchasedTrip.getTripHotel().getRoom().getPriceForSingleRoom()*purchasedTrip.getNrOfSingleRooms())+(purchasedTrip.getTripHotel().getRoom().getPriceForDoubleRoom()*purchasedTrip.getNrOfDoubleRooms())+(purchasedTrip.getTripHotel().getRoom().getPriceForExtraBed()*purchasedTrip.getNrOfExtraBeds());
        String totalAmountOfTrip = "Total amount of Trip is "+totalAmount+" Euro";
        System.out.println(totalAmountOfTrip);

        //transfer datele din TripDTO intr-un obiect Flight pt a putea accesa metoda updateFlight din FlightDAO
        Flight departureFlight = transferDataFromTripDTOInFlightObject(tripDTO);


        //fac insert la TripDetails
        TripDetails tripDetails = getTripDetailsFromTrip(trip);
        tripDetailsDAO.insertTripDetails(tripDetails);

        //fac update la availableSeets din Flight
        flightDAO.updateFlight(departureFlight, nrOfPersons);

        //fac update la user pt a recalcula totalAmount per User
        userDao.updateUser(tripDetails, totalAmount);

        //fac update la camerele din Hotelul ales (scad camerele din Trip din nr total de camere disponibile)
        roomDAO.updateRooms(tripDetails);

        //Inchid sesiunea si tranzactia, ca sa nu le mai caut prin DAO
        HibernateUtil.getInstance().closeSessionAndTransaction();

        return totalAmountOfTrip;

    }

    private TripDetails getTripDetailsFromTrip(Trip trip) {

        Trip trip2 = tripDAO.findTripSingleResult(trip.getTripHotel().getName());
        //String emailUser = "user@yahoo.com";
        Query findUser = HibernateUtil.getInstance().session.createQuery("select s from User s where s.email='user@yahoo.com'");
        //findUser.setParameter("email", emailUser);
        User user = (User) findUser.getSingleResult();
        TripDetails tripDetails = new TripDetails();
        Set<User> userSet = new HashSet<>();
        userSet.add(user);

        tripDetails.setUserSet(userSet);

        Date date = new Date(Calendar.getInstance().getTime().getTime());
        tripDetails.setTripPurchasedDate(date);
        
        tripDetails.setAmount(user.getTotalAmount());
        tripDetails.setTrip(trip2);

        return tripDetails;
    }

    private Flight transferDataFromTripDTOInFlightObject(TripDTO tripDTO) {
        Flight departureFlight = new Flight();
        departureFlight.setAvailableSeets(tripDTO.getDepartureFlightDTO().getAvailableSeets());
        departureFlight.setFlightPrice(tripDTO.getDepartureFlightDTO().getFlightPrice());
        departureFlight.setFlightNumber(tripDTO.getDepartureFlightDTO().getFlightNumber());
        departureFlight.setDepartureDate(tripDTO.getDepartureFlightDTO().getDepartureDate());
        departureFlight.setTotalNrOfSeets(tripDTO.getDepartureFlightDTO().getTotalNrOfSeets());
        Airport airport = new Airport();
        airport.setName(tripDTO.getDepartureFlightDTO().getAirportDTO().getName());
        City city = new City();
        city.setName(tripDTO.getDepartureFlightDTO().getAirportDTO().getCityDTO().getName());
        Country country = new Country();
        country.setCountryName(tripDTO.getDepartureFlightDTO().getAirportDTO().getCityDTO().getCountryDTO().getCountryName());
        city.setCountry(country);
        airport.setCityAirports(city);
        departureFlight.setAirport(airport);

        return departureFlight;
    }

    private Trip transferTripDTOFieldsInTripObject(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setNrOfSingleRooms(tripDTO.getNrOfSingleRooms());
        trip.setNrOfDoubleRooms(tripDTO.getNrOfDoubleRooms());
        trip.setNrOfExtraBeds(tripDTO.getNrOfExtraBeds());
        trip.setType(tripDTO.getType());
        trip.setPromoted(tripDTO.isPromoted());

        Hotel hotel = new Hotel();
        hotel.setName(tripDTO.getHotelDTO().getName());
        City city = new City();
        city.setName(tripDTO.getHotelDTO().getCityDTO().getName());
        Country country = new Country();
        country.setCountryName(tripDTO.getHotelDTO().getCityDTO().getCountryDTO().getCountryName());
        city.setCountry(country);
        hotel.setCity(city);
        trip.setTripHotel(hotel);

        Flight departureDate = new Flight();
        departureDate.setDepartureDate(tripDTO.getDepartureFlightDTO().getDepartureDate());
        departureDate.setFlightNumber(tripDTO.getDepartureFlightDTO().getFlightNumber());
        departureDate.setFlightPrice(tripDTO.getDepartureFlightDTO().getFlightPrice());
        departureDate.setAvailableSeets(tripDTO.getDepartureFlightDTO().getAvailableSeets());
        Airport airport = new Airport();
        airport.setName(tripDTO.getDepartureFlightDTO().getAirportDTO().getName());
        departureDate.setAirport(airport);

        trip.setDepartureDate(departureDate);

        Flight returnDate = new Flight();
        returnDate.setDepartureDate(tripDTO.getReturnFlightDTO().getDepartureDate());
        returnDate.setFlightNumber(tripDTO.getReturnFlightDTO().getFlightNumber());
        returnDate.setFlightPrice(tripDTO.getReturnFlightDTO().getFlightPrice());
        returnDate.setAvailableSeets(tripDTO.getReturnFlightDTO().getAvailableSeets());
        Airport airport1 = new Airport();
        airport1.setName(tripDTO.getReturnFlightDTO().getAirportDTO().getName());
        returnDate.setAirport(airport1);

        trip.setReturnDate(returnDate);
        return trip;
    }

    public List<TripDTO> findTripsByCountries(String countryName){
                //iau lista de Trip-uri din DAO
        List<Trip> tripsList = tripDAO.findTripsByCountries(countryName);
        List<TripDTO> tripDTOSList = new ArrayList<>();

        //transfer datele dintr-un obiect Trip in TripDTO si il adaug in lista, pe care o returnez
        for (Trip t: tripsList){
            TripDTO tripDTO1 = transferTripFieldsInTripDTOObject(t);

            tripDTOSList.add(tripDTO1);
        }
        return tripDTOSList;
    }

    public TripDTO transferTripFieldsInTripDTOObject(Trip trip){
        TripDTO tripDTO = new TripDTO();
        tripDTO.setNrOfExtraBeds(trip.getNrOfExtraBeds());
        tripDTO.setNrOfSingleRooms(trip.getNrOfSingleRooms());
        tripDTO.setNrOfDoubleRooms(trip.getNrOfDoubleRooms());
        tripDTO.setPromoted(trip.isPromoted());
        tripDTO.setType(trip.getType());

        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setName(trip.getTripHotel().getName());
        hotelDTO.setDescription(trip.getTripHotel().getDescription());
        hotelDTO.setStandard(trip.getTripHotel().getStandard());
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(trip.getTripHotel().getCity().getName());
        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setCountryName(trip.getTripHotel().getCity().getCountry().getCountryName());
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(trip.getTripHotel().getCity().getCountry().getContinent().getName());
        countryDTO.setContinentDTO(continentDTO);
        cityDTO.setCountryDTO(countryDTO);
        hotelDTO.setCityDTO(cityDTO);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setPriceForSingleRoom(trip.getTripHotel().getRoom().getPriceForSingleRoom());
        roomDTO.setPriceForExtraBed(trip.getTripHotel().getRoom().getPriceForExtraBed());
        roomDTO.setPriceForDoubleRoom(trip.getTripHotel().getRoom().getPriceForDoubleRoom());

        hotelDTO.setRoomDTO(roomDTO);
        tripDTO.setHotelDTO(hotelDTO);

        FlightDTO departureFlight = new FlightDTO();
        departureFlight.setFlightNumber(trip.getDepartureDate().getFlightNumber());
        departureFlight.setDepartureDate(trip.getDepartureDate().getDepartureDate());
        departureFlight.setFlightPrice(trip.getDepartureDate().getFlightPrice());
        departureFlight.setAvailableSeets(trip.getDepartureDate().getAvailableSeets());
        departureFlight.setTotalNrOfSeets(trip.getDepartureDate().getTotalNrOfSeets());

        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setName(trip.getDepartureDate().getAirport().getName());
        CityDTO cityDTO1 = new CityDTO();
        cityDTO1.setName(trip.getDepartureDate().getAirport().getCityAirports().getName());
        airportDTO.setCityDTO(cityDTO1);
        departureFlight.setAirportDTO(airportDTO);
        tripDTO.setDepartureFlightDTO(departureFlight);

        FlightDTO returnFlight = new FlightDTO();
        returnFlight.setFlightNumber(trip.getDepartureDate().getFlightNumber());
        returnFlight.setDepartureDate(trip.getDepartureDate().getDepartureDate());
        returnFlight.setFlightPrice(trip.getDepartureDate().getFlightPrice());
        returnFlight.setAvailableSeets(trip.getDepartureDate().getAvailableSeets());
        returnFlight.setTotalNrOfSeets(trip.getDepartureDate().getTotalNrOfSeets());

        AirportDTO airportDTO1 = new AirportDTO();
        airportDTO1.setName(trip.getReturnDate().getAirport().getName());
        CityDTO cityDTO2 = new CityDTO();
        cityDTO2.setName(trip.getReturnDate().getAirport().getCityAirports().getName());
        airportDTO1.setCityDTO(cityDTO2);
        returnFlight.setAirportDTO(airportDTO1);
        tripDTO.setReturnFlightDTO(returnFlight);

        return tripDTO;
    }

    /**
     *
     * @param isPromoted
     * @return
     */
    public List<TripDTO> findTripIfIsPromoted(boolean isPromoted){
        List<Trip> tripsList = tripDAO.findTripIfIsPromoted(isPromoted);
        List<TripDTO> tripDTOSList = new ArrayList<>();

        for (Trip t: tripsList){
            TripDTO tripDTO = transferTripFieldsInTripDTOObject(t);
            tripDTOSList.add(tripDTO);
        }
        return tripDTOSList;
    }

    public List<TripDTO> findTripByType(String type){
        List<Trip> tripsList = tripDAO.findTripByType(type);
        List<TripDTO> tripDTOSList = new ArrayList<>();

        for (Trip t: tripsList){
            TripDTO tripDTO = transferTripFieldsInTripDTOObject(t);
            tripDTOSList.add(tripDTO);
        }
        return tripDTOSList;
    }

    public List<TripDTO> findTripByDepartureFlight(Date departureFlight){
        List<Trip>tripsList = tripDAO.findTripByDepartureFlight(departureFlight);
        List<TripDTO> tripDTOSList = new ArrayList<>();

        for (Trip t: tripsList){
            TripDTO tripDTO = transferTripFieldsInTripDTOObject(t);
            tripDTOSList.add(tripDTO);
        }
        return tripDTOSList;
    }
}
