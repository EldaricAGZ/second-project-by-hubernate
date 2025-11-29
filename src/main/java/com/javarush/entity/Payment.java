package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment",
        indexes = {
        @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
                @Index(name = "idx_fk_staff_id", columnList = "staff_id")
        })
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Short id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @Column(name = "amount", columnDefinition = "DECIMAL(5, 2)", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Payment(Short id, Customer customer, Staff staff, Rental rental, BigDecimal amount, LocalDateTime paymentDate, LocalDateTime lastUpdate) {
        this.id = id;
        this.customer = customer;
        this.staff = staff;
        this.rental = rental;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }

    public Payment(Customer customer, Staff staff, Rental rental, BigDecimal amount, LocalDateTime paymentDate, LocalDateTime lastUpdate) {
        this.customer = customer;
        this.staff = staff;
        this.rental = rental;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}
