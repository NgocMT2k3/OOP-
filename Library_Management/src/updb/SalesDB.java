package updb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;
import util.*;
import java.util.*;
import java.util.Date;

public class SalesDB 
{
    public static void addDay() throws SQLException 
    {
        Date day = new Date();
        String sql = "INSERT INTO daysales (day) values (?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setDate(1, new java.sql.Date(day.getTime()));
            stmt.executeUpdate();
        }
    }
    
    public static boolean checkDay(Date day) throws SQLException 
    {
        String sql = "SELECT * FROM daysales where day = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setDate(1, new java.sql.Date(day.getTime()));
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public static void daySale() throws SQLException 
    {
        List<Sales> listsales = new ArrayList<>();
        String sql = "SELECT * FROM daysales ";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Sales sale = new Sales();
                sale.setDay(rs.getDate("day"));
                sale.setSales(rs.getInt("sales"));
                listsales.add(sale);
            }
            System.out.printf("%-15s | %-15s\n", "Day", "Sales");
            for(Sales s : listsales)
            {
                System.out.printf("%-15s | %-15d\n", s.getDay(), s.getSales());            
            }
        }
        
    }  
    
    public static void monthSale() throws SQLException 
    {
        String sql = "SELECT YEAR(day) AS year, MONTH(day) AS month, SUM(sales) AS month_sales FROM daysales " +
        "GROUP BY YEAR(day), MONTH(day) " +
        "ORDER BY YEAR(day), MONTH(day);";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            System.out.printf("%-7s | %-5s |%-15s\n","Year" ,"Month", "Month Sales");
            while(rs.next())
            {
                System.out.printf("%-7s | %-5s |%-15d\n", rs.getString("year"), rs.getString("month"), rs.getInt("month_sales"));   
            }
        }  
    }
}
