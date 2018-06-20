package services;


import datastructure.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.List;


public class UsersServices {

    private SessionFactory sessionFactory;

    public UsersServices() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        User user = null;

        Query query = session.createQuery("SELECT u FROM User u WHERE u.username=:username");
        query.setParameter("username", username);
        List<User> userList = query.list();
        if(!userList.isEmpty()) {
            user = userList.get(0);
        }
        trx.commit();
        session.close();

        return user;
    }

    public User findByPassword(String pass) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        User user= null;

        Query query = session.createQuery("SELECT u FROM User u WHERE u.password=:password");
        query.setParameter("password", pass);
        List<User> userList = query.list();
        if(!userList.isEmpty()) {
            user = userList.get(0);
        }

        trx.commit();
        session.close();

        return user;
    }
}
