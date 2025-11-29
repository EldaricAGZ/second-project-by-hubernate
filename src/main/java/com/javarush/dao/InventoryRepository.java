package com.javarush.dao;

import com.javarush.entity.Inventory;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

public class InventoryRepository extends BaseRepository<Inventory>{
    protected InventoryRepository() {
        super(Inventory.class);
    }

    public Inventory tryIsFreeFilm(Session session) {
        try {
            String queryString = "SELECT * " +
                    "FROM inventory i LEFT JOIN rental r ON i.inventory_id = r.inventory_id " +
                    "AND r.rental_date = (SELECT MAX(re.rental_date) " +
                                         "FROM rental re " +
                                         "WHERE re.inventory_id = i.inventory_id) " +
                    "WHERE r.return_date IS NOT NULL OR r.inventory_id IS NULL " +
                    "LIMIT 1";
            return session.createNativeQuery(queryString, Inventory.class)
                    .uniqueResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
