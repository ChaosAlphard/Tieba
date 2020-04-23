package com.tieba.servlet.feature;

import com.tieba.dao.UserDao;
import com.tieba.tools.LogTool;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final LogTool log = LogTool.of(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        log.info("LoginServlet>>post");

        /* 用户登录 */
        String result = "err";

        String account = request.getParameter("aot");
        String password = request.getParameter("pwd");

        String[] info = new UserDao().Login(account,password);
        log.info("info:"+info[0]+"-"+info[1]+"-"+info[2]);

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

        log.info("LoginServlet<<post");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        log.info("LoginServlet>>get");

        /* 账户状态检测 */
        String result = "noCookie";

        Cookie[] cs=request.getCookies();
        log.info("haveCookie:"+(cs!=null));
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
                log.info("SessionID: "+session.getAttribute("uid")+
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
            } else {
                result="noNameAndID";
            }
        }

        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        log.info("LoginServlet<<get");
    }
}
