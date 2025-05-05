package updb;

import java.sql.*;
import util.DBConnection;
import model.*;
import java.util.*;
import java.util.Date;

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
        System.out.printf("%-15s | %-15s\n", "Admin Name", "PassWord");
        for(Admins admin : adminlist)
        {
            System.out.printf("%-15s | %-15s\n", admin.getAdminname(), admin.getPassword());
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
        System.out.printf("%-20s | %-20s | %-30s | %-15s\n", "UserName", "PassWord", "Email", "PhoneNumber");
        for(Login account : userlist)
        {
            System.out.printf("%-20s | %-20s | %-30s | %-15s", 
                    account.getUsername(), account.getPassword(), account.getEmail(), account.getPhonenumber());
            System.out.println();
        }
    }
    
    public static void showPay(String username) throws SQLException
    {
        int sum = 0;
        List<History> listhistory = new ArrayList<>();
        String sql = "SELECT username, bookid, price, status FROM history where username = ? ORDER BY request_date DESC ";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                History h = new History();
                h.setBookId(rs.getInt("bookid"));
                h.setPrice(rs.getInt("price"));
                h.setStatus(rs.getString("status"));
                if(h.getStatus().equals("requested"))
                {
                    sum += rs.getInt("price");
                }
                listhistory.add(h);
            }
            System.out.printf("%-20s | %-8s | %-10s\n", "UserName", "BookID", "Price");
            for(History h : listhistory)
            {
                if(h.getStatus().equals("requested"))
                {
                    System.out.printf("%-20s | %-8s | %-10s\n", username, h.getBookId(), h.getPrice());
                }                    
            }
             System.out.println("Total Payment Amount: " + sum);
        }
    }

    
    
//    public static void deleteUser(String username) throws SQLException
//    {
//        String sql = "DELETE FROM accounts WHERE username = ?";
//        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
//        {
//            stmt.setString(1, username);
//            stmt.executeUpdate();
//        }
//    }
}
