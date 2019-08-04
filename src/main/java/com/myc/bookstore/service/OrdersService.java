package com.myc.bookstore.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.myc.bookstore.dao.OrdersDao;
import com.myc.bookstore.dao.OrdersItemDao;
import com.myc.bookstore.entity.Orders;
import com.myc.bookstore.entity.OrdersItem;
import com.myc.bookstore.utils.JDBCUtils;

public class OrdersService
{
    private OrdersDao ordersDao = new OrdersDao();
    private OrdersItemDao ordersItemDao = new OrdersItemDao();

    public int findUnpaidOrdersCount(Integer userId)
    {
        try
        {
            return ordersDao.findUnpaidOrdersCount(userId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void add(Orders orders)
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            // 保存订单信息
            ordersDao.add(conn, orders);
            // 保存订单项信息
            List<OrdersItem> ordersItemList = orders.getOrdersItemList();
            for (OrdersItem ordersItem : ordersItemList)
            {
                ordersItemDao.add(conn, ordersItem);
            }

            conn.commit();
        }
        catch (Exception e)
        {
            JDBCUtils.rollback(conn);
            throw new RuntimeException(e);
        }
        finally
        {
            JDBCUtils.close(conn);
        }

    }

}
