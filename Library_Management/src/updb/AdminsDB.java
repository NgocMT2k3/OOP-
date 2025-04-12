package updb;

import java.sql.*;
import util.DBConnection;
import model.Admins;
import model.Login;
import java.util.*;

public class AdminsDB 
{
    public static void addAmin(Admins admin) throws SQLException
    {
        String sql = "INSERT INTO admins (adminname, passwords) VALUE (?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, admin.getAdminname());
            stmt.setString(2, admin.getPassword());
            stmt.executeUpdate();
        }
    }
    
    public static boolean checkAdd(String adminname) throws SQLException
    {
        String sql = "SELECT COUNT(*) FROM admins WHERE adminname = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, adminname);
            try (ResultSet rs = stmt.executeQuery())
            {
                if(rs.next()) return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    public static boolean checkLog(Admins admin) throws SQLException
    {
        String sql = "SELECT * FROM admins WHERE adminname = ? AND passwords = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, admin.getAdminname());
            stmt.setString(2, admin.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    
    public static void showAdmin() throws SQLException
    {
        List<Admins> adminlist = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Admins admin = new Admins();
                admin.setAdminname(rs.getString("adminname"));
                admin.setPassword(rs.getString("passwords"));
                adminlist.add(admin);
            }
            
        }
        for(Admins admin : adminlist)
        {
            System.out.printf("%s -- %s\n", admin.getAdminname(), admin.getPassword());
        }
    }
    
    public static void showUser(String username) throws SQLException
    {
        List<Login> userlist = new ArrayList<>();
        String sql = "SELECT * FROM accounts" + (!username.isEmpty() ? " WHERE username = ?" : "");
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            if(!username.isEmpty()) stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Login account = new Login();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("passwords"));
                account.setEmail(rs.getString("email"));
                account.setPhonenumber(rs.getString("phonenumber"));
                userlist.add(account);
            }
            
        }
        for(Login account : userlist)
        {
            System.out.printf("%s -- %s  -- %s -- %s\n", account.getUsername(), account.getPassword(), account.getEmail(), account.getPhonenumber());
        }
    }
    
    public static void deleteUser(String username) throws SQLException
    {
        String sql = "DELETE FROM accounts WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }
}
