package com.javarush.dao;

import com.javarush.entity.Store;

public class StoreRepository extends BaseRepository<Store>{
    public StoreRepository() {
        super(Store.class);
    }
}
