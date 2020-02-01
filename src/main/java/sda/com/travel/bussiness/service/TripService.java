package sda.com.travel.bussiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.com.travel.frontend.dto.*;
import sda.com.travel.persistence.dao.FlightDAO;
import sda.com.travel.persistence.dao.HotelDAO;
import sda.com.travel.persistence.dao.TripDAO;
import sda.com.travel.persistence.dao.UserDAO;
import sda.com.travel.persistence.entity.*;

import java.util.ArrayList;
import java.util.List;

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

    public void insertTrip(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setNrOfDoubleRooms(tripDTO.getNrOfDoubleRooms());
        trip.setNrOfExtraBeds(tripDTO.getNrOfExtraBeds());
        trip.setNrOfSingleRooms(tripDTO.getNrOfSingleRooms());
        trip.setPromoted(tripDTO.isPromoted());
        trip.setType(tripDTO.getType());
        Flight flight = flightDAO.findFlightByFlightNumber(tripDTO.getDepartureDate().getFlightNumber());
        trip.setDepartureDate(flight);
        Flight flight1 = flightDAO.findFlightByFlightNumber(tripDTO.getReturnDate().getFlightNumber());
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
            tripDTO.setDepartureDate(departureDate);

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
            tripDTO.setDepartureDate(departureDate);

            tripDTO.setReturnDate(returnDate);

            tripDTOSList.add(tripDTO);
        }
        return tripDTOSList;
    }

    public List<TripDTO> findTrip(TripDTO tripDTO){
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
        departureDate.setDepartureDate(tripDTO.getDepartureDate().getDepartureDate());
        trip.setDepartureDate(departureDate);

        Flight returnDate = new Flight();
        returnDate.setDepartureDate(tripDTO.getReturnDate().getDepartureDate());
        trip.setReturnDate(returnDate);

        List<Trip> tripsList = tripDAO.findTrip(trip);
        List<TripDTO> tripDTOList = new ArrayList<>();

        for (Trip t: tripsList){
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
            AirportDTO airportDTO = new AirportDTO();
            airportDTO.setName(t.getDepartureDate().getAirport().getName());
            departureDate1.setAirportDTO(airportDTO);
            tripDTO1.setDepartureDate(departureDate1);

            FlightDTO returnDate1 = new FlightDTO();
            returnDate1.setAvailableSeets(t.getReturnDate().getAvailableSeets());
            returnDate1.setFlightPrice(t.getReturnDate().getFlightPrice());
            returnDate1.setDepartureDate(t.getReturnDate().getDepartureDate());
            AirportDTO airportDTO1 = new AirportDTO();
            airportDTO.setName(t.getReturnDate().getAirport().getName());
            returnDate1.setAirportDTO(airportDTO1);
            tripDTO1.setReturnDate(returnDate1);

            tripDTOList.add(tripDTO1);
        }
        return tripDTOList;
    }

    public void buyTrip(TripDTO tripDTO){
        Trip trip = tripDAO.findTripSingleResult(tripDTO.getHotelDTO().getName());
        User user = userDao.findUser(tripDTO.getTripDetailsDTOSet().);
    }
}
