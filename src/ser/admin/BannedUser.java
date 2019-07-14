package ser.admin;

import com.common.ConnSQL;
import com.dao.UserDao;
import com.model.User;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "BannedUser", urlPatterns = {"/BannedUser"})
public class BannedUser extends HttpServlet {
    private void print(Object o) {System.out.println("ser.admin.BannedUser[log]: " + o);}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("userID");
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        String lv = (String)session.getAttribute("adminLV");

        if(uid!=null&&uid.equals("1")){
            result = "lock";
        } else {
            if(lv!=null&&lv.equals("3")){
                if(uid!=null&&uid.matches("^[0-9]+$")){
                    try {
                        print(status);
                        int id = Integer.parseInt(uid);
                        int i=0;
                        UserDao dao = new UserDao();
                        switch (status) {
                            case "0":
                                i=dao.bannedUser(id);
                                break;
                            case "1":
                                i=dao.unbannedUser(id);
                                break;
                            case "2":
                                i=dao.setAsAdmin(id);
                                break;
                        }
                        if(i!=0){
                            result="suc";
                        } else {
                            result="dataErr";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        ConnSQL.closeSQL();
                    }
                }
            } else {
                result = "lvErr";
            }
        }

        webPrint(result,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("userID");

        if(uid!=null&&uid.matches("^[0-9]+$")){
            UserDao dao = new UserDao();
            try {
                User u = dao.findByID(Integer.parseInt(uid));
                if(u!=null&&u.getUID()!=0&&u.getAccount()!=null){
                    result = String.valueOf(JSONArray.fromObject(u));
                } else {
                    result = "dataErr";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }
        }
        webPrint(result,response);
    }

    private void webPrint(String result, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
