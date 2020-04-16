package com.tieba.servlet.feature;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String uid = null;
        String usr = null;
        Cookie[] cs = request.getCookies();
        if(cs!=null){
            for (Cookie c : cs) {
                if(c.getName().equals("uid")){
                    uid = c.getValue();
                    c.setMaxAge(0);//设为0表示立即删除
                    response.addCookie(c);
                }
                if(c.getName().equals("usr")){
                    usr = c.getValue();
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }

        HttpSession session = request.getSession();
        session.removeAttribute("uid");
        session.removeAttribute("usr");
        session.invalidate();//销毁session

        System.out.println("==================== ["+uid+"]"+usr+" 注销 ====================");
        RequestDispatcher dis = request.getRequestDispatcher("otherPage/logOut.jsp");
        dis.forward(request,response);
    }
}
