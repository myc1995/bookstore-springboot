package com.myc.bookstore.entity;

import java.util.Date;
import java.util.List;

public class Orders
{
    private String id;
    private int userId;
    private String addressInfo;
    private double totalPrice;
    private String status;
    private Date createTime;

    private List<OrdersItem> ordersItemList;

    public List<OrdersItem> getOrdersItemList()
    {
        return ordersItemList;
    }

    public void setOrdersItemList(List<OrdersItem> ordersItemList)
    {
        this.ordersItemList = ordersItemList;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Orders other = (Orders) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getAddressInfo()
    {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo)
    {
        this.addressInfo = addressInfo;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
