package com.myc.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myc.bookstore.entity.User;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
        {
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }
        if (!"manager".equals(user.getRole()))
        {
            request.getRequestDispatcher("/nopermission.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/manager/managerIndex.jsp").forward(request, response);
    }

}
