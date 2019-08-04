package com.myc.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myc.bookstore.entity.Book;
import com.myc.bookstore.entity.Cart;
import com.myc.bookstore.service.BookService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet
{
    private BookService bookService = new BookService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if ("addSubmit".equals(action))
        {
            processAddSubmit(request, response);
        }
        else if ("list".equals(action))
        {
            processList(request, response);
        }
    }

    private void processList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/cartList.jsp").forward(request, response);
    }

    private void processAddSubmit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 如果session中还没有购物车，则创建一个新的，并放入session
        if (cart == null)
        {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int count = 1;
        try
        {
            count = Integer.parseInt(request.getParameter("count"));
        }
        catch (Exception e)
        {
        }

        Book book = bookService.findById(bookId);
        System.out.println(book);
        System.out.println(bookId + count + "xxxxxxxxxxxxxxxxxxxxxx");
        cart.addItem(book, count);

        // 跳转到购物车列表页面：cartList.jsp
        request.getRequestDispatcher("/WEB-INF/jsp/cartList.jsp").forward(request, response);
    }
}
