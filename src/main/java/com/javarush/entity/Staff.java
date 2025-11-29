package com.javarush.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "staff",
        indexes = { @Index(name = "idx_fk_address_id", columnList = "address_id"),
                    @Index(name = "idx_fk_store_id", columnList = "store_id")}
       )
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Short id;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "picture")
    private Blob picture;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "active", nullable = false)
    private Short active;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Staff(Short id, String password, String username, Short active, Store store, String email, Blob picture, Address address, String lastName, String firstName, LocalDateTime lastUpdate) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.active = active;
        this.store = store;
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}

