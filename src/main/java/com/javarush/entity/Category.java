package com.javarush.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Short id;

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categoryList")
    private List<Film> films;

    public Category(Short id, String name, LocalDateTime lastUpdate, List<Film> films) {
        this.id = id;
        this.name = name;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
        this.films = films;
    }
}
