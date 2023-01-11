package input;

import java.util.ArrayList;

public class Input {

    private ArrayList<UserIn> users;
    private  ArrayList<MovieIn> movies;
    private ArrayList<ActionsIn> actions;

    private static Input instance = null;

    private Input() {
    }

    /**
     * Implementation of sinlgeton pattern
     * @return
     */
    public static Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }

    public final ArrayList<UserIn> getUsers() {
        return users;
    }

    public final void setUsers(final ArrayList<UserIn> users) {
        this.users = users;
    }

    public final ArrayList<MovieIn> getMovies() {
        return movies;
    }

    public final void setMovies(final ArrayList<MovieIn> movies) {
        this.movies = movies;
    }

    public final ArrayList<ActionsIn> getActions() {
        return actions;
    }

    public final void setActions(final ArrayList<ActionsIn> actions) {
        this.actions = actions;
    }

    @Override
    public final String toString() {
        return "Input{"
                +
                "users=" + users
                +
                ", movies=" + movies
                +
                ", actions=" + actions
                +
                '}';
    }
}
