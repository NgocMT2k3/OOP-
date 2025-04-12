package model;

public class Books 
{
    private int bookid;
    private String title;
    private int publishyear;
    private String author;
    private String category;
    private String status;
    private int quantity;
    
    public Books(int bookid, String title, int publishyear, String author, String category, String status, int quantity)
    {
        this.bookid = bookid;
        this.title = title;
        this.publishyear = publishyear;
        this.author = author;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
    }
    
    public Books(int bookid, String title, int publishyear, String author, String category, int quantity)
    {
        this.bookid = bookid;
        this.title = title;
        this.publishyear = publishyear;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
    }
    
    public Books(){};
    public void setBookId(int bookid) { this.bookid = bookid;}
    public void setTitle(String title) { this.title = title;}
    public void setPublishYear(int publishyear) { this.publishyear = publishyear;}
    public void setAuthor(String author){ this.author = author;}
    public void setCategory(String category){ this.category = category;}
    public void setStatus(String status){ this.status = status;}
    public void setQuantity(int quantity){ this.quantity = quantity;}
    
    public int getBookId() { return bookid;}
    public int getPublishYear() { return publishyear;}
    public int getQuantity() { return quantity;}
    public String getTitle() { return title;}
    public String getAuthor() { return author;}
    public String getCategory() { return category;}
    public String getStatus() { return status;}    
}
