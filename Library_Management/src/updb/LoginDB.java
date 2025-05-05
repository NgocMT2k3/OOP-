package updb;

import java.sql.*;
import util.DBConnection;
import model.Login;

public class LoginDB 
{
    public static void addAccount(Login account) throws SQLException
    {
        String sql = "INSERT INTO accounts (username, passwords, email, phonenumber) VALUE (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getPhonenumber());
            stmt.executeUpdate();
        }
    }
    
    public static boolean checkUserName(String username) throws SQLException
    {
        String sql = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            try ( ResultSet rs = stmt.executeQuery())
            {
                if(rs.next()) return rs.getInt(1) > 0;     
            }
        }
        return false;   
    }
    
    public static boolean checkLog(Login account) throws SQLException
    {
        String sql = "SELECT username, passwords FROM accounts WHERE username = ? AND passwords = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    
    public static void chanceInformation(Login account) throws SQLException
    {
        String sql = "UPDATE accounts SET " + (!account.getPassword().equals("0") ? " passwords = ? " : "") + (!account.getEmail().equals("0") ? " email = ?" : "") + (!account.getPhonenumber().equals("0") ? " phonenumber = ?" : "") + " WHERE username = ? "  ;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            int i = 1;
            if(!account.getPassword().equals("0")) stmt.setString(i++, account.getPassword());
            if(!account.getEmail().equals("0")) stmt.setString(i++, account.getEmail()); 
            if(!account.getPhonenumber().equals("0")) stmt.setString(i++, account.getPhonenumber());
            stmt.setString(i, account.getUsername());
            stmt.executeUpdate();           
        }
    }
}
