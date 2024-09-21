package com.example.gamesystemmanagement.models;

import com.example.gamesystemmanagement.controllers.MyLinkedList;

import java.util.Objects;

public class Game {
    private String name;
    private String publisher;
    private String description;
    private String developer;
    private String machineDevelopedFor;
    private int releaseYear;
    private String coverImageURL;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return releaseYear == game.releaseYear && name.equals(game.name) && publisher.equals(game.publisher) && description.equals(game.description) && developer.equals(game.developer) && machineDevelopedFor.equals(game.machineDevelopedFor) && coverImageURL.equals(game.coverImageURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, publisher, description, developer, machineDevelopedFor, releaseYear, coverImageURL);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getMachineDevelopedFor() {
        return machineDevelopedFor;
    }

    public void setMachineDevelopedFor(String machineDevelopedFor) {
        this.machineDevelopedFor = machineDevelopedFor;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    public static void selectionSortByReleaseYear(MyLinkedList<Game> games) {
        int n = games.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Compare based on release year
                if (games.get(j).getReleaseYear() < games.get(minIndex).getReleaseYear()) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the element at i
            Game temp = games.get(minIndex);
            games.set(minIndex, games.get(i));
            games.set(i, temp);
        }
    }

    public Game(String text, String gameGenreText, String gamePriceText, String gameRatingText, String machineDevelopedForText, int i, String coverImageURLText) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.developer = developer;
        this.machineDevelopedFor = machineDevelopedFor;
        this.releaseYear = releaseYear;
        this.coverImageURL = coverImageURL;
    }
}
