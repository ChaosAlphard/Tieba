package ser.admin;

import com.dao.TieDao;
import com.model.Tie;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteTie", urlPatterns = {"/DeleteTie"})
public class DeleteTie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String tieID = request.getParameter("tieID");
        HttpSession session = request.getSession();
        String LV = (String)session.getAttribute("adminLV");

        if(LV!=null&&(LV.equals("2")||LV.equals("3"))) {
            if (tieID != null && tieID.matches("^[0-9]+$")) {
                TieDao dao = new TieDao();
                int id = Integer.parseInt(tieID);
                Tie t = dao.FindByID(id);
                if (t != null && t.getTieID() != 0 && t.getTieTitle() != null) {
                    if (dao.deleteTie(id) > 0) {
                        result = "suc";
                    } else {
                        result = "dataErr";
                    }
                }
            }
        } else {
            result = "lvErr";
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String tieID = request.getParameter("tieID");

        if(tieID!=null&&tieID.matches("^[0-9]+$")){
            TieDao dao = new TieDao();
            Tie t = dao.FindByID(Integer.parseInt(tieID));
            if(t!=null&&t.getTieID()!=0&&t.getTieTitle()!=null&&t.getVisible()!=0){
                result = String.valueOf(JSONArray.fromObject(t));
            } else {
                result = "dataErr";
            }
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
