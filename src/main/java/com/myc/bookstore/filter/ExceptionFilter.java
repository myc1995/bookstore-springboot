package com.myc.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.myc.bookstore.utils.AjaxResult;
import com.myc.bookstore.utils.Utils;

@WebFilter("/*")
public class ExceptionFilter implements Filter
{
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        try
        {
            chain.doFilter(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            String action = request.getParameter("action");
            if (action != null)
            {
                if (action.endsWith("Json"))
                {
                    Utils.sendAjaxResponse(response, new AjaxResult("error", "服务器出错了"));
                }
                else
                {
                    request.getRequestDispatcher("/500.jsp").forward(request, response);
                }
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException
    {
    }

}
