package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "store",
       indexes = @Index(name = "idx_fk_address_id", columnList = "address_id"))
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Short id;

    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false, unique = true)
    private Staff manager;

    @OneToMany(mappedBy = "store")
    private List<Staff> staffList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Store(Short id, Staff manager, List<Staff> staffList, Address address, LocalDateTime lastUpdate) {
        this.id = id;
        this.manager = manager;
        this.staffList = staffList;
        this.address = address;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}
