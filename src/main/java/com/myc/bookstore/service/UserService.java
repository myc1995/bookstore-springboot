package com.myc.bookstore.service;

import java.sql.SQLException;

import com.myc.bookstore.dao.UserDao;
import com.myc.bookstore.entity.User;

public class UserService
{
    private UserDao userDao = new UserDao();

    public boolean emailIsNotExist(String email)
    {
        try
        {
            return userDao.emailIsNotExist(email);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void register(User user)
    {
        try
        {
            userDao.register(user);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public User login(String email, String password)
    {
        try
        {
            return userDao.login(email, password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String email, String newpassword)
    {
        try
        {
            userDao.updatePassword(email, newpassword);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean passwordIsNotRight(String email, String password)
    {
        try
        {
            return userDao.passwordIsNotRight(email, password);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
