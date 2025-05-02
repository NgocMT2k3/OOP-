package updb;

import java.sql.*;
import util.DBConnection;
import model.*;
import java.util.*;
import java.util.Date;

public class BooksDB 
{
    public static void showBooks() throws SQLException
    {
        List<Books> booklist = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
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
            System.out.printf("%-10s | %-10s | %-15s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "BookID", "Tiltle", "PublishYear", "Author", "Category", "Quantity", "Price", "Status");
            for(Books book : booklist)
            {
                System.out.printf("%-10d | %-10s | %-15d | %-10s | %-10s | %-10d | %-10d |%-10s\n", 
                book.getBookId(), book.getTitle(), book.getPublishYear(), book.getAuthor(), book.getCategory(), book.getQuantity(), book.getPrice(),book.getStatus());
                
            }
        }
    }
   
    public static void addBooks(Books book) throws SQLException
    {
        String sql = "INSERT INTO books (bookid, title, publishyear, author,category, quantity, price) VALUE (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getPublishYear());
            stmt.setString(4, book.getAuthor());
            stmt.setString(5, book.getCategory());
            stmt.setInt(6, book.getQuantity());
            stmt.setInt(7, book.getPrice());
            stmt.executeUpdate();
        }
    }
    
    public static void requestBooks( String username, int bookid, Date request_date) throws SQLException
    {
        String sql = "INSERT INTO history (username, bookid, request_date) VALUE (?, ?, ?) ";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            stmt.setInt(2, bookid);
            stmt.setTimestamp(3, new java.sql.Timestamp(request_date.getTime()));
            stmt.executeUpdate();
        }  
    }
    
    public static void borrowBooks(int bookid, Date borrow_date) throws SQLException
    {
        String sql = "UPDATE history SET borrow_date = ? WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setTimestamp(1, new java.sql.Timestamp(borrow_date.getTime()));
            stmt.setInt(2, bookid);
            stmt.executeUpdate();
        }
    }
    
    public static void updateBooks(int bookid, int quantity) throws SQLException
    {
            String sql = "UPDATE books SET quantity = quantity + ? WHERE bookid = ?";
            try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
            {
                stmt.setInt(1, quantity);
                stmt.setInt(2, bookid);
                stmt.executeUpdate();
            }   
            
    }
    
    public static void returnBooks(int bookid, Date return_date) throws SQLException
    {
        String sql = "UPDATE history SET return_date = ? WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setTimestamp(1, new java.sql.Timestamp(return_date.getTime()));
            stmt.setInt(2, bookid);
            stmt.executeUpdate();
        }
    }
    
    public static boolean checkQuantity(int bookid) throws SQLException
    {
        String sql = "SELECT * FROM books WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                return rs.getInt("quantity") > 0;
            }
        }
            return false;
    }
    
    public static boolean checkBookId(int bookid) throws SQLException
    {
        String sql = "SELECT COUNT(*) AS cnt FROM books WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                int count = rs.getInt("cnt");
                return count > 0;
            }
        }
        catch (SQLException e)
        {
            return false;
        }
        return false;
            
    }
}
