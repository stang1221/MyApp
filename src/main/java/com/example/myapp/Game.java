package com.example.myapp;

import java.util.Objects;

public class Game {

    private String title;
    private String system;
    private String developer;
    private String category;
    private double rating;
    private int year;

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", system='" + system + '\'' +
                ", developer='" + developer + '\'' +
                ", category='" + category + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(game.rating, rating) == 0 &&
                year == game.year &&
                title.equals(game.title) &&
                system.equals(game.system) &&
                developer.equals(game.developer) &&
                category.equals(game.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, system, developer, category, rating, year);
    }

    public Game(String title, String system, String developer, String category, double rating, int year) {
        this.title = title;
        this.system = system;
        this.developer = developer;
        this.category = category;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
