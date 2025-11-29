package com.javarush.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental",
            indexes = {
                @Index(name = "idx_fk_customer_id", columnList = "customer_id"),
                    @Index(name = "idx_fk_inventory_id", columnList = "inventory_id"),
                    @Index(name = "idx_fk_staff_id", columnList = "staff_id")
            },
            uniqueConstraints = @UniqueConstraint(
                name = "rental_date",
                columnNames = {"rental_date", "inventory_id", "customer_id"}
            ))
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Integer id;

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Rental(Integer id, LocalDateTime rentalDate, Inventory inventory, Customer customer, LocalDateTime returnDate, Staff staff, LocalDateTime lastUpdate) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.inventory = inventory;
        this.customer = customer;
        this.returnDate = returnDate;
        this.staff = staff;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }

    public Rental(LocalDateTime rentalDate, Inventory inventory, Customer customer, LocalDateTime returnDate, Staff staff, LocalDateTime lastUpdate) {
        this.rentalDate = rentalDate;
        this.inventory = inventory;
        this.customer = customer;
        this.returnDate = returnDate;
        this.staff = staff;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}