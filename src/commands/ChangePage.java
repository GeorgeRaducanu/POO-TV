package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionsIn;
import struct.CurrentPage;
import struct.Movie;
import struct.Notifications;

import java.util.ArrayList;

public final class ChangePage {

    private ChangePage() {

    }

    /**
     * Change page method for upgrades command
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */
    public static void upgradesChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                          final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("upgrades")) {
            if (currentPage.getCurrentUser() != null) {
                currentPage.setPageName("upgrades");
                currentPage.setCurrentMovie(null);
                currentPage.setMoviesList(null);
                if (!currentPage.getCurrentUser().getStackActions().peek().equals(iterate)) {
                    currentPage.getCurrentUser().getStackActions().push(iterate);
                }
            } else {
                ObjectNode auxx = objectMapper.createObjectNode();
                auxx.put("eroare la upgrade", "eroare");
                output.add(auxx);
            }
        }
    }

    /**
     * Change page method for see details command
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */

    public static void seeDetailsChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                            final CurrentPage currentPage,
                                            final ActionsIn iterate) {
        if (iterate.getPage().equals("see details")) {
            String movieee = iterate.getMovie();
            int found = 0;
            Movie searchedMovie = null;

            for (Movie iter : currentPage.getCurrentUser().getCurrentMoviesList()) {
                if (iter.getName().equals(movieee)) {
                    found = 1;
                    searchedMovie = iter;
                }
            }
            int banned = 0;
            if (found == 1) {
                currentPage.setCurrentMovie(searchedMovie);
                for (String itStr : searchedMovie.getCountriesBanned()) {
                    if (itStr.equals(currentPage.getCurrentUser()
                            .getCredentials().getCountry())) {
                        banned = 1;
                        break;
                    }
                }
            }

            if (found == 1 && banned == 0) {
                // o printare buna
                currentPage.setCurrentMovie(searchedMovie);
                currentPage.getCurrentUser().setCurrentMoviesList(new ArrayList<>());
                currentPage.getCurrentUser().getCurrentMoviesList().add(searchedMovie);
                //current movie list ul numai filmul pe cae il caut
                printFunctionChangePage(objectMapper, output, currentPage, searchedMovie);
                currentPage.setPageName("see details");

                if (!currentPage.getCurrentUser().getStackActions().peek().equals(iterate)) {
                    currentPage.getCurrentUser().getStackActions().push(iterate);
                }
            } else {
                errorChangePage(objectMapper, output, currentPage);
            }
        }
    }

    /**
     * Change pagge method for movies change page command
     * @param objectMapper
     * @param output
     * @param movies
     * @param currentPage
     * @param iterate
     */
    public static void moviesChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                        final ArrayList<Movie> movies,
                                        final CurrentPage currentPage,
                                        final ActionsIn iterate) {
        if (iterate.getPage().equals("movies")) {
            // incercam sa trecem pe pagina de movies acuma
            if (currentPage.getCurrentUser() != null) {


                if (!currentPage.getCurrentUser().getStackActions().peek().equals(iterate)) {
                    currentPage.getCurrentUser().getStackActions().push(iterate);
                }

                currentPage.setPageName("movies");
                ArrayList<Movie> possibleMovies = new ArrayList<>();

                for (Movie itMovie : movies) {
                    int banat = 0;
                    for (String itCountry : itMovie.getCountriesBanned()) {
                        if (itCountry.equals(currentPage.getCurrentUser()
                                .getCredentials().getCountry())) {
                            banat = 1;
                            break;
                        }
                    }
                    if (banat == 0) {
                        possibleMovies.add(itMovie);
                    }
                }

                // in possible movies avem toate filmele
                currentPage.setMoviesList(possibleMovies);
                currentPage.getCurrentUser().setCurrentMoviesList(possibleMovies);

                printMoviesChangePage(objectMapper, output, currentPage);


            } else {
                errorChangePage(objectMapper, output);
            }
        }
    }


    /**
     * Method for printing the required output for movies change page command
     * @param objectMapper
     * @param output
     * @param currentPage
     */
    private static void printMoviesChangePage(final ObjectMapper objectMapper,
                                              final ArrayNode output,
                                              final CurrentPage currentPage) {
        ObjectNode helpp = objectMapper.createObjectNode();
        ObjectNode auxErr;
        auxErr = null;
        helpp.put("error", auxErr);
        ArrayNode outCurrMoviesList = objectMapper.createArrayNode();


        for (Movie itMovies : currentPage.getMoviesList()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", itMovies.getName());
            ajutor.put("year", String.valueOf(itMovies.getYear()));
            ajutor.put("duration", itMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : itMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : itMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : itMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", itMovies.getNumLikes());
            ajutor.put("rating", itMovies.getRating());
            ajutor.put("numRatings", itMovies.getNumRatings());
            outCurrMoviesList.add(ajutor);
        }

        helpp.set("currentMoviesList", outCurrMoviesList);

        ObjectNode help = objectMapper.createObjectNode();

        ObjectNode credent = objectMapper.createObjectNode();
        credent.put("name", currentPage
                .getCurrentUser().getCredentials().getName());
        credent.put("password", currentPage
                .getCurrentUser().getCredentials().getPassword());
        credent.put("accountType", currentPage
                .getCurrentUser().getCredentials().getAccountType());
        credent.put("country", currentPage.getCurrentUser()
                .getCredentials().getCountry());
        credent.put("balance", Integer
                .toString(currentPage.getCurrentUser()
                        .getCredentials().getBalance()));

        help.set("credentials", credent);
        help.put("tokensCount", currentPage.getCurrentUser().getTokensCount());
        help.put("numFreePremiumMovies", currentPage
                .getCurrentUser().getNumFreePremiumMovies());

        ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

        for (Movie iterMovies : currentPage.getCurrentUser().getPurchasedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outPurchasedMovies.add(ajutor);
        }
        help.set("purchasedMovies", outPurchasedMovies);

        ArrayNode outWatchedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getWatchedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outWatchedMovies.add(ajutor);
        }
        help.set("watchedMovies", outWatchedMovies);

        ArrayNode outNotifications = objectMapper.createArrayNode();
        for (Notifications iterNot : currentPage.getCurrentUser().getNotifications()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("movieName", iterNot.getMovieName());
            ajutor.put("message", iterNot.getMessage());
            outNotifications.add(ajutor);
        }
        help.set("notifications", outNotifications);

        ArrayNode outLikedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getLikedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outLikedMovies.add(ajutor);
        }
        help.set("likedMovies", outLikedMovies);

        ArrayNode outratedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getRatedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outratedMovies.add(ajutor);
        }
        help.set("ratedMovies", outratedMovies);
        helpp.set("currentUser", help);
        output.add(helpp);
    }

    /**
     * Method for logout change page command
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */
    public static void logoutChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                        final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("logout")) {
            if (currentPage.getCurrentUser() != null) {
                // ne delogam
                currentPage.setPageName("homepage");
                currentPage.setCurrentUser(null);
                currentPage.setCurrentMovie(null);
                currentPage.setMoviesList(null);
            } else {
                // nu ne putem deloga cu succes
                errorChangePage(objectMapper, output);
            }
            currentPage.setCurrentUser(null);
        }
    }

    /**
     * Method for login change page command
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */
    public static void loginChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                       final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("login")) {
            // incercam sa intram pe pagina de login
            if (currentPage.getCurrentUser() == null && currentPage
                    .getPageName().equals("homepage")) {
                currentPage.setPageName("login");
            } else {
                //eroare nu putem sa schimbam pe pagina de login
                errorChangePage(objectMapper, output);
            }
        }
    }

    /**
     * Method for register change page command
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */
    public static void registerChangePage(final ObjectMapper objectMapper, final ArrayNode output,
                                          final CurrentPage currentPage, final ActionsIn iterate) {
        if (iterate.getPage().equals("register")) {
            if (currentPage.getCurrentUser() == null && currentPage
                    .getPageName().equals("homepage")) {
                currentPage.setPageName("register");
            } else {
                // eroare ca nu putem schimba pe pagina de register
                errorChangePage(objectMapper, output);
            }
        }
    }

    /**
     * Method for printing errors on change page
     * @param objectMapper
     * @param output
     * @param currentPage
     */
    private static void errorChangePage(final ObjectMapper objectMapper,
                                        final ArrayNode output,
                                        final CurrentPage currentPage) {
        ObjectNode help = objectMapper.createObjectNode();
        currentPage.setCurrentMovie(null);
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

    /**
     * Another method for printing error in different contexts
     * @param objectMapper
     * @param output
     */
    private static void errorChangePage(final ObjectMapper objectMapper,
                                        final ArrayNode output) {
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

    /**
     * Method for printing the required output for change page commands
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param searchedMovie
     */
    static void printFunctionChangePage(final ObjectMapper objectMapper,
                                        final ArrayNode output,
                                        final CurrentPage currentPage,
                                        final Movie searchedMovie) {
        ObjectNode helpp = objectMapper.createObjectNode();
        ObjectNode auxErr;
        auxErr = null;
        helpp.put("error", auxErr);
        ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
        ArrayList<Movie> auxCurrMovieList = new ArrayList<>();
        auxCurrMovieList.add(searchedMovie);

        for (Movie itMovies : auxCurrMovieList) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", itMovies.getName());
            ajutor.put("year", String.valueOf(itMovies.getYear()));
            ajutor.put("duration", itMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : itMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : itMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : itMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", itMovies.getNumLikes());
            ajutor.put("rating", itMovies.getRating());
            ajutor.put("numRatings", itMovies.getNumRatings());
            outCurrMoviesList.add(ajutor);
        }

        helpp.set("currentMoviesList", outCurrMoviesList);

        ObjectNode help = objectMapper.createObjectNode();

        ObjectNode credent = objectMapper.createObjectNode();
        credent.put("name", currentPage.getCurrentUser().getCredentials().getName());
        credent.put("password", currentPage.getCurrentUser()
                .getCredentials().getPassword());
        credent.put("accountType", currentPage.getCurrentUser().getCredentials()
                .getAccountType());
        credent.put("country", currentPage.getCurrentUser().getCredentials().getCountry());
        credent.put("balance", Integer.toString(currentPage.getCurrentUser()
                .getCredentials().getBalance()));

        help.set("credentials", credent);
        help.put("tokensCount", currentPage.getCurrentUser().getTokensCount());
        help.put("numFreePremiumMovies", currentPage.getCurrentUser()
                .getNumFreePremiumMovies());

        ArrayNode outPurchasedMovies = objectMapper.createArrayNode();

        for (Movie iterMovies : currentPage.getCurrentUser().getPurchasedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outPurchasedMovies.add(ajutor);
        }
        help.set("purchasedMovies", outPurchasedMovies);

        ArrayNode outWatchedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getWatchedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outWatchedMovies.add(ajutor);
        }
        help.set("watchedMovies", outWatchedMovies);

        ArrayNode outNotifications = objectMapper.createArrayNode();
        for (Notifications iterNot : currentPage.getCurrentUser().getNotifications()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("movieName", iterNot.getMovieName());
            ajutor.put("message", iterNot.getMessage());
            outNotifications.add(ajutor);
        }
        help.set("notifications", outNotifications);

        ArrayNode outLikedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getLikedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outLikedMovies.add(ajutor);
        }
        help.set("likedMovies", outLikedMovies);

        ArrayNode outratedMovies = objectMapper.createArrayNode();
        for (Movie iterMovies : currentPage.getCurrentUser().getRatedMovies()) {
            ObjectNode ajutor = objectMapper.createObjectNode();
            ajutor.put("name", iterMovies.getName());
            ajutor.put("year", String.valueOf(iterMovies.getYear()));
            ajutor.put("duration", iterMovies.getDuration());

            ArrayNode noName = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getGenres()) {
                noName.add(itStr);
            }
            ajutor.set("genres", noName);

            ArrayNode noName1 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getActors()) {
                noName1.add(itStr);
            }
            ajutor.set("actors", noName1);

            ArrayNode noName2 = objectMapper.createArrayNode();
            for (String itStr : iterMovies.getCountriesBanned()) {
                noName2.add(itStr);
            }
            ajutor.set("countriesBanned", noName2);

            ajutor.put("numLikes", iterMovies.getNumLikes());
            ajutor.put("rating", iterMovies.getRating());
            ajutor.put("numRatings", iterMovies.getNumRatings());
            outratedMovies.add(ajutor);
        }
        help.set("ratedMovies", outratedMovies);
        helpp.set("currentUser", help);
        output.add(helpp);
    }

}
