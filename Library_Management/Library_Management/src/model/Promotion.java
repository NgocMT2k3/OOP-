package model;

public class Promotion {
    private int bookId;
    private int discountPercent;

    public Promotion(int bookId, int discountPercent) {
        this.bookId = bookId;
        this.discountPercent = discountPercent;
    }

    public int getBookId() {
        return bookId;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int applyDiscount(int originalPrice) {
        return originalPrice - (originalPrice * discountPercent / 100);
    }
}