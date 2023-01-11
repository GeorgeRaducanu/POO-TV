package input;

import java.util.ArrayList;

public class ContainsIn {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    public ContainsIn() {

    }

    public final ArrayList<String> getActors() {
        return actors;
    }

    public final void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public final ArrayList<String> getGenre() {
        return genre;
    }

    public final void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }

    @Override
    public final String toString() {
        return "ContainsIn{"
                +
                "actors=" + actors
                +
                ", genre=" + genre
                +
                '}';
    }
}
