package model;

public class BookReview {
     
    private int bookid;
    private String bookTitle;
    private String reviewer;
    private int rating;
    private String comment;
    
    public BookReview(int bookid, String bookTitle, String reviewer, int rating, String comment) {
        this.bookid = bookid;
        this.bookTitle = bookTitle;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }
    
    public BookReview(int bookid, String reviewer, int rating, String comment) {
        this.bookid = bookid;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }
    
    // Getters
    public int getBookId() {
        return bookid;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }
    
    public String getReviewer() {
        return reviewer;
    }
    
    public int getRating() {
        return rating;
    }
    
    public String getComment() {
        return comment;
    }
    
    // Setters
    public void setId(int bookid) {
        this.bookid = bookid;
    }
    
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}