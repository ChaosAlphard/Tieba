package com.tieba.servlet.feature;

import com.tieba.tools.LogTool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginConfirm", urlPatterns = {"/LoginConfirm"})
public class LoginConfirm extends HttpServlet {
    private static final LogTool log = LogTool.of(LoginConfirm.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        log.info("LoginConfirm>>post");

        String uid = request.getParameter("uid");
        String usr = request.getParameter("usr");

        HttpSession session=request.getSession();
        session.setAttribute("uid",uid);
        session.setAttribute("usr",usr);
        log.info("SetSession>>\n\tuid:"+uid+"\n\tusr:"+usr);

        String id = URLEncoder.encode(uid,"UTF-8");
        String name=URLEncoder.encode(usr,"UTF-8");

        Cookie idcok = new Cookie("uid",id);
        idcok.setMaxAge(604800);//7(d)*24(h)*60(m)*60(s)
        idcok.setPath("/");
        idcok.setHttpOnly(true);

        Cookie namecok=new Cookie("usr",name);
        namecok.setMaxAge(604800);
        namecok.setPath("/");
        namecok.setHttpOnly(true);

        response.addCookie(idcok);
        response.addCookie(namecok);

        RequestDispatcher dis = request.getRequestDispatcher("otherPage/loginFrame.jsp");
        dis.forward(request,response);

        log.info("LoginConfirm<<post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
