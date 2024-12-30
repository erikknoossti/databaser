import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAO {

    private Connection conn;

    public WorkRoleDAO() throws SQLException {
        this.conn = JDBCUtil.getConnection();
    }


    //Metod för att lägga till ny arbetsroll i databasen
    public void insertWorkRole(WorkRole role) throws SQLException {
        String sql = "INSERT INTO work_role (title, description, salary, creation_date) VALUES (?, ?, ?, ?)";  //sql sats för att sätta in en ny arbetsroll.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.getTitle());
            pstmt.setString(2, role.getDescription());
            pstmt.setDouble(3, role.getSalary());
            pstmt.setDate(4, role.getCreationDate());
            pstmt.executeUpdate();  // Utför Sql satsen för att sätta in arbetsrollen i databasen.
            conn.commit();
        }
    }

    // Metod för att hämta alla arbetsroller från databasen.
    public List<WorkRole> getAllWorkRoles() throws SQLException {
        List<WorkRole> roles = new ArrayList<>();
        String sql = "SELECT * FROM work_role";  //sql sats för att hämta alla arbetsroller.
        try (Statement pstmt = conn.createStatement();
             ResultSet rs = pstmt.executeQuery(sql)) {  // Kör sql satsen och får resultatet som en ResultSet.
            while (rs.next()) {
                WorkRole role = new WorkRole(
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDouble("salary"),
                        rs.getDate("creation_date")
                );
                role.setRoleId(rs.getInt("role_id"));  // Sätter roleId från databasen.
                roles.add(role);  // Lägger till arbetsrollen i listan.
            }
        }
        return roles;
    }
    public void updateWorkRole(WorkRole role) throws SQLException {  // Metod för att uppdatera en arbetsroll i databasen.
        String sql = "UPDATE work_role SET title = ?, description = ?, salary = ?, creation_date = ? WHERE role_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, role.getTitle());
            pstmt.setString(2, role.getDescription());
            pstmt.setDouble(3, role.getSalary());
            pstmt.setDate(4, role.getCreationDate());
            pstmt.setInt(5, role.getRoleId());
            pstmt.executeUpdate();
            conn.commit();
        }
    }

    public WorkRole getWorkRoleById(int id) throws SQLException {  // metod för att hämta arbetsroll med hjälp av roleId.
        String sql = "SELECT * FROM work_role WHERE role_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new WorkRole(  // Skapar och returnerar en ny instans av WorkRole med data från databasen.
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getDouble("salary"),
                            rs.getDate("creation_date")
                    );
                }
            }
        }
        return null;
    }

    public void deleteWorkRole(int roleId) throws SQLException {
        String query = "DELETE FROM work_role WHERE role_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, roleId);
            pstmt.executeUpdate();
            conn.commit();
        }
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}


