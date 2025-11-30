package com.javarush.dao;

import com.javarush.entity.Rental;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalRepository extends BaseRepository<Rental>{
    public RentalRepository() {
        super(Rental.class);
    }

    public Rental searchByCustomerAndFilm(Short idCustomer, Short idFilm, Session session) {
        try {
            String queryString = "from Rental r join fetch r.inventory i " +
                    "where r.customer.id = :idCustomer " +
                            "and i.film.id = :idFilm " +
                            "and r.returnDate is null";

            Query<Rental> query = session.createQuery(queryString, Rental.class);
            query.setParameter("idCustomer", idCustomer);
            query.setParameter("idFilm", idFilm);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}


