package com.myc.service;

import com.myc.entities.Book;
import com.myc.entities.Category;
import com.myc.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public List<Book> getBookList(int pageIndex) {
        List<Book> bookList = bookMapper.list((pageIndex - 1) * 6);
        return bookList;
    }

    public List<Category> getCategoryList() {
        List<Category> categoryList = bookMapper.getCategoryList();
        return categoryList;
    }

    public Book getBookDetail(Integer bookId) {
        Book book = bookMapper.findById(bookId);
        return book;
    }

    public List<Book> getBookByCategory(Integer categoryId, Integer categoryIndex) {
        List<Book> bookList = bookMapper.listByCategory(categoryId, (categoryIndex - 1) * 6);
        return bookList;
    }

    public int getPageCount() {
        int pageCount;
        int bookCount = bookMapper.getBookCount();
        if (bookCount % 6 == 0) {
            pageCount = bookCount / 6;
        } else {
            pageCount = (bookCount / 6) + 1;
        }
        return pageCount;
    }

    public int getPageCountByCategoryId(Integer categoryId) {
        int pageCount;
        int bookCount = bookMapper.getBookCountByCategoryId(categoryId);
        if (bookCount % 6 == 0) {
            pageCount = bookCount / 6;
        } else {
            pageCount = (bookCount / 6) + 1;
        }
        return pageCount;
    }
}
