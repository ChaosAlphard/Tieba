package com.tieba.servlet.admin;

import com.tieba.tools.LogTool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminLogout", urlPatterns = {"/AdminLogout"})
public class AdminLogout extends HttpServlet {
    private static final LogTool log = LogTool.of(AdminLogout.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("ausr");
        session.removeAttribute("auid");
        session.removeAttribute("ausr");
        session.removeAttribute("adminLV");
        session.invalidate();//销毁session

        log.info(user+" is logout");
        RequestDispatcher dis = request.getRequestDispatcher("/otherPage/logOut.jsp");
        dis.forward(request,response);
    }
}
