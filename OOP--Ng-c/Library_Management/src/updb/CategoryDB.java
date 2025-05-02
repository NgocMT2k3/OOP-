package updb;

import java.sql.*;
import java.util.*;
import model.Category;
import util.DBConnection;

public class CategoryDB 
{
    public static List<Category> getAllCategories() throws SQLException 
    {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                list.add(c);
            }
        }
        return list;
    }

    public static void addCategory(Category category) throws SQLException 
    {
        String sql = "INSERT INTO categories (category_id, category_name) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, category.getCategoryId());
            stmt.setString(2, category.getCategoryName());
            stmt.executeUpdate();
        }
    }
    public static void updateCategory(Category category) throws SQLException 
    {
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getCategoryId());
            stmt.executeUpdate();
        }
    }
    public static void deleteCategory(int categoryId) throws SQLException 
    {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
        }
    }

    public static boolean checkCategoryName(String categoryName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM categories WHERE category_name = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, categoryName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  
                }
            }
        }
        return false;  
    }
}
