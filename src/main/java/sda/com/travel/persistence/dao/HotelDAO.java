package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Hotel;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

@Repository
public class HotelDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertHotel(Hotel hotel){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(hotel);
        hibernateUtil.closeSessionAndTransaction();
    }

    public void deleteHotel(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("delete_hotel_by_name");
        query.setParameter("name", name);
        query.executeUpdate();
        hibernateUtil.closeSessionAndTransaction();
    }

    public List<Hotel> findHotelByName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_hotel_by_name");
        query.setParameter("name", name);
        List<Hotel> hotelsList = query.getResultList();
        hibernateUtil.closeSessionAndTransaction();
        return hotelsList;
    }
}
