package com.myc.bookstore.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myc.bookstore.entity.Address;
import com.myc.bookstore.entity.User;
import com.myc.bookstore.service.AddressService;

@WebServlet("/address")
public class AddressServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private AddressService addressService = new AddressService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        System.out.println(action);
        if ("list".equals(action))
        {
            processList(request, response);
        }
        else if ("add".equals(action))
        {
            processAdd(request, response);
        }
        else if ("addSubmit".equals(action))
        {
            processAddSubmit(request, response);
        }
    }

    private void processAddSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO 自动生成的方法存根
        User user = (User) request.getSession().getAttribute("user");

        String consignee = request.getParameter("consignee");
        String phone = request.getParameter("phone");
        String location = request.getParameter("location");
        String isDefault = request.getParameter("isDefault");

        Address address = new Address();
        address.setUserId(user.getId());
        address.setConsignee(consignee);
        address.setPhone(phone);
        address.setLocation(location);
        address.setCreateTime(new Date());
        if ("yes".equals(isDefault))
        {
            address.setIsDefault(true);
        }

        // 执行添加
        addressService.add(address);

        request.getRequestDispatcher("/WEB-INF/jsp/addressAddSuccess.jsp").forward(request, response);
    }

    private void processAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("WEB-INF/jsp/addressAdd.jsp").forward(request, response);
    }

    private void processList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        int userId = Integer.parseInt(request.getParameter("id"));
        System.out.println(userId + "hhhhhhhhhhh");
        List<Address> addressList = addressService.findAddressById(userId);
        request.setAttribute("addressList", addressList);
        request.getRequestDispatcher("WEB-INF/jsp/addressList.jsp").forward(request, response);
    }

}
