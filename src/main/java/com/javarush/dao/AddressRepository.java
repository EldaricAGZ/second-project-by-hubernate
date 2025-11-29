package com.javarush.dao;

import com.javarush.entity.Address;

public class AddressRepository extends BaseRepository<Address>{
    public AddressRepository() {
        super(Address.class);
    }
}
