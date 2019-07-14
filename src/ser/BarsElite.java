package ser;

import com.common.ConnSQL;
import com.dao.BarDao;
import com.dao.TieDao;
import com.model.Bar;
import com.model.Tie;
import com.tools.ChangePage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BarsElite", urlPatterns = {"/BarsElite"})
public class BarsElite extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String barID = request.getParameter("id");

        if(barID!=null&&barID.matches("[0-9]*")){
            BarDao bdao = new BarDao();
            TieDao tdao = new TieDao();
            Bar b = new Bar();
            List<Tie> lis = new ArrayList<>();
            try {
                b = bdao.FindById(Integer.parseInt(barID));
                lis = tdao.findEliteByBarID(b.getBarID());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }

            if(b.getBarID()!=0&&b.getBarName()!=null){
                if(b.getVisible()==1){
                    request.setAttribute("bar", b);
                    request.setAttribute("ties",lis);
                    RequestDispatcher dis = request.getRequestDispatcher("barElite.jsp");
                    dis.forward(request, response);
                } else {
                    response.sendRedirect("errPage/barHidden.html");
                }
            } else {
                response.sendRedirect("errPage/notFound.html");
            }
        } else {
            response.sendRedirect("search.jsp");
        }
    }
}
