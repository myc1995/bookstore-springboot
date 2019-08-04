package com.myc.bookstore.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.myc.bookstore.dao.AddressDao;
import com.myc.bookstore.entity.Address;
import com.myc.bookstore.utils.JDBCUtils;

public class AddressService
{
    private AddressDao addressDao = new AddressDao();

    public List<Address> findAddressById(int userId)
    {
        try
        {
            return addressDao.list(userId);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void add(Address address)
    {
        Connection conn = null;
        try
        {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);

            // 如果不是默认收货地址
            if (!address.getIsDefault())
            {
                // 如果数据库里没有其他收货地址，本次添加的默认就是收货地址
                int count = addressDao.count(conn, address.getUserId());
                if (count == 0)
                {
                    address.setIsDefault(true);
                }
            }
            // 如果现在是默认收货地址，需要把其他默认的收货地址设置非默认的
            if (address.getIsDefault())
            {
                // 先取消现有的默认收货地址
                addressDao.cancelExistingDefault(conn, address.getUserId());
            }
            // 执行添加
            addressDao.add(conn, address);
            // 提交事务
            conn.commit();
        }
        catch (SQLException e)
        {
            try
            {
                conn.rollback();
            }
            catch (SQLException e1)
            {
                // 应该记录日志的
            }
            throw new RuntimeException(e);
        }
        finally
        {
            JDBCUtils.close(conn);
        }
    }

    public List<Address> list(Integer userId)
    {
        try
        {
            return addressDao.list(userId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Address findById(Integer id, Integer userId)
    {
        try
        {
            return addressDao.findById(id, userId);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
