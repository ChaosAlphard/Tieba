package ser.admin;

import com.common.ConnSQL;
import com.dao.TieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SetElite", urlPatterns = {"/SetElite"})
public class SetElite extends HttpServlet {
    private void sout(Object o){
        System.out.println("ser.admin.SetElite[log]: "+o);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String tieID = request.getParameter("tieID");
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        String lv = (String)session.getAttribute("adminLV");

        if(lv!=null&&(lv.equals("2")||lv.equals("3"))) {
            if (tieID != null && tieID.matches("^[0-9]+$")) {
                try {
                    sout("status:"+status);
                    int id = Integer.parseInt(tieID);
                    switch (status) {
                        case "0":
                            result = unsetElite(id);
                            break;
                        case "1":
                            result = setElite(id);
                            break;
                        default:
                            result = "err";
                            break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    ConnSQL.closeSQL();
                }
            }
        } else {
            result = "lvErr";
        }
        print(result, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    private String setElite(int tieID) throws SQLException, IOException {
        TieDao dao = new TieDao();
        int i = dao.setTieElite(tieID);
        if(i!=0){
            return "suc";
        } else {
            return "dataErr";
        }
    }
    private String unsetElite(int tieID) throws SQLException, IOException {
        TieDao dao = new TieDao();
        int i = dao.unsetTieElite(tieID);
        if(i!=0){
            return "suc";
        } else {
            return "dataErr";
        }
    }

    private void print(String result,HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
