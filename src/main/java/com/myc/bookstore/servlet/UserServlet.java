package com.myc.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myc.bookstore.entity.User;
import com.myc.bookstore.service.UserService;
import com.myc.bookstore.utils.AjaxResult;
import com.myc.bookstore.utils.Utils;

@WebServlet("/user")
public class UserServlet extends HttpServlet
{
    private UserService userService = new UserService();
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if ("register".equals(action))
        {
            processRegister(request, response);
        }
        else if ("registerSubmit".equals(action))
        {
            prcessRegisterSubmit(request, response);
        }
        else if ("login".equals(action))
        {
            processLogin(response, request);
        }
        else if ("loginSubmit".equals(action))
        {
            processLoginSubmit(response, request);
        }
        else if ("logout".equals(action))
        {
            processLogout(response, request);
        }
        else if ("passwordRetrieve".equals(action))
        {
            processPasswordRetrieve(response, request);
        }
        else if ("sendCheckCodeJson".equals(action))
        {
            processSendCheckCodeJson(request, response);
        }
        else if ("passwordRetrieveSubmit".equals(action))
        {
            processPasswordRetrieveSubmit(response, request);
        }
        else if ("passwordUpdate".equals(action))
        {
            processPasswordUpdate(response, request);
        }
        else if ("passwordUpdateSubmit".equals(action))
        {
            processPasswordUpdateSubmit(request, response);
        }
    }

    private void processPasswordUpdateSubmit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("user");
        // 原密码
        String password = request.getParameter("password");
        // 新密码
        String newpassword = request.getParameter("newpassword");

        String imageCode = request.getParameter("imageCode");
        // 原密码长度检查
        if (Utils.isNotMatchImageCode(imageCode, request.getSession()))
        {
            request.setAttribute("message", "验证码不正确，请重新输入");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdate.jsp").forward(request, response);
            return;
        }
        if (Utils.isEmptyOrNotLengthEnough(password, 6))
        {
            request.setAttribute("message", "原密码长度至少6位");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdate.jsp").forward(request, response);
            return;
        }

        // 原密码正确性检查--需要查询数据库
        if (userService.passwordIsNotRight(user.getEmail(), Utils.md5(password)))
        {
            request.setAttribute("message", "原密码不正确，请重新输入");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdate.jsp").forward(request, response);
            return;
        }

        // 新密码非空检查
        if (Utils.isEmptyOrNotLengthEnough(newpassword, 6))
        {
            request.setAttribute("message", "新密码长度不能小于6位");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdate.jsp").forward(request, response);
            return;
        }

        // 执行修改密码的业务逻辑
        userService.updatePassword(user.getEmail(), Utils.md5(newpassword));

        request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdateSuccess.jsp").forward(request, response);
    }

    private void processPasswordUpdate(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException
    {
        // TODO 自动生成的方法存根
        request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdate.jsp").forward(request, response);
    }

    private void processPasswordRetrieveSubmit(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String checkCode = request.getParameter("checkCode");
        String newPassword = request.getParameter("newpassword");

        if (Utils.isEmptyOrNotEmail(email))
        {
            request.setAttribute("message", "请输入正确的邮箱格式");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordRetrieve.jsp").forward(request, response);
            return;
        }
        else
        {
            if (userService.emailIsNotExist(email))
            {
                request.setAttribute("message", "此邮箱没有被注册，不能用来找回密码");
                request.getRequestDispatcher("/WEB-INF/jsp/userPasswordRetrieve.jsp").forward(request, response);
                return;
            }
        }
        if (Utils.isNotMatchMailCode(checkCode, request.getSession()))
        {
            request.setAttribute("message", "邮件验证码错误，请重新获取并填写");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordRetrieve.jsp").forward(request, response);
            return;
        }
        if (Utils.isEmptyOrNotLengthEnough(newPassword, 6))
        {
            request.setAttribute("message", "新密码长度至少6位");
            request.getRequestDispatcher("/WEB-INF/jsp/userPasswordRetrieve.jsp").forward(request, response);
            return;
        }
        // 处理修改密码的业务逻辑
        userService.updatePassword(email, Utils.md5(newPassword));
        request.getRequestDispatcher("/WEB-INF/jsp/userPasswordUpdateSuccess.jsp").forward(request, response);
    }

    private void processSendCheckCodeJson(HttpServletRequest request, HttpServletResponse response)
    {
        String email = request.getParameter("email");
        System.out.println(email);
        // 检查非空、格式
        if (Utils.isEmptyOrNotEmail(email))
        {
            Utils.sendAjaxResponse(response, new AjaxResult("error", "请输入正确的邮箱地址"));
            return;
        }

        // 检查此邮箱是否已经注册
        if (userService.emailIsNotExist(email))
        {
            Utils.sendAjaxResponse(response, new AjaxResult("error", "此邮箱没有被注册"));
            return;
        }

        // 尝试发送验证码邮件
        boolean result = Utils.sendMailCode4PasswordRetrieve(request.getSession(), email);
        // 如果发送成功
        if (result)
        {
            Utils.sendAjaxResponse(response, new AjaxResult("success", "发送验证码邮件成功"));
        }
        else
        {
            Utils.sendAjaxResponse(response, new AjaxResult("error", "发送验证码邮件失败"));
        }

    }

    private void processPasswordRetrieve(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/userPasswordRetrieve.jsp").forward(request, response);
    }

    private void processLogout(HttpServletResponse response, HttpServletRequest request) throws IOException
    {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/user?action=login");
    }

    private void processLoginSubmit(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String imageCode = request.getParameter("imageCode");

        if (Utils.isEmptyOrNotEmail(email))
        {
            request.setAttribute("message", "请输入正确的邮箱格式");
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }
        if (Utils.isEmptyOrNotLengthEnough(password, 6))
        {
            request.setAttribute("message", "密码至少6位");
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }
        if (Utils.isNotMatchImageCode(imageCode, request.getSession()))
        {
            request.setAttribute("message", "验证码不正确，请重新输入");
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }

        User user = userService.login(email, Utils.md5(password));
        if (user == null)
        {
            request.setAttribute("message", "邮箱或密码错误");
            request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
            return;
        }

        // 登录成功后，需要把user对象设置到session
        request.getSession().setAttribute("user", user);

        // 判断用户角色，如果是管理员的话，就把请求重定向到ManagerServlet
        if ("manager".equals(user.getRole()))
        {
            response.sendRedirect(request.getContextPath() + "/manager");
            return;
        }
        response.sendRedirect(request.getServletContext().getContextPath());
        // request.getServletContext().getContextPath()就是项目的目录
    }

    private void processLogin(HttpServletResponse response, HttpServletRequest request)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
    }

    private void prcessRegisterSubmit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String imageCode = request.getParameter("imageCode");

        if (Utils.isNotMatchImageCode(imageCode, request.getSession()))
        {
            // 不匹配
            request.setAttribute("message", "验证码不正确，请重新输入");
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
            return;
        }

        if (Utils.isEmptyOrNotEmail(email))
        {
            // 检查失败
            request.setAttribute("message", "请输入正确的邮箱格式");
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
            return;
        }
        else
        {
            if (!userService.emailIsNotExist(email))
            {
                request.setAttribute("message", "此邮箱已经被注册过");
                request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
                return;
            }
        }

        if (Utils.isEmptyOrNotLengthEnough(password, 6))
        {
            request.setAttribute("message", "密码至少6位");
            request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(Utils.md5(password));// md5加密

        userService.register(user);

        request.setAttribute("message", "注册成功，请登录");
        request.getRequestDispatcher("/WEB-INF/jsp/userLogin.jsp").forward(request, response);
    }

    private void processRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/userRegister.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request, response);
    }
}
