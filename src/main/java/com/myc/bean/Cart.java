package com.myc.bean;

import java.util.ArrayList;
import java.util.List;

public class Cart
{
    private List<CartItem> cartItemList = new ArrayList<CartItem>();

    public List<CartItem> getCartItemList()
    {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList)
    {
        this.cartItemList = cartItemList;
    }

    public void addItem(Book book, int count)
    {
        for (CartItem cartItem : cartItemList)
        {
            if (book.getId().equals(cartItem.getBookId()))
            {
                cartItem.setCount(cartItem.getCount() + count);
                return;
            }
        }
        // 如果这里面没有这个图书的信息
        CartItem cartItem = new CartItem();
        cartItem.setBookId(book.getId());
        cartItem.setBookName(book.getName());
        cartItem.setCount(count);
        cartItem.setPrice(book.getPrice());
        cartItemList.add(cartItem);
    }

    public int getTotalCount()
    {
        int totalCount = 0;
        for (CartItem cartItem : cartItemList)
        {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    public double getTotalPrice()
    {
        double totalPrice = 0;
        for (CartItem cartItem : cartItemList)
        {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }

    public void updateItem(int bookId, int count)
    {
        for (CartItem cartItem : cartItemList)
        {
            if (bookId == cartItem.getBookId())
            {
                cartItem.setCount(count);
                return;
            }
        }
    }
}
