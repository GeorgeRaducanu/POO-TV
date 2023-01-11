package struct;

import input.ActionsIn;
import input.CredentialsIn;
import input.UserIn;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class User implements Observer {

    private final int magicNumber = 15;
    private Credentials credentials = new Credentials();

    private int tokensCount;

    private int numFreePremiumMovies = this.magicNumber;

    private ArrayList<Movie> purchasedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> watchedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> likedMovies = new ArrayList<Movie>();

    private ArrayList<Movie> ratedMovies = new ArrayList<Movie>();

    private  ArrayList<Movie> currentMoviesList = new ArrayList<Movie>();

    private Stack<ActionsIn> stackActions = new Stack<>();

    private ArrayList<Notifications> notifications = new ArrayList<>();

    private ArrayList<String> subscribedGenre = new ArrayList<>();


    /**
     * Constructor for User from UserIn
     * @param user
     */
    public User(final UserIn user) {
        this.credentials = new Credentials(user.getCredentials());
        this.numFreePremiumMovies = this.magicNumber;
    }

    /**
     * Constructor for User from User
     * @param user
     */
    public User(final User user) {

        // Builder utilizations
        this.credentials = new Credentials.Builder(user.getCredentials().getName(),
                                                    user.getCredentials().getPassword())
                .addAccountType(user.getCredentials().getAccountType())
                .addCountry(user.getCredentials().getCountry())
                .addBalance(user.getCredentials().getBalance()).build();

        //this.credentials = new Credentials(user.getCredentials());
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.tokensCount = user.getTokensCount();
        this.purchasedMovies = new ArrayList<>();
        for (Movie it : user.getPurchasedMovies()) {
            this.purchasedMovies.add(it);
        }
        this.watchedMovies = new ArrayList<>();
        for (Movie it : user.getWatchedMovies()) {
            this.watchedMovies.add(it);
        }
        this.likedMovies = new ArrayList<>();
        for (Movie it : user.getLikedMovies()) {
            this.likedMovies.add(it);
        }
        this.ratedMovies = new ArrayList<>();
        for (Movie it : user.getRatedMovies()) {
            this.ratedMovies.add(it);
        }
        this.credentials.setAccountType("standard");
    }

    /**
     * Constructor for User from Credentials
     * @param cred
     */
    public User(final Credentials cred) {
        this.credentials = new Credentials(cred);
        this.numFreePremiumMovies = this.magicNumber;

    }

    public User() {

    }

    /**
     * Variant of constructor for user
     * @param name
     * @param password
     * @param accountType
     * @param country
     * @param balance
     */
    public User(final String name, final String password,
                final String accountType, final String country,
                final int balance) {
        this.credentials.setName(name);
        this.credentials.setPassword(password);
        this.credentials.setAccountType(accountType);
        this.credentials.setCountry(country);
        this.credentials.setBalance(balance);
    }

    /**
     * Constructor for User from CredentialsIn
     * @param credentials
     */

    public User(final CredentialsIn credentials) {
        this.credentials = new Credentials(credentials);
        this.numFreePremiumMovies = this.magicNumber;
    }

    public final ArrayList<Notifications> getNotifications() {
        return notifications;
    }

    public final void setNotifications(final ArrayList<Notifications> notifications) {
        this.notifications = notifications;
    }

    public final ArrayList<String> getSubscribedGenre() {
        return subscribedGenre;
    }

    public final void setSubscribedGenre(final ArrayList<String> subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public final Stack<ActionsIn> getStackActions() {
        return stackActions;
    }

    public final void setStackActions(final Stack<ActionsIn> stackActions) {
        this.stackActions = stackActions;
    }

    public final Credentials getCredentials() {
        return credentials;
    }

    public final void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public final int getTokensCount() {
        return tokensCount;
    }

    public final void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public final int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public final void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public final ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public final void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public final ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public final void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public final ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public final void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public final ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public final void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public final ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public final void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    @Override
    public final String toString() {
        return "User{"
                +
                "credentials=" + credentials
                +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
