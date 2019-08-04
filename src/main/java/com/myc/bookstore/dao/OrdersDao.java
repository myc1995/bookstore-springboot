package com.myc.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myc.bookstore.entity.Orders;
import com.myc.bookstore.utils.JDBCUtils;

public class OrdersDao
{
    public int findUnpaidOrdersCount(Integer userId) throws SQLException
    {
        String sql = "select count(*) from orders where status='未支付' and userId=?";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, userId);
            rs.next();
            return rs.getInt(1);
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    public void add(Connection conn, Orders orders) throws SQLException
    {
        String sql = "insert into orders(id,userId,addressInfo,totalPrice,status,createTime) values(?,?,?,?,?,?)";
        JDBCUtils.executeUpdate(conn, sql, orders.getId(), orders.getUserId(), orders.getAddressInfo(),
                orders.getTotalPrice(), orders.getStatus(), orders.getCreateTime());
    }
}
