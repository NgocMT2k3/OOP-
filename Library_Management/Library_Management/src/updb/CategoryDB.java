package updb;

import java.sql.*;
import java.util.*;
import model.*;
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
    
    public static boolean checkCategoryID(int category_id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM categories WHERE category_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, category_id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  
                }
            }
        }
        return false;  
    }
    
    public static void showBookofCategory(String category) throws SQLException
    {
        List<Books> booklist = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE category = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                Books book = new Books();
                book.setBookId(rs.getInt("bookid"));
                book.setTitle(rs.getString("title"));
                book.setPublishYear(rs.getInt("publishyear"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setStatus(rs.getString("status"));
                book.setQuantity(rs.getInt("quantity"));
                book.setPrice(rs.getInt("price"));
                booklist.add(book);
            }     
            System.out.printf("%-7s | %-50s | %-15s | %-30s | %-25s | %-10s |%-10s | %-10s\n", 
                    "BookID", "Tiltle", "PublishYear", "Author", "Category", "Quantity", "Price", "Status");
            for(Books book : booklist)
            {
                System.out.printf("%-7d | %-50s | %-15d | %-30s | %-25s | %-10d |%-10d | %-10s\n", 
                book.getBookId(), book.getTitle(), book.getPublishYear(), book.getAuthor(), book.getCategory(), book.getQuantity(), book.getPrice(),book.getStatus());
                
            }
        }
    }
}