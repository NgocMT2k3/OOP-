package updb;

import java.sql.*;
import util.DBConnection;
import model.*;

public class PromotionDB {
    public static void addPromotion(Promotion promo) throws SQLException {
        String sql = "INSERT INTO promotions (book_id, discount_percent, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, promo.getBookId());
            stmt.setInt(2, promo.getDiscountPercent());
            stmt.setDate(3, promo.getStartDate());
            stmt.setDate(4, promo.getEndDate());
            stmt.executeUpdate();
        }
    }
    public static void showPromotions() throws SQLException {
        String sql = "SELECT * FROM promotions";
        try (Connection conn = DBConnection.getConnection();Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("BookID: %d, Discount: %d%%, From: %s To: %s\n",
                        rs.getInt("book_id"),
                        rs.getInt("discount_percent"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"));
            }
        }
    }
    public static void updatePromotion(Promotion promo) throws SQLException {
        String sql = "UPDATE promotions SET discount_percent = ?, start_date = ?, end_date = ? WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, promo.getDiscountPercent());
            stmt.setDate(2, promo.getStartDate());
            stmt.setDate(3, promo.getEndDate());
            stmt.setInt(4, promo.getBookId());
            stmt.executeUpdate();
        }
    }
    public static void deletePromotion(int bookId) throws SQLException {
        String sql = "DELETE FROM promotions WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        }
    }
}
