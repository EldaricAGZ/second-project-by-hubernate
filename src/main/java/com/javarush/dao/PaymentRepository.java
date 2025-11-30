package com.javarush.dao;

import com.javarush.entity.Payment;

public class PaymentRepository extends BaseRepository<Payment>{
    public PaymentRepository() {
        super(Payment.class);
    }
}
