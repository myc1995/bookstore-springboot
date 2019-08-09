package com.myc.controller;

import com.myc.entities.Book;
import com.myc.entities.Category;
import com.myc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/bookList/{pageIndex}")
    public String bookList(Model model, @PathVariable("pageIndex") Integer pageIndex) {
        List<Book> bookList = bookService.getBookList(pageIndex);
        List<Category> categoryList = bookService.getCategoryList();
        model.addAttribute("bookList", bookList);
        model.addAttribute("categoryList", categoryList);
        return "book/bookList";
    }
}
