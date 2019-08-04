package com.myc.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myc.bookstore.entity.Category;
import com.myc.bookstore.service.CategoryService;
import com.myc.bookstore.utils.Utils;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet
{
    private CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    private void processListJson(HttpServletRequest request, HttpServletResponse response)
    {
        List<Category> category = categoryService.list();
        Utils.sendAjaxResponse(response, category);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if ("listJson".equals(action))
        {
            processListJson(request, response);
        }
    }

}
