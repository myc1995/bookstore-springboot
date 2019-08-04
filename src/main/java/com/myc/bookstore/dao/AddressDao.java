package com.myc.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.myc.bookstore.entity.Address;
import com.myc.bookstore.utils.JDBCUtils;

public class AddressDao
{

    public List<Address> list(int userId) throws SQLException
    {
        String sql = "select * from address where userId = ? order by isDefault desc,createTime desc";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, userId);
            return JDBCUtils.packEntityList(Address.class, rs);
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    public int count(Connection conn, int userId) throws SQLException
    {
        String sql = "select count(*) from address where userId=?";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(conn, sql, userId);
            rs.next();
            return rs.getInt(1);
        }
        finally
        {
            JDBCUtils.closeResultSetAndStatement(rs);
        }
    }

    public int cancelExistingDefault(Connection conn, int userId) throws SQLException
    {
        String sql = "update address set isDefault=false where userid=? and isDefault=true";
        return JDBCUtils.executeUpdate(conn, sql, userId);
    }

    public int add(Connection conn, Address address) throws SQLException
    {
        String sql = "insert into address(userId,consignee,phone,location,createTime,isDefault) values(?,?,?,?,?,?)";
        return JDBCUtils.executeUpdate(conn, sql, address.getUserId(), address.getConsignee(), address.getPhone(),
                address.getLocation(), address.getCreateTime(), address.getIsDefault());
    }

    public Address findById(int id, Integer userId) throws SQLException
    {
        String sql = "select * from address where id=? and userId=?";
        ResultSet rs = null;

        try
        {
            rs = JDBCUtils.executeQuery(sql, id, userId);

            return JDBCUtils.packEntity(Address.class, rs);
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

}
