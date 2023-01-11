package input;

public class ActionsIn {
    private String type;
    private String page;
    private String feature;

    private String startsWith;
    private CredentialsIn credentials;
    private FiltersIn filters;
    private String movie;
    private ContainsIn contains;
    private String objectType;
    private int rate;
    private int count;

    private String deletedMovie;

    private MovieIn addedMovie;

    private String subscribedGenre;

    public final String getDeletedMovie() {
        return deletedMovie;
    }

    public final void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    public final MovieIn getAddedMovie() {
        return addedMovie;
    }

    public final void setAddedMovie(final MovieIn addedMovie) {
        this.addedMovie = addedMovie;
    }

    public final String getSubscribedGenre() {
        return subscribedGenre;
    }

    public final void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public ActionsIn() {

    }

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final String getPage() {
        return page;
    }

    public final void setPage(final String page) {
        this.page = page;
    }

    public final String getFeature() {
        return feature;
    }

    public final void setFeature(final String feature) {
        this.feature = feature;
    }

    public final CredentialsIn getCredentials() {
        return credentials;
    }

    public final ContainsIn getContains() {
        return contains;
    }

    public final void setContains(final ContainsIn contains) {
        this.contains = contains;
    }

    public final void setCredentials(final CredentialsIn credentials) {
        this.credentials = credentials;
    }

    public final String getStartsWith() {
        return startsWith;
    }

    public final void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public final FiltersIn getFilters() {
        return filters;
    }

    public final void setFilters(final FiltersIn filters) {
        this.filters = filters;
    }

    public final String getMovie() {
        return movie;
    }

    public final void setMovie(final String movie) {
        this.movie = movie;
    }

    public final String getObjectType() {
        return objectType;
    }

    public final void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public final int getRate() {
        return rate;
    }

    public final void setRate(final int rate) {
        this.rate = rate;
    }

    public final int getCount() {
        return count;
    }

    public final void setCount(final int count) {
        this.count = count;
    }

    @Override
    public final String toString() {
        return "ActionsIn{"
                +
                "type='" + type + '\''
                +
                ", page='" + page + '\''
                +
                ", feature='" + feature + '\''
                +
                ", startsWith='" + startsWith + '\''
                +
                ", credentials=" + credentials
                +
                ", filters=" + filters
                +
                ", movie='" + movie + '\''
                +
                ", contains=" + contains
                +
                ", objectType='" + objectType + '\''
                +
                ", rate=" + rate
                +
                ", count=" + count
                +
                '}';
    }
}
