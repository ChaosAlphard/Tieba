package ser;

import com.common.ConnSQL;
import com.dao.TieDao;
import com.model.Tie;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RecentlyTieServlet", urlPatterns = {"/RecentlyTieServlet"})
public class RecentlyTieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String result = "err";

        TieDao dao = new TieDao();
        JSONArray json = JSONArray.fromObject(dao.findRecentlyTie());
        result = String.valueOf(json);

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
