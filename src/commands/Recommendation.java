package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import struct.CurrentPage;
import struct.GenreLikes;
import struct.Movie;
import struct.Notifications;

import java.util.ArrayList;
import java.util.Comparator;

public final class Recommendation {

    private Recommendation() {

    }
    /**
     * Master method for recommendations at the end
     * @param objectMapper
     * @param output
     * @param movies
     * @param currentPage
     */
    public static void recommendation(final ObjectMapper objectMapper,
                                      final ArrayNode output,
                                      final ArrayList<Movie> movies,
                                      final CurrentPage currentPage) {
        if (currentPage.getCurrentUser() != null) {
            if (currentPage.getCurrentUser().getCredentials().getAccountType().equals("premium")) {
                // acuma mai pe larg dar momentan bagam shortcut
                ArrayList<GenreLikes> listGenres = new ArrayList<>();
                for (Movie likedMovie : currentPage.getCurrentUser().getLikedMovies()) {
                    for (String genre : likedMovie.getGenres()) {
                        // avem un gen din alea la care am dat like
                        int found = 0;
                        for (int i = 0; i < listGenres.size(); ++i) {
                            if (listGenres.get(i).getGenreName().equals(genre)) {
                                listGenres.get(i)
                                        .setNumLikes(1 + listGenres.get(i).getNumLikes());
                                found = 1;
                            }
                        }
                        if (found == 0) {
                            listGenres.add(new GenreLikes(genre, 1));
                        }
                    }
                }

                listGenres.sort(new Comparator<GenreLikes>() {
                    @Override
                    public int compare(final GenreLikes o1, final GenreLikes o2) {

                        if (o1.getNumLikes() > o2.getNumLikes()) {
                            return 1;
                        }
                        if (o1.getNumLikes() < o2.getNumLikes()) {
                            return -1;
                        }
                        if (o1.getGenreName().compareTo(o2.getGenreName()) > 0) {
                            return 1;
                        }
                        return -1;
                    }
                });

                // acum in baza de date cautam filmele nebanate si nevazute
                ArrayList<Movie> newDatabase = new ArrayList<>();
                for (Movie iterMovie : movies) {
                    if (!currentPage.getCurrentUser()
                            .getWatchedMovies().contains(iterMovie)
                            && !iterMovie.getCountriesBanned().contains(currentPage
                            .getCurrentUser().getCredentials()
                            .getCountry())) {
                        newDatabase.add(iterMovie);
                    }
                }
                newDatabase.sort(new Comparator<Movie>() {
                    @Override
                    public int compare(final Movie o1, final Movie o2) {
                        if (o1.getNumLikes() < o2.getNumLikes()) {
                            return 1;
                        }
                        if (o1.getNumLikes() > o2.getNumLikes()) {
                            return -1;
                        }
                        if (o1.getName().compareTo(o2.getName()) > 0) {
                            return 1;
                        }
                        return -1;
                    }
                });

                int foundd = 0;
                for (GenreLikes iterGenre : listGenres) {
                    for (Movie itMov : newDatabase) {
                        if (itMov.getGenres().contains(iterGenre.getGenreName())) {
                            if (foundd == 0) {
                                foundd = 1;
                                currentPage.getCurrentUser()
                                        .getNotifications()
                                        .add(new Notifications(itMov
                                                .getName(), "Recommendation"));
                            }
                        }
                    }
                }

                if (foundd == 0) {
                    currentPage.getCurrentUser()
                            .getNotifications()
                            .add(new Notifications("No recommendation",
                                    "Recommendation"));
                }
                // aici printare, nu ne bagam
                printRecommendation(objectMapper, output, currentPage);

            }
        }
    }

    /**
     * Method for printing the necessary output for recommendations
     * @param objectMapper
     * @param output
     * @param currentPage
     */
    private static void printRecommendation(final ObjectMapper objectMapper,
                                            final ArrayNode output,
                                            final CurrentPage currentPage) {
        ObjectNode helpp = objectMapper.createObjectNode();
        ObjectNode auxErr;
        auxErr = null;
        helpp.put("error", auxErr);
        ArrayNode outCurrMoviesList = objectMapper.createArrayNode();
        ArrayList<Movie> auxxCurrMovieList = new ArrayList<>();


        helpp.put("currentMoviesList", auxErr);


        ObjectNode help = objectMapper.createObjectNode();

        ObjectNode credent = objectMapper.createObjectNode();
        credent.put("name", currentPage.getCurrentUser().getCredentials().getName());
        credent.put("password", currentPage.getCurrentUser().getCredentials()
                .getPassword());
        credent.put("accountType", currentPage.getCurrentUser().getCredentials()
                .getAccountType());
        credent.put("country", currentPage.getCurrentUser().getCredentials()
                .getCountry());
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
