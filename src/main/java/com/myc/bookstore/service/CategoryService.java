package com.myc.bookstore.service;

import java.util.List;

import com.myc.bookstore.dao.CategoryDao;
import com.myc.bookstore.entity.Category;

public class CategoryService
{
    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> list()
    {
        try
        {
            return categoryDao.list();
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

}
