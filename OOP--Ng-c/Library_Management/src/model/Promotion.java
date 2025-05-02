package model;
import java.sql.Date;
public class Promotion {
    private int bookId;
    private int discountPercent;
    private Date startDate;
    private Date endDate;

    public Promotion(int bookId, int discountPercent, Date startDate, Date endDate) {
        this.bookId = bookId;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
