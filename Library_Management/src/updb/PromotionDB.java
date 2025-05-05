package updb;

import java.sql.*;
import java.util.*;
import model.Promotion;
import util.DBConnection;

public class PromotionDB 
{
    public static void addPromotion(Promotion promo) 
    {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO promotions (bookid, discount_percent) VALUES (?, ?)")) 
        {

            stmt.setInt(1, promo.getBookId());
            stmt.setInt(2, promo.getDiscountPercent());
            stmt.executeUpdate();

            // Update book price
            BooksDB.updateDiscount(promo.getBookId(), promo.getDiscountPercent());

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void updatePromotion(Promotion promo) 
    {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE promotions SET discount_percent=? WHERE bookid=?")) 
        {

            stmt.setInt(1, promo.getDiscountPercent());
            stmt.setInt(2, promo.getBookId());
            stmt.executeUpdate();

            // Update book price
            BooksDB.updateDiscount(promo.getBookId(), promo.getDiscountPercent());

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public static void deletePromotion(int bookId) 
    {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM promotions WHERE bookid=?")) 
        {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();

            // Reset book price
            BooksDB.updateDiscount(bookId, 0);

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllPromotions() throws SQLException
    {
        List<Promotion> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM promotions")) 
        {
            while (rs.next()) 
            {
                int bookId = rs.getInt("bookid");
                int discount = rs.getInt("discount_percent");
                list.add(new Promotion(bookId, discount));
            }
            System.out.printf("%-15s | %-20s\n", "📚 Book ID", "🔖 Discount");
            for (Promotion pr : list)
            {
                System.out.printf("%-15d | %-20d\n", pr.getBookId(), pr.getDiscountPercent());
            }
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
