package com.javarush.dao;

import com.javarush.entity.Customer;

public class CustomerRepository extends BaseRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
