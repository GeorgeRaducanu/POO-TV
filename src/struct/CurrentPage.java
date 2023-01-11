package struct;

import java.util.ArrayList;

public class CurrentPage {
    private String pageName;
    private User currentUser;
    private ArrayList<Movie> moviesList = new ArrayList<Movie>();
    private Movie currentMovie = new Movie();

    public CurrentPage(final String pageName) {
        this.pageName = pageName;
    }
    private CurrentPage() {

    }
    private static CurrentPage instance = null;

    /**
     * Implementation of singleton pattern for the current page
     * @return
     */
    public static CurrentPage getInstance() {
        if (instance == null) {
            instance = new CurrentPage();
        }
        return instance;
    }

    public final String getPageName() {
        return pageName;
    }

    public final void setPageName(final String pageName) {
        this.pageName = pageName;
    }

    public final User getCurrentUser() {
        return currentUser;
    }

    public final void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public final ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public final void setMoviesList(final ArrayList<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public final Movie getCurrentMovie() {
        return currentMovie;
    }

    public final void setCurrentMovie(final Movie currentMovie) {
        this.currentMovie = currentMovie;
    }
}
