package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.Hotel;
import sda.com.travel.persistence.entity.Room;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;

@Repository
public class RoomDAO {

    HibernateUtil hibernateUtil = new HibernateUtil();

    public void insertRoom(Room room){
        hibernateUtil.openSessionAndTransaction();
        hibernateUtil.session.persist(room);
        hibernateUtil.closeSessionAndTransaction();
    }

    public Room findRoomsByHotelName(String name){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_rooms");
        query.setParameter("name", name);
        Room room = (Room) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return room;

    }
}
