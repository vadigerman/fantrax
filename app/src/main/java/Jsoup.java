import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jsoup {
    public Map<String, String> getTeams() throws IOException {
        Map<String, String> teams = new HashMap<String, String>();

        Document doc = org.jsoup.Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2020_standings.html").get();
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

    public Map<String, List<Match>> getCalendar() throws IOException {
        Map<String, String> teams = getTeams();
        Map<String, List<Match>> calendar = new HashMap<String, List<Match>>();
        Document doc = org.jsoup.Jsoup.connect("https://www.hockey-reference.com/leagues/NHL_2020_games.html").get();
        Elements table = doc.select("#div_games");
        for (Element el : table) {
            Elements teamsDom = el.select(".left");
            String date = "";
            boolean homeTeam = false;
            List<Match> matches = new ArrayList<Match>();
            Match match = new Match();
            for (Element t : teamsDom) {
                String content = t.text();
                String url = t.children().attr("href");
                if (!content.equals(date) && content.contains("-")) {
                    if (!date.equals("")) {
                        List<Match> matchesDB = new ArrayList<Match>(matches);
                        calendar.put(date, matchesDB);
                        matches.clear();
                    }
                    date = content;
                }
                if (!url.equals("")) {
                    String teamId = url.substring(7, 10);
                    if (!homeTeam) {
                        match.setVisitorTeam(teamId);
                        homeTeam = true;
                    } else {
                        match.setHomeTeam(teamId);
                        homeTeam = false;
                    }
                    if (match.getHomeTeam() != null && match.getVisitorTeam() != null) {
                        Match matchDB = new Match(match);
                        matches.add(matchDB);
                        match.setVisitorTeam(null);
                        match.setHomeTeam(null);
                    }
                }
            }
        }
        return calendar;
    }
}
