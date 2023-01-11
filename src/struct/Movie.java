package struct;

import input.MovieIn;

import java.util.ArrayList;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    private int numLikes;

    private double rating;

    private int numRatings;

    private int trueNumRatings;

    /**
     * Movie constructor
     * @param movie
     */
    public Movie(final MovieIn movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = new ArrayList<String>();
        for (String iterate : movie.getGenres()) {
            this.genres.add(iterate);
        }
        this.actors = new ArrayList<String>();
        for (String iterate : movie.getActors()) {
            this.actors.add(iterate);
        }
        this.countriesBanned = new ArrayList<String>();
        for (String iterate : movie.getCountriesBanned()) {
            this.countriesBanned.add(iterate);
        }
    }

    public Movie() {

    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final int getYear() {
        return year;
    }

    public final void setYear(final int year) {
        this.year = year;
    }

    public final int getDuration() {
        return duration;
    }

    public final void setDuration(final int duration) {
        this.duration = duration;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public final ArrayList<String> getActors() {
        return actors;
    }

    public final void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public final ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public final void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public final int getNumLikes() {
        return numLikes;
    }

    public final void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public final double getRating() {
        return rating;
    }

    public final void setRating(final double rating) {
        this.rating = rating;
    }

    public final int getNumRatings() {
        return numRatings;
    }

    public final void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public final int getTrueNumRatings() {
        return trueNumRatings;
    }

    public final void setTrueNumRatings(final int trueNumRatings) {
        this.trueNumRatings = trueNumRatings;
    }

    @Override
    public final String toString() {
        return "MovieIn{"
                +
                "name='" + name + '\''
                +
                ", year=" + year + '\''
                +
                ", duration=" + duration + '\''
                +
                ", genres=" + genres + '\''
                +
                ", actors=" + actors + '\''
                +
                ", countriesBanned=" + countriesBanned
                +
                '}';
    }
}
