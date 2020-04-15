package ser;

import com.dao.TieFavoriteDao;
import com.model.TieFavorite;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FavTiesServlet", urlPatterns = {"/FavTiesServlet"})
public class FavTiesServlet extends HttpServlet {
    private void print(Object o) {
        System.out.println("FavTiesServlet[log]: " + o);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("uid");

        if(uid!=null&&uid.matches("^[0-9]+$")){
            TieFavoriteDao dao = new TieFavoriteDao();
            List<TieFavorite> lis = dao.findFavoriteTies(Integer.parseInt(uid));
            if(lis!=null && !lis.isEmpty()){
                JSONArray json = JSONArray.fromObject(lis);
                result = String.valueOf(json);
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
