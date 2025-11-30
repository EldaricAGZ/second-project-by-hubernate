package com.javarush.entity;

import com.javarush.entity.converter.SpecialFeaturesConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "film")
@NoArgsConstructor
@Getter
@Setter
@ToString
@SecondaryTable(
        name = "film_text",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "film_id")
)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer id;

    @Column(table = "film_text", name = "title", nullable = false)
    private String title;

    @Column(table = "film_text", name = "description")
    private String description;

    @Column(name = "release_year")
    private Short releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Language language;

    @Column(name = "rental_duration", columnDefinition = "TINYINT UNSIGNED")
    private Short rentalDuration;

    @Column(name = "rental_rate", columnDefinition = "DECIMAL(4, 2)")
    private BigDecimal rentalRate;

    @Column(columnDefinition = "SMALLINT UNSIGNED")
    private Integer length;

    @Column(name = "replacement_cost", columnDefinition = "DECIMAL(5, 2)")
    private BigDecimal replacementCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    @Convert(converter = SpecialFeaturesConverter.class)
    @Column(name = "special_features")
    private Set<String> specialFeatures;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
               joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
               inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_category",
                joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categoryList;

    public Film(Integer id, LocalDateTime lastUpdate, String title, BigDecimal rentalRate, String description, Short releaseYear, Language language, Short rentalDuration, Integer length, Rating rating, BigDecimal replacementCost, Set<String> specialFeatures, Set<Actor> actors) {
        this.id = id;
        this.lastUpdate = lastUpdate == null? LocalDateTime.now() : lastUpdate;
        this.title = title;
        this.rentalRate = rentalRate == null? BigDecimal.valueOf(4.99) : rentalRate;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.rentalDuration = rentalDuration == null? Short.valueOf("3") : rentalDuration;
        this.length = length;
        this.rating = rating == null? Rating.G : rating;
        this.replacementCost = replacementCost == null? BigDecimal.valueOf(19.99) : replacementCost;
        this.specialFeatures = specialFeatures;
        this.actors = actors;
    }
}
