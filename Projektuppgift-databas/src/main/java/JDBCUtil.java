
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil{

    private static Properties properties = new Properties();

    public static void test() {
        System.out.println("hej");
    }

    public static Connection getConnection() throws SQLException {
//skapa upp en instans av hsql:s jdbcDriver-klass
        Driver hsqlDriver = new org.hsqldb.jdbcDriver();
        DriverManager.registerDriver(hsqlDriver);
        String dbURL = properties.getProperty("db.url");
        String userId = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

//Använd metoden getConnection i DriverManager för att få en anslutning till databasen
        Connection conn = DriverManager.getConnection(dbURL, userId, password);
//Sätt autoCommit till false
        conn.setAutoCommit(false);
//returnera anslutningen
        return DriverManager.getConnection(dbURL, userId, password);
    }

    //Stänger en aktiv databasanslutning
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Stänger ett aktivt statement-objekt
    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Stänger ett aktivt ResultSet-objekt
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Bekräfar och sparar ändringar i databasen
    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Ångrar ändringar i databasen om något går fel
    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getDatabaseProductName() throws SQLException {
        DatabaseMetaData metaData = getConnection().getMetaData();
        return metaData.getDatabaseProductName();
    }

    static {
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");

        }

    }
}
