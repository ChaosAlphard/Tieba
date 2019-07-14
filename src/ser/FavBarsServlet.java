package ser;

import com.common.ConnSQL;
import com.dao.BarFollowDao;
import com.dao.TieFavoriteDao;
import com.model.BarFollow;
import com.model.TieFavorite;
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

@WebServlet(name = "FavBarsServlet", urlPatterns = {"/FavBarsServlet"})
public class FavBarsServlet extends HttpServlet {
    private void print(Object o) {System.out.println("ser.FavBarsServlet[log]: "+o);}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("uid");

        if(uid!=null&&uid.matches("^[0-9]+$")){
            try {
                BarFollowDao dao = new BarFollowDao();
                List<BarFollow> lis = dao.findFollowBars(Integer.parseInt(uid));
                print(lis!=null && !lis.isEmpty());
                if(lis!=null) print("empty"+lis.isEmpty());
                if(lis!=null && !lis.isEmpty()){
                    JSONArray json = JSONArray.fromObject(lis);
                    result = String.valueOf(json);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }
        }
        webPrint(result,response);
    }

    private void webPrint(String result,HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
