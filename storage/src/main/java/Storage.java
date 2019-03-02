import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Storage {
    public void saveTeams(Map<String, String> teams) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            con.setAutoCommit(false);
            statement = con.prepareStatement("INSERT INTO TEAMS VALUES (?, ?)");
            for (Map.Entry<String, String> team : teams.entrySet()) {
                statement.setString(1, team.getKey());
                statement.setString(2, team.getValue());
                statement.addBatch();
            }
            statement.executeBatch();
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    //
                }
            }
        } catch(ClassNotFoundException e) {
            System.err.println(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    //
                }
            }
        }
    }
}
