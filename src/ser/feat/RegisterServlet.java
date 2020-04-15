package ser.feat;

import com.dao.UserDao;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String result = "err";

        String aot=request.getParameter("account");
        String usr=request.getParameter("nickname");
        String pwd=request.getParameter("password");

        System.out.println(aot);
        System.out.println(usr);
        System.out.println(pwd);

        UserDao dao=new UserDao();
        //判断账户名是否存在
        User u = dao.FindSingle("Account",aot);
        if (u!=null){
            result="aot";
        } else {
            //判断昵称是否存在
            u = dao.FindSingle("Username",usr);
            if(u!=null){
                result="usr";
            } else {
                //判断是否添加成功
                int i=dao.Register(aot,usr,pwd);
                if(i!=0){
                    result="suc";
                }
            }
        }

        PrintWriter out=response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
