package com.myc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.myc.entity.OrdersItem;
import com.myc.utils.JDBCUtils;

public class OrdersItemDao
{
    public void add(Connection conn, OrdersItem ordersItem) throws SQLException
    {
        String sql = "insert into ordersItem(ordersId,bookId,bookName,price,count) values(?,?,?,?,?)";
        JDBCUtils.executeUpdate(conn, sql, ordersItem.getOrdersId(), ordersItem.getBookId(), ordersItem.getBookName(),
                ordersItem.getPrice(), ordersItem.getCount());
    }
}
