package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer",
        indexes = { @Index(name = "idx_fk_address_id", columnList = "address_id"),
                    @Index(name = "idx_fk_store_id", columnList = "store_id"),
                    @Index(name = "idx_last_name", columnList = "last_name")
        })
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "active")
    private Short active;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_date")
    private LocalDateTime lastUpdate;

    public Customer(Short id, LocalDateTime lastUpdate, LocalDateTime createDate, Short active, Address address, String email, String lastName, String firstName, Store storeSet) {
        this.id = id;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
        this.createDate = createDate;
        this.active = active == null? 1 : active;
        this.address = address;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.store = store;
    }
}

