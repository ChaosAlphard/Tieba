package com.tieba.servlet.feature;

import com.tieba.dao.UserDao;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("LoginServlet>>post================================================================================");

        /* 用户登录 */
        String result = "err";

        String account = request.getParameter("aot");
        String password = request.getParameter("pwd");

        String[] info = new UserDao().Login(account,password);
        System.out.println("info:"+info[0]+"-"+info[1]+"-"+info[2]);

        if(info[0]!=null&&info[1]!=null&&info[2]!=null){
            if(info[2].equals("0")){
                result="blocked";
            } else {
                JSONArray json = JSONArray.fromObject(info);
                result=String.valueOf(json);
            }
        }

        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        System.out.println("LoginServlet<<post================================================================================");
        //if(result.equals("success")){
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
        //    //idcok.setPath("/");
        //    //idcok.setHttpOnly(true);
        //
        //    Cookie namecok=new Cookie("usr",name);
        //    namecok.setMaxAge(864000);
        //    //namecok.setPath("/");
        //    //namecok.setHttpOnly(true);
        //
        //    response.addCookie(idcok);
        //    response.addCookie(namecok);
        //
        //    System.out.println("Dispatcher>>forward");
        //    RequestDispatcher dis = request.getRequestDispatcher("SearchServlet");
        //    //空字符串相当于转发给自身
        //    //RequestDispatcher dis = request.getRequestDispatcher("");
        //    dis.forward(request,response);
        //} else {
        //
        //}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("LoginServlet>>get================================================================================");

        /* 账户状态检测 */
        String result = "noCookie";

        Cookie[] cs=request.getCookies();
        System.out.println("haveCookie:"+(cs!=null));
        if(cs!=null){
            String uid=null;
            String usr=null;
            //boolean uidHttp=false;
            //boolean usrHttp=false;
            for (Cookie c : cs) {
                if(c.getName().equals("uid")){
                    uid=URLDecoder.decode(c.getValue(),"UTF-8");
                }
                if(c.getName().equals("usr")){
                    usr=URLDecoder.decode(c.getValue(),"UTF-8");
                }
            }

            if(uid!=null&&usr!=null){
                HttpSession session=request.getSession();
                System.out.println("SessionID: "+session.getAttribute("uid")+
                                   "      \tCookieID: "+uid +
                                   "\nSessionUser: "+session.getAttribute("usr")+
                                   "  \tCookieUser: "+usr);
                if(session.getAttribute("uid")==null||session.getAttribute("usr")==null){
                    session.setAttribute("uid",uid);
                    session.setAttribute("usr",usr);
                    result="reload";
                } else {
                    result="haveSession";
                }

                //System.out.println("notHttpOnly:"+!(uidHttp&&usrHttp));
                //System.out.println(uidHttp);
                //System.out.println(usrHttp);
                //if(!(uidHttp&&usrHttp)){
                //    result="notHttpOnly";
                //}
            } else {
                result="noNameAndID";
            }
        }

        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        System.out.println("LoginServlet<<get================================================================================");
        //UserDao dao=new UserDao();
        //try {
        //    User u=dao.FindSingle("UID",uid);
        //    if(u.getAdminLv()!=0){
        //        result="suc";
        //    }
        //} catch (SQLException e) {
        //    result="err";
        //    e.printStackTrace();
        //} finally {
        //    ConnSQL.closeSQL();
        //}
    }
}
