package model;

public class Admins 
{
    private String adminname;
    private String password;
    
    public Admins(String adminname, String password)
    {
        this.adminname = adminname;
        this.password = password;
    }
    
    public Admins(){};
    
    public void setAdminname(String adminname)
    {
        this.adminname = adminname;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getAdminname() {return adminname;}
    public String getPassword() {return password;}
}
