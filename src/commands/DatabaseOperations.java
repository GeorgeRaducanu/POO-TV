package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionsIn;
import input.Input;
import input.MovieIn;
import input.UserIn;
import struct.Movie;
import struct.Notifications;
import struct.User;

import java.util.ArrayList;
import java.util.Observable;

public class DatabaseOperations extends Observable {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    /**
     * Database operations method
     * @param inData
     */
    public DatabaseOperations(final Input inData) {
        for (UserIn it : inData.getUsers()) {
            users.add(new User(it));
            addObserver(users.get(users.size() - 1));
        }

        //bagam toate filmele
        for (MovieIn it : inData.getMovies()) {
            movies.add(new Movie(it));
        }
    }

    /**
     * Actual computing and database management
     * @param objectMapper
     * @param output
     * @param users
     * @param movies
     * @param iterate
     */
    public static void database(final ObjectMapper objectMapper,
                                final ArrayNode output,
                                final ArrayList<User> users,
                                final ArrayList<Movie> movies,
                                final ActionsIn iterate) {
        if (iterate.getType().equals("database")) {
            addToDatabase(objectMapper, output, users, movies, iterate);

            removeFromDatabase(objectMapper, output, movies, iterate);
        }
    }

    /**
     * Remove from Database method
     * @param objectMapper
     * @param output
     * @param movies
     * @param iterate
     */
    private static void removeFromDatabase(final ObjectMapper objectMapper,
                                           final ArrayNode output,
                                           final ArrayList<Movie> movies,
                                           final ActionsIn iterate) {
        if (iterate.getFeature().equals("delete")) {
            String deleteMovie = iterate.getDeletedMovie();
            int found = 0;
            Movie foundMovie = null;
            for (Movie iterMov : movies) {
                if (deleteMovie.equals(iterMov.getName())) {
                    found = 1;
                    foundMovie = iterMov;
                }
            }

            if (found == 1) {
                movies.remove(foundMovie);
            } else {
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            }

        }
    }

    /**
     * Add to database method
     * @param objectMapper
     * @param output
     * @param users
     * @param movies
     * @param iterate
     */

    private static void addToDatabase(final ObjectMapper objectMapper,
                                      final ArrayNode output,
                                      final ArrayList<User> users,
                                      final ArrayList<Movie> movies,
                                      final ActionsIn iterate) {
        if (iterate.getFeature().equals("add")) {
            Movie newMovieAdd = new Movie(iterate.getAddedMovie());

            int exists = 0;
            for (Movie ittMovie : movies) {
                if (ittMovie.getName().equals(newMovieAdd.getName())) {
                    exists = 1;
                    break;
                }
            }

            if (exists == 1) {
                // filmul este deja in baza de date;
                // si printam eroare
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error");
                ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
                ArrayList<String> currMovieList = new ArrayList<>();
                for (String itCurrMovieList : currMovieList) {
                    outCurrMoviesList.add(itCurrMovieList);
                }
                help.set("currentMoviesList", outCurrMoviesList);
                ObjectNode aux = objectMapper.createObjectNode();
                aux = null;
                help.put("currentUser", aux);
                output.add(help);
            } else {
                // nu apare in baza de date
                movies.add(newMovieAdd);

                notificationAction(users, newMovieAdd);

            }


        }
    }

    /**
     * Notifications method
     * @param users
     * @param newMovieAdd
     */
    private static void notificationAction(final ArrayList<User> users,
                                           final Movie newMovieAdd) {
        // trb sa mai notificam utilizatorii care sunt abonati cu subscribe
        // la cel putin unul din genurile filmului

        ArrayList<Integer> dataFreq = new ArrayList<>(users.size());
        for (int i = 0; i < users.size(); ++i) {
            dataFreq.add(Integer.valueOf(0));
        }

        for (String genreNow : newMovieAdd.getGenres()) {
            // genre now genul curent al filmului
            int i = 0;
            for (User searchNowUser : users) {
                if (searchNowUser.getSubscribedGenre().contains(genreNow)
                        && !newMovieAdd.getCountriesBanned()
                        .contains(searchNowUser.getCredentials().getCountry())) {
                    dataFreq.set(i, 1);
                }
                i++;
            }
        }

        int k = 0;
        for (User searchNowUser : users) {
            if (dataFreq.get(k) != 0) {
                searchNowUser.getNotifications()
                        .add(new Notifications(newMovieAdd.getName(),
                                "ADD"));
            }
            k++;
        }
    }
}
