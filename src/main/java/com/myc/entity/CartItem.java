package com.myc.entity;

public class CartItem
{
    private int bookId;
    private String bookName;
    private int count;
    private double price;

    public double getTotalPrice()
    {
        return price * count;
    }

    public int getBookId()
    {
        return bookId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + bookId;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartItem other = (CartItem) obj;
        if (bookId != other.bookId)
            return false;
        return true;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
