public class Match {
    String homeTeam;
    String visitorTeam;

    public Match() {
    }

    public Match(Match match) {
        this.homeTeam = match.getHomeTeam();
        this.visitorTeam = match.getVisitorTeam();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(String visitorTeam) {
        this.visitorTeam = visitorTeam;
    }
}
