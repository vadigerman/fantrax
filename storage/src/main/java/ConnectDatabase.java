import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDatabase {
    public static void main(String[] args) {
        try {
            int result;
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            Statement stmt = con.createStatement();

            result = stmt.executeUpdate("CREATE TABLE TEAMS (" +
                    "TEAM_ID VARCHAR(20) NOT NULL PRIMARY KEY, " +
                    "TEAM_NAME VARCHAR(50) NOT NULL, " +
                    ");");

//            result = stmt.executeUpdate("DROP TABLE TEAMS");

            con.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("successful");
    }
}
