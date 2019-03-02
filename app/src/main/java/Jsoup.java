import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Jsoup {
    public Map<String, String> getTeams() throws IOException {
        Map<String, String> teams = new HashMap<String, String>();

        Document doc = org.jsoup.Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2019_standings.html").get();
        Elements table = doc.select("#all_standings");
        for (Element el : table) {
            Elements teamsDom = el.select(".left");
            for (Element t : teamsDom) {
                String teamName = t.text();
                String url = t.children().attr("href");
                if (!url.equals("")) {
                    String teamId = url.substring(7, 10);
                    teams.put(teamId, teamName);
                }
            }
        }
        return teams;
    }
    //        Document doc1 = org.jsoup.Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2019_games.html").get();
}
