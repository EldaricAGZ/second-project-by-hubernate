package com.javarush.util;

import com.javarush.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class SessionManager {

    private static SessionManager instance; // является singleton, так как хранить состояние между вызовами, т.е. является stateful
    private final SessionFactory sessionFactory;

    private static Lock lock = new ReentrantLock();

    public SessionManager() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .configure("hibernate.properties")
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        lock.lock();
        if (instance == null) instance = new SessionManager();
        lock.unlock();
        return instance.sessionFactory;
    }

}
