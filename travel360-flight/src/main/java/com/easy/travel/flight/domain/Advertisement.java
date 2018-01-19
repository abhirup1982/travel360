package com.easy.travel.flight.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public class Advertisement {
    public enum Image {
        ADVENTURE_SPORTS("sports", "sports.jpg"), PETS_ACCESSORIES("pets", "pets.jpg"), ROMANTIC_HOLIDAY("romance", "romance.jpg");

        private String fileName;
        private String key;

        Image(String key, String fileName) {
            this.key = key;
            this.fileName = fileName;
        }

        @JsonValue
        public String toValue() {
            return this.fileName;
        }

    }

    public Image image;
    public String link;
    public String caption;
}

