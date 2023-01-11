package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionsIn;
import struct.CurrentPage;
import struct.Movie;
import struct.User;

import java.util.ArrayList;

public final class PageSolver {

    private PageSolver() {

    }

    /**
     * Master method for change page commands
     * @param objectMapper
     * @param output
     * @param movies
     * @param currentPage
     * @param iterate
     */
    public static void changePageCommands(final ObjectMapper objectMapper, final ArrayNode output,
                                          final ArrayList<Movie> movies,
                                          final CurrentPage currentPage,
                                          final ActionsIn iterate) {
        ChangePage.loginChangePage(objectMapper, output, currentPage, iterate);

        ChangePage.registerChangePage(objectMapper, output, currentPage, iterate);

        ChangePage.logoutChangePage(objectMapper, output, currentPage, iterate);

        ChangePage.moviesChangePage(objectMapper, output, movies, currentPage, iterate);

        ChangePage.seeDetailsChangePage(objectMapper, output, currentPage, iterate);

        ChangePage.upgradesChangePage(objectMapper, output, currentPage, iterate);
    }

    /**
     * Master method for on page commands
     * @param objectMapper
     * @param output
     * @param users
     * @param movies
     * @param currentPage
     * @param iterate
     */

    public static void onPageCommands(final ObjectMapper objectMapper, final ArrayNode output,
                                      final ArrayList<User> users, final ArrayList<Movie> movies,
                                      final CurrentPage currentPage, final ActionsIn iterate) {
        OnPage.loginOnPage(objectMapper, output, users, currentPage, iterate);

        OnPage.registerOnPage(objectMapper, output, users, currentPage, iterate);

        OnPage.searchOnPage(objectMapper, output, movies, currentPage, iterate);

        OnPage.filterOnPage(objectMapper, output, movies, currentPage, iterate);

        OnPage.buyTokensOnPage(objectMapper, output, currentPage, iterate);

        OnPage.buyPremiumAccountOnPage(objectMapper, output, currentPage, iterate);

        OnPage.likeOnPage(objectMapper, output, movies, currentPage, iterate);

        OnPage.purchaseOnPage(objectMapper, output, movies, currentPage, iterate);

        OnPage.watchOnPage(objectMapper, output, movies, currentPage, iterate);

        OnPage.rateOnPage(objectMapper, output, movies, currentPage, iterate);
    }

}
