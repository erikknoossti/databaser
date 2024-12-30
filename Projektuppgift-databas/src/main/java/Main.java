
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            WorkRoleDAO dao = new WorkRoleDAO();

            // Skapar en ny arbetsroll och sätter in den i databasen.
//            WorkRole newRole = new WorkRole("Software Developer", "Develop software applications", 50000.0, new java.sql.Date(2024-12-10));
//            dao.insertWorkRole(newRole);
//            System.out.println("Ny arbetsroll inlagd: " + newRole.getTitle());
//
//            WorkRole newRole2 = new WorkRole("Software Developer", "coding", 50000.0, new java.sql.Date(2024-12-10));
//            dao.insertWorkRole(newRole2);
//            System.out.println("Ny arbetsroll inlagd: " + newRole2.getTitle());


            // Hämtar alla arbetsroller från databasen.
            List<WorkRole> roles = dao.getAllWorkRoles();
            System.out.println("Alla arbetsroller:");
            for (WorkRole role : roles) {
                System.out.println(role.getTitle() + " - " + role.getDescription());
            }

            // Hämtar en specifik arbetsroll baserat på id
            WorkRole specificRole = dao.getWorkRoleById(43);
            if (specificRole != null) {
                System.out.println("Arbetsroll med ID ?: " + specificRole.getTitle() + " - " + specificRole.getDescription());
            } else {
                System.out.println("Ingen arbetsroll hittades med ID ?.");
            }

            // Uppdaterar en arbetsroll
            if (specificRole != null) {
                specificRole.setTitle("Software Developer");
                specificRole.setSalary(60000.0);
                dao.updateWorkRole(specificRole);
                System.out.println("Uppdaterad arbetsroll: " + specificRole.getTitle() + " - " + specificRole.getSalary());
            }

            // Tar bort en arbetsroll
            dao.deleteWorkRole(43);
            System.out.println("Arbetsrollen med ID 43 har tagits bort.");


            // Stänger ned databasanslutningen.
            dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

