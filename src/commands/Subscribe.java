package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.ActionsIn;
import struct.CurrentPage;

import java.util.ArrayList;

public class Subscribe {
    /**
     * Method for the subscribe action!
     * @param objectMapper
     * @param output
     * @param currentPage
     * @param iterate
     */
    public static void subscribe(final ObjectMapper objectMapper,
                                 final ArrayNode output,
                                 final CurrentPage currentPage,
                                 final ActionsIn iterate) {
        if (iterate.getType().equals("on page")) {

            if (iterate.getFeature().equals("subscribe")) {
                if (currentPage.getPageName().equals("see details")) {

                    String addNewGenre = iterate.getSubscribedGenre();
                    if (currentPage.getCurrentUser().getSubscribedGenre().contains(addNewGenre)) {
                        // ar trebui eroare
                        // deja am abonat acest user la acest genre
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
                        currentPage.getCurrentUser().getSubscribedGenre().add(addNewGenre);
                    }

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
    }

}
