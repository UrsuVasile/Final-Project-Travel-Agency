package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.User;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;


@Repository
public class UserDAO {
    HibernateUtil hibernateUtil = new HibernateUtil();

    public User findUser (String email){
        hibernateUtil.openSessionAndTransaction();
        Query query = hibernateUtil.session.createNamedQuery("find_user_by_email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();
        hibernateUtil.closeSessionAndTransaction();
        return user;
    }
}
