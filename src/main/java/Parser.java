import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Game> games = new ArrayList<>();

    public List<Game> sortByName() {
        List<Game> sortedByName = new ArrayList<>(games);
        for (int i = 0; i < sortedByName.size(); i++) {
            for (int j = i + 1; j < sortedByName.size(); j++) {
                if (sortedByName.get(i).getName().compareTo(sortedByName.get(j).getName()) > 0) {
                    Game temp = sortedByName.get(i);
                    sortedByName.set(i, sortedByName.get(j));
                    sortedByName.set(j, temp);
                }
            }
        }

        return sortedByName;
    }

    public List<Game> sortByRating(){
        List<Game> sortedByRating = new ArrayList<>(games);
        for (int i = 0; i < sortedByRating.size(); i++) {
            for (int j = i + 1; j < sortedByRating.size(); j++) {
                if (sortedByRating.get(i).getRating() < sortedByRating.get(j).getRating()) {
                    Game temp = sortedByRating.get(i);
                    sortedByRating.set(i, sortedByRating.get(j));
                    sortedByRating.set(j, temp);
                }
            }
        }

        return sortedByRating;
    }

    public List<Game> sortByPrice(){
        List<Game> sortedByPrice = new ArrayList<>(games);
        for (int i = 0; i < sortedByPrice.size(); i++) {
            for (int j = i + 1; j < sortedByPrice.size(); j++) {
                if (sortedByPrice.get(i).getPrice() < sortedByPrice.get(j).getPrice()) {
                    Game temp = sortedByPrice.get(i);
                    sortedByPrice.set(i, sortedByPrice.get(j));
                    sortedByPrice.set(j, temp);
                }
            }
        }

        return sortedByPrice;
    }


    public void setUp() throws IOException {
        File input = new File("src/Resources/Video_Games.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        //Parse the HTML file using Jsoup
        //TODO

        Elements gameElements = doc.getElementsByClass("game");

        // Extract data from the HTML
        //TODO

        for (Element game : gameElements) {
            String name = game.getElementsByClass("game-name").text();
//            System.out.println("Text: " + name);

            String rating = game.getElementsByClass("game-rating").text();
            String firstRate = rating.split("/")[0];
            double rateDouble = Double.parseDouble(firstRate);
//            System.out.println("Rating: " + rateDouble);

            String price = game.getElementsByClass("game-price").text();
            String firstPrice = price.split(" ")[0];
            int priceInt = Integer.parseInt(firstPrice);
//            System.out.println("Price: " + priceInt);

            games.add(new Game(name, rateDouble, priceInt));
        }


        // Iterate through each Game div to extract Game data
        //TODO
    }

    public static void main(String[] args) throws IOException {
        //you can test your code here before you run the unit tests

        Parser p = new Parser();
        p.setUp();
        String name = "Bloodborne";
        List<Game> sortedByName = p.sortByPrice();
        for (int i = 0; i < sortedByName.size(); i++) {
            System.out.println((i+1) + ". " + sortedByName.get(i).getName() + "   " + sortedByName.get(i).getPrice());
        }
    }
}
