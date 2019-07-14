package ser;

import com.common.ConnSQL;
import com.dao.NewTie;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;

@WebServlet(name = "NewTieServlet", urlPatterns = {"/NewTieServlet"})
public class NewTieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("NewTieServlet>>post================================================================================");

        String result = "err";
        HttpSession session = request.getSession();
        String suid = String.valueOf(session.getAttribute("uid"));
        String susr = String.valueOf(session.getAttribute("usr"));

        String barID = request.getParameter("barID");
        String uid = request.getParameter("uid");
        System.out.println("uid: "+uid);
        String usr = request.getParameter("usr");
        System.out.println("usr: "+usr);
        String title = request.getParameter("title");
        System.out.println("title: "+title);
        String main = request.getParameter("main");
        System.out.println("main: "+main);

        if(suid.equals(uid)&&susr.equals(usr)&&barID!=null&&title!=null&&main!=null
        &&barID.matches("^[0-9]+$")&&uid.matches("^[0-9]+$")&&
        title.length()>0&&title.length()<21&&main.length()>0&&main.length()<1000){

            UserDao ud = new UserDao();
            NewTie dao = new NewTie();
            try {
                int id = Integer.parseInt(uid);
                if(ud.findByID(id).getAdminLv()!=0){
                    int i=dao.CreateNewTie(Integer.parseInt(barID),title,main,usr,id);
                    if(i!=0){
                        result = "suc";
                    }
                } else {
                    result = "lvErr";
                }
            } catch (SQLException e) {
                result = "daoErr";
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }
        } else {
            result = "dataErr";
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        System.out.println("NewTieServlet<<post================================================================================");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
