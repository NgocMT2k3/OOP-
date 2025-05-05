package model;

import java.util.Date;

public class Sales 
{
    private Date day;
    private int sales;
    
    public Sales (Date day, int sales)
    {
        this.day = day;
        this.sales = sales;
    }
    
    public Sales(){}
    
    public void setDay(Date day) {this.day = day;}
    public void setSales(int sales) {this.sales = sales;}
    
    public Date getDay() { return day;}
    public int getSales () { return sales;}
}
