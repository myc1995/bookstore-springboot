package com.myc.bookstore.service;

import com.myc.bookstore.dao.BookDao;
import com.myc.bookstore.entity.Book;
import com.myc.bookstore.utils.Page;
import com.myc.bookstore.utils.PageAjaxResult;

public class BookService
{
    private BookDao bookDao = new BookDao();

    public void list(Page<Book> page)
    {
        try
        {
            bookDao.list(page);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void list(Page<Book> page, Integer categoryId)
    {
        try
        {
            bookDao.list(page, categoryId);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void list(Page<Book> page, String searchText)
    {
        try
        {
            bookDao.list(page, searchText);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public Book findById(int bookId)
    {
        try
        {
            return bookDao.findById(bookId);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void list(PageAjaxResult pageAjaxResult)
    {
        try
        {
            bookDao.list(pageAjaxResult);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void list(PageAjaxResult pageAjaxResult, String searchText)
    {
        try
        {
            bookDao.list(pageAjaxResult, searchText);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

    public void add(Book book)
    {
        try
        {
            bookDao.add(book);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void update(Book book)
    {
        try
        {
            bookDao.update(book);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

    public void deleteById(int id)
    {
        try
        {
            bookDao.deleteById(id);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
    }

}
