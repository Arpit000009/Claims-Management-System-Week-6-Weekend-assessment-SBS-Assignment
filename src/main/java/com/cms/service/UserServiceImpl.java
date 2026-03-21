package com.cms.service;

import com.cms.entity.User;
import com.cms.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser(User user) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(user);

        tx.commit();
        session.close();
    }

    @Override
    public User login(String username, String password) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        User dbUser = session.createQuery(
                "FROM User WHERE username = :username", User.class)
                .setParameter("username", username)
                .uniqueResult();

        session.close();

        if (dbUser == null) return null;
        
        System.out.println("INPUT password: " + password);
        System.out.println("DB password: " + dbUser.getPassword());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        
        if (encoder.matches(password, dbUser.getPassword())) {
            return dbUser;
        }

        return null;
    }
}