package com.javarush.entity;

import lombok.Getter;

@Getter
public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String label;

    Rating(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Rating fromDb(String value) {
        for (Rating r: values()) {
            if (r.getLabel().equals(value)) return r;
        }
        throw new IllegalArgumentException("Unknown rating: " + value);
    }
}
