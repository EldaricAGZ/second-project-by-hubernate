package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Short id;

    @Column(name = "country", nullable = false, length = 50)
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Country(Short id, String name, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
    }
}