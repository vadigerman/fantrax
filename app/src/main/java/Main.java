import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Jsoup jsoup = new Jsoup();
        Storage storage = new Storage();
        storage.saveTeams(jsoup.getTeams());
    }
}
