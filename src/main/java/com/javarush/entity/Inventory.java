package com.javarush.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory",
        indexes = { @Index(name = "idx_fk_film_id", columnList = "film_id"),
                    @Index(name = "idx_store_id_film_id", columnList = "store_id, film_id")
                    })
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Inventory(Integer id, Film film, Store store, LocalDateTime lastUpdate) {
        this.id = id;
        this.film = film;
        this.store = store;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }

    public Inventory(Film film, Store store, LocalDateTime lastUpdate) {
        this.film = film;
        this.store = store;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}

