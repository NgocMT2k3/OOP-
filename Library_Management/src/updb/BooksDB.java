package updb;

import java.sql.*;
import util.DBConnection;
import model.Books;
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
                booklist.add(book);
            }
            for(Books book : booklist)
            {
                System.out.printf("%d -- %s -- %d -- %s -- %s -- %s -- %d\n", book.getBookId(), book.getTitle(), book.getPublishYear(), book.getAuthor(), book.getCategory(), book.getStatus(), book.getQuantity());
                
            }
        }
    }
   
    public static void addBooks(Books book) throws SQLException
    {
        String sql = "INSERT INTO books (bookid, title, publishyear, author,category, quantity) VALUE (?, ?, ?, ?, ?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, book.getBookId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getPublishYear());
            stmt.setString(4, book.getAuthor());
            stmt.setString(5, book.getCategory());
            stmt.setInt(6, book.getQuantity());
            stmt.executeUpdate();
        }
    }
    
    public static void borrowBooks( String username, int bookid, Date borrow_date) throws SQLException
    {
        String sql = "INSERT INTO history (username, bookid, borrow_date) VALUE (?, ?, ?) ";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, username);
            stmt.setInt(2, bookid);
            stmt.setTimestamp(3, new java.sql.Timestamp(borrow_date.getTime()));
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
        String sql = "SELECT COUNT(*) FROM books WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    
    public static void showHistory(String username) throws SQLException
        {
            String sql = "SELECT * FROM history " +  (!username.isEmpty() ? " WHERE username = ?" : "");
            try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
            {
                if(!username.isEmpty()) stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                System.out.printf("%-20s | %-6s | %-25s | %-25s | %-10s\n","UserName", "BookID", "Borrow_Date", "Return_Date", "Status" );
                System.out.println("-----------------------------------------------------------------------" );
                while(rs.next())
                {
                    username = rs.getString("username");
                    String bookid = rs.getInt("bookid") + "";
                    String borrow_date = rs.getTimestamp("borrow_date") + "";
                    String return_date = rs.getTimestamp("return_date") + "";
                    String status = rs.getString("status");
                    System.out.printf("%-20s | %-6s | %-25s | %-25s | %-10s\n ",username, bookid, borrow_date, return_date, status);
                }
            }
        }
}
