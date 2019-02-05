import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> teamsName = new ArrayList<String>();
        String team;

        Document doc1 = Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2019_games.html").get();
        Document doc2 = Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2019_standings.html").get();
        Elements table = doc2.select("#all_standings");
        for (Element el : table) {
            Elements teamsDom = el.select(".left");
            for (Element t : teamsDom) {
                team = t.text();
                if (!team.equals("")) {
                    teamsName.add(team);
                }
            }
        }
        System.out.println(teamsName);
    }
}
