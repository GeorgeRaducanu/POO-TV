package struct;

public class Notifications {
    private String movieName;
    private String message;

    public final String getMovieName() {
        return movieName;
    }

    public final void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public final String getMessage() {
        return message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor for Notifications class
     * @param movieName
     * @param message
     */
    public Notifications(final String movieName, final String message) {
        this.message = message;
        this.movieName = movieName;
    }
}
