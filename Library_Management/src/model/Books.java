package model;

public class Books 
{
    private int bookid;
    private String title;
    private int publishyear;
    private String author;
    private String category;
    private String status;
    private int original_price;
    private int quantity;
    private int price;
    
    public Books(int bookid, String title, int publishyear, String author, String category, String status, int quantity, int price, int original_price)
    {
        this.bookid = bookid;
        this.title = title;
        this.publishyear = publishyear;
        this.author = author;
        this.category = category;
        this.status = status;
        this.original_price = original_price;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Books(int bookid, String title, int publishyear, String author, String category, int quantity, int original_price)
    {
        this.bookid = bookid;
        this.title = title;
        this.publishyear = publishyear;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.original_price = original_price;
    }
    
    public Books(){};
    public void setBookId(int bookid) { this.bookid = bookid;}
    public void setTitle(String title) { this.title = title;}
    public void setPublishYear(int publishyear) { this.publishyear = publishyear;}
    public void setAuthor(String author){ this.author = author;}
    public void setCategory(String category){ this.category = category;}
    public void setStatus(String status){ this.status = status;}
    public void setQuantity(int quantity){ this.quantity = quantity;}
    public void setPrice(int price){ this.price = price;}
    public void setOriginalPrice(int original_price){ this.original_price = original_price;}
    
    public int getBookId() { return bookid;}
    public int getPublishYear() { return publishyear;}
    public int getQuantity() { return quantity;}
    public String getTitle() { return title;}
    public String getAuthor() { return author;}
    public String getCategory() { return category;}
    public String getStatus() { return status;}   
    public int getPrice() { return price;}
    public int getOriginalPrice() { return original_price;}
}
