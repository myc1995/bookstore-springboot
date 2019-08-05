package com.myc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.myc.entity.User;
import com.myc.utils.JDBCUtils;

public class UserDao
{

    public boolean emailIsNotExist(String email) throws SQLException
    {
        String sql = "select * from user where email=?";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, email);
            if (rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    public int register(User user) throws SQLException
    {
        String sql = "insert into user(email,password)values(?,?);";
        return JDBCUtils.executeUpdate(sql, user.getEmail(), user.getPassword());
    }

    public User login(String email, String password) throws SQLException
    {
        String sql = "select * from user where email=? and password=?";

        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, email, password);
            return JDBCUtils.packEntity(User.class, rs);
            /*
             * if (rs.next()) { User user = new User();
             * user.setEmail(rs.getString("email"));
             * user.setId(rs.getInt("id")); user.setRole(rs.getString("role"));
             * return user; } return null;
             */
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }

    public int updatePassword(String email, String newpassword) throws SQLException
    {
        String sql = "update user set password=? where email=?";
        return JDBCUtils.executeUpdate(sql, newpassword, email);
    }

    public boolean passwordIsNotRight(String email, String password) throws SQLException
    {
        String sql = "select * from user where email=? and password=?";
        ResultSet rs = null;
        try
        {
            rs = JDBCUtils.executeQuery(sql, email, password);
            return !rs.next();
        }
        finally
        {
            JDBCUtils.closeAll(rs);
        }
    }
}
