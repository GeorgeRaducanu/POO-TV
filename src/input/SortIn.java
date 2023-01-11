package input;

public class SortIn {
    private String rating;
    private String duration;

    public SortIn() {

    }

    public final String getRating() {
        return rating;
    }

    public final void setRating(final String rating) {
        this.rating = rating;
    }

    public final String getDuration() {
        return duration;
    }

    public final void setDuration(final String duration) {
        this.duration = duration;
    }

    @Override
    public final String toString() {
        return "SortIn{"
                +
                "rating='" + rating + '\''
                +
                ", duration='" + duration + '\''
                +
                '}';
    }
}
