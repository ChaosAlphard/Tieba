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

        System.out.println("Register========================================\n\tAccount: "
                +aot+"\n\tNickname: "+usr+"\n\tPassword: "+pwd);

        UserDao dao=new UserDao();
        //判断账户名是否存在
        User user = dao.FindSingle("Account",aot);
        System.out.println(user);
        if (user!=null){
            System.out.println(user.toString());
            result="aot";
        } else {
            //判断昵称是否存在
            user = dao.FindSingle("Username",usr);
            if(user!=null){
                result="usr";
            } else {
                //判断是否添加成功
                if(dao.Register(aot,usr,pwd) > 0){
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
