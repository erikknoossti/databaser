

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkRoleDAOTest {

    private Connection connect;
    private WorkRoleDAO wdao;

    @BeforeEach
    public void setUp() throws SQLException {
        connect = JDBCUtil.getConnection();
        wdao= new WorkRoleDAO();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        try (Statement stmt = connect.createStatement()) {
            stmt.execute("DROP TABLE work_roleTest");
            connect.commit();
        }
        connect.close();
    }

    @Test
    public void testInsertAndGetAllWorkRoles() throws SQLException {
        WorkRole newRole = new WorkRole("Software Developer", "coding", 60000, new Date(2024 - 06 - 07));
        wdao.insertWorkRole(newRole);
        List<WorkRole> roles = wdao.getAllWorkRoles();
        System.out.println("Alla arbetsroller hämtade");
        assertEquals("Software Developer", roles.get(0).getTitle());

    }
}
