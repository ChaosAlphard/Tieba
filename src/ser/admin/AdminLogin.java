package ser.admin;

import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("AdminLogin>>post================================================================================");

        String result = "err";

        String account = request.getParameter("aot");
        String password = request.getParameter("pwd");

        System.out.println(account);
        System.out.println(password);

        HttpSession session = request.getSession();

        UserDao dao = new UserDao();
        String[] info = dao.AdminLogin(account,password);
        System.out.println("info:"+info[0]+"-"+info[1]+"-"+info[2]);

        if(info[0]!=null&&info[1]!=null&&info[2]!=null){
            if(info[2].equals("2")||info[2].equals("3")){
                result = "suc";
                session.setAttribute("auid",info[0]);
                session.setAttribute("ausr",info[1]);
                session.setAttribute("adminLV",info[2]);
            } else {
                result = "lvErr";
            }
        } else {
            result="daoErr";
        }

        System.out.println("LoginResult>>"+result);

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        System.out.println("AdminLogin<<post================================================================================");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
