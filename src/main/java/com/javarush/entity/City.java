package com.javarush.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "city",
       indexes = @Index(name = "idx_fk_country_id", columnList = "country_id"))
@NoArgsConstructor
@Setter
@Getter
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Short id;

    @Column(name = "city", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public City(Short id, String name, Country country, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}