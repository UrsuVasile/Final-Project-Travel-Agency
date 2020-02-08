package sda.com.travel.persistence.dao;

import org.springframework.stereotype.Repository;
import sda.com.travel.persistence.entity.TripDetails;
import sda.com.travel.persistence.entity.User;
import sda.com.travel.utils.config.HibernateUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class UserDAO {
    HibernateUtil hibernateUtil = HibernateUtil.getInstance();

    public User findUser(String email) {

        Query query = hibernateUtil.session.createNamedQuery("find_user_by_email");
        query.setParameter("email", email);
        User user = (User) query.getSingleResult();

        return user;
    }

    public void updateUser(TripDetails tripDetails, int totalAmount) {
        Query query = hibernateUtil.session.createNamedQuery("countUser");
        Set<User> userSet = tripDetails.getUserSet();
        List<User> userList = new ArrayList<>(userSet);

        query.setParameter("email", userList.get(0).getEmail());
        Long countUser = (Long) query.getSingleResult();

        if (countUser == 1) {
            User user = new User();
            Query query1 = hibernateUtil.session.createNamedQuery("find_user_by_email");

            query1.setParameter("email", userList.get(0).getEmail());

            user = (User) query1.getSingleResult();

            user.setEmail(user.getEmail());
            user.setFirsName(user.getFirsName());
            user.setLastName(user.getLastName());
            user.setId(user.getId());
            user.setPassword(user.getEmail());
            user.setTotalAmount(user.getTotalAmount() + totalAmount);
            hibernateUtil.session.persist(user);

        } else {
            try {
                throw new Exception("Error! User could not be updated! countUser !=1");
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }
}
