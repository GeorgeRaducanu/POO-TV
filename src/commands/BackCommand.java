package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionsIn;
import struct.CurrentPage;
import struct.Movie;

import java.util.ArrayList;

public final class BackCommand {
    private BackCommand() {

    }

    /**
     * Back command method, it is based on a stack of actions
     * @param objectMapper
     * @param output
     * @param movies
     * @param currentPage
     * @param iterate
     */
    public static void backCommand(final ObjectMapper objectMapper,
                                   final ArrayNode output,
                                   final ArrayList<Movie> movies,
                                   final CurrentPage currentPage,
                                   final ActionsIn iterate) {
        if (iterate.getType().equals("back")) {
            if (currentPage.getCurrentUser() != null) {
                if (currentPage.getCurrentUser().getStackActions().size() < 2) {
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
                    currentPage.getCurrentUser().getStackActions().pop();
                    ActionsIn nowActionBack = currentPage.getCurrentUser()
                            .getStackActions().peek();
                    if (nowActionBack.getType().equals("change page")) {
                        PageSolver
                                .changePageCommands(objectMapper,
                                        output, movies, currentPage,
                                        nowActionBack);
                    } else {
                        currentPage.setPageName("homepage");
                    }
                }
            } else {
                ObjectNode help = objectMapper.createObjectNode();
                help.put("error", "Error la back");
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

}
