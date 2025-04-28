package model;

public class Login 
{
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    
    public Login(String username, String password, String email, String phonenumber)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
    }
    
    public Login() {};
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber= phonenumber;
    }
    
    public String getUsername() { return username;}
    public String getPassword() { return password;}
    public String getEmail() { return email;}
    public String getPhonenumber() { return phonenumber;}
}
