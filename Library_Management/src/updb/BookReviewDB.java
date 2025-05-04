package updb;

import java.sql.*;
import java.util.*;
import model.BookReview;
import util.DBConnection;

public class BookReviewDB 
{
    public static boolean addReview(BookReview review) 
    {
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO book_review (bookid, reviewer, rating, comment) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, review.getBookId());
            pstmt.setString(2, review.getReviewer());
            pstmt.setInt(3, review.getRating());
            pstmt.setString(4, review.getComment());
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error adding review: " + e.getMessage());
            return false;
        }
    }
  
    public static void getAllReviews() 
    {
        List<BookReview> reviews = new ArrayList<>();
        
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM book_review";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) 
            {
                int bookid = rs.getInt("bookid");
                String book_title = rs.getString("book_title");
                String reviewer = rs.getString("reviewer");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                
                BookReview review = new BookReview(bookid, book_title, reviewer, rating, comment);
                reviews.add(review);
            }
            
            System.out.printf("%-8s | %-25s | %-10s |%-7s | %-100s \n", "BookID", "Book Title", "Reviewer", "Rating", "Comment");
            for(BookReview br : reviews)
            {
                System.out.printf("%-8d | %-25s | %-10s |%-7d | %-100s \n", 
                    br.getBookId(), br.getBookTitle(), br.getReviewer(), br.getRating(), br.getComment());  
            }
            rs.close();
            stmt.close();
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving reviews: " + e.getMessage());
        }
      
    }
   
    public static List<BookReview> getReviewsByBook(int bookid) 
    {
        List<BookReview> reviews = new ArrayList<>();
        
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM book_review WHERE bookid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookid);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) 
            {
                String reviewer = rs.getString("reviewer");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                
                BookReview review = new BookReview(bookid, reviewer, rating, comment);
                reviews.add(review);
            }
            
            rs.close();
            pstmt.close();
        } 
        catch (SQLException e) 
        {
            System.out.println("Error retrieving reviews: " + e.getMessage());
        }
        
        return reviews;
    }
    

    public static boolean checkBookId(int bookid) throws SQLException
    {
        String sql = "SELECT COUNT(*) AS cnt FROM book_review WHERE bookid = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, bookid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return rs.getInt("cnt") > 0;
            }
        }
        catch (SQLException e)
        {
            return false;
        }
        return false; 
    }
    
    public static boolean updateReview(int bookid, int rating, String comment) 
    {
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE book_review SET rating = ?, comment = ? WHERE bookid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, rating);
            pstmt.setString(2, comment);
            pstmt.setInt(3, bookid);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error updating review: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean deleteReview(int bookid) 
    {
        try 
        {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM book_review WHERE bookid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, bookid);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            return rowsAffected > 0;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error deleting review: " + e.getMessage());
            return false;
        }
    }
}