import java.sql.Date;

public class WorkRole {
    private int roleId;
    private String title;
    private String description;
    private double salary;
    private Date creationDate;


    public WorkRole(String title, String description, double salary, Date date){
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = date;

    }
    public WorkRole(){

    }
    public int getRoleId(){
        return this.roleId;
    }
    public void setRoleId(int roleId){
        this.roleId = roleId;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Double getSalary(){
        return this.salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public Date getCreationDate(){
        return this.creationDate;
    }
    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
}
