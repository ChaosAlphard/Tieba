package com.tieba.servlet.feature;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginConfirm", urlPatterns = {"/LoginConfirm"})
public class LoginConfirm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("LoginConfirm>>post================================================================================");

        String uid = request.getParameter("uid");
        String usr = request.getParameter("usr");

        HttpSession session=request.getSession();
        session.setAttribute("uid",uid);
        session.setAttribute("usr",usr);
        System.out.println("SetSession>>\n\tuid:"+uid+"\n\tusr:"+usr);

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

        System.out.println("LoginConfirm<<post================================================================================");

        //String account = request.getParameter("aot");
        //String password = request.getParameter("pwd");
        //
        //String result = "err";
        //String[] info = new String[3];
        //
        //UserDao dao=new UserDao();
        //try {
        //    info=dao.Login(account,password);
        //} catch (SQLException e) {
        //    e.printStackTrace();
        //} finally {
        //    ConnSQL.closeSQL();
        //}
        //if(info[0]!=null&&info[1]!=null&&info[2]!=null){
        //    //JSONArray json = JSONArray.fromObject(info);
        //    //result=String.valueOf(json);
        //    if(info[2].equals("0")){
        //        result="blocked";
        //    } else {
        //        result="suc";
        //    }
        //}
        //if(result.equals("suc")){
        //    HttpSession session=request.getSession();
        //    session.setAttribute("uid",info[0]);
        //    session.setAttribute("nickname",info[1]);
        //    System.out.println("SetSession>>\n\tuid:"+info[0]+"\n\tnickname:"+info[1]);
        //    System.out.println("path: "+ request.getRequestURI());
        //
        //    String id = URLEncoder.encode(info[0],"UTF-8");
        //    String name=URLEncoder.encode(info[1],"UTF-8");
        //
        //    Cookie idcok = new Cookie("uid",id);
        //    idcok.setMaxAge(864000);//10(d)*24(h)*60(m)*60(s)
        //    idcok.setPath("/");
        //    idcok.setHttpOnly(true);
        //
        //    Cookie namecok=new Cookie("usr",name);
        //    namecok.setMaxAge(864000);
        //    namecok.setPath("/");
        //    namecok.setHttpOnly(true);
        //
        //    response.addCookie(idcok);
        //    response.addCookie(namecok);
        //
        //    System.out.println("Dispatcher>>forward");
        //    //RequestDispatcher dis = request.getRequestDispatcher("SearchServlet");
        //    //空字符串相当于转发给自身
        //    //RequestDispatcher dis = request.getRequestDispatcher("");
        //    RequestDispatcher dis = request.getRequestDispatcher("search.jsp");
        //    dis.forward(request,response);
        //}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
