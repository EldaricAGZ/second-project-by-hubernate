package com.javarush.dto;

import com.javarush.entity.Actor;
import com.javarush.entity.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class FilmDto {

    private String title;

    private String description;

    private Short releaseYear;

    private String language;

    private Short rentalDuration;

    private BigDecimal rentalRate;

    private Integer length;

    private BigDecimal replacementCost;

    private String rating;

    private Set<String> specialFeatures;

    private LocalDateTime lastUpdate;

    private Set<Actor> actors;

    private List<Category> categoryList;

}
