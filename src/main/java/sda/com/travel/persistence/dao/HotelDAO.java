package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Hotel;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

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
}
