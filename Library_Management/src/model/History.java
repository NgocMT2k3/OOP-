package model;

import java.util.Date;
public class History 
{
    private String username;
    private int bookid;
    private int price;
    private Date request_date;
    private Date borrow_date;
    private Date return_date;
    private String status;
    
    public History(String username, int bookid, int price, Date request_date, Date borrow_date, Date return_date, String status)
    {
        this.username = username;
        this.bookid = bookid;
        this.price = price;
        this.request_date = request_date;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;
    }
    
    public History(){};
    
    public void setUsername(String username){this.username = username;}
    public void setBookId(int bookid){this.bookid = bookid;}
    public void setPrice(int price) {this.price = price;}
    public void setRequestDate(Date request_date){this.request_date = request_date;}
    public void setBorrowDate(Date borrow_date){this.borrow_date = borrow_date;}
    public void setReturnDate(Date return_date){this.return_date = return_date;}
    public void setStatus (String status) { this.status = status;}
    
    public String getUsername(){return username;}
    public int getBookId(){return bookid;}
    public int getPrice(){return price;}
    public Date getRequestDate(){return request_date;}
    public Date getBorrowDate(){return borrow_date;}
    public Date getReturnDate(){return return_date;}
    public String getStatus() { return status;}
}
