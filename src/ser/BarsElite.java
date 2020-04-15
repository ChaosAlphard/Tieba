package ser;

import com.dao.BarDao;
import com.dao.TieDao;
import com.model.Bar;
import com.model.Tie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        if(barID==null||!(barID.matches("[0-9]*"))){
            response.sendRedirect("search.jsp");
            return;
        }

        BarDao bdao = new BarDao();
        Bar b = bdao.FindById(Integer.parseInt(barID));

        if(b==null||b.getBarID()==0||b.getBarName()==null){
            response.sendRedirect("errPage/notFound.html");
            return;
        }
        if(b.getVisible()!=1){
            response.sendRedirect("errPage/barHidden.html");
            return;
        }

        TieDao tdao = new TieDao();
        List<Tie> lis = tdao.findEliteByBarID(b.getBarID());

        request.setAttribute("bar", b);
        request.setAttribute("ties",lis);

        RequestDispatcher dis = request.getRequestDispatcher("barElite.jsp");
        dis.forward(request, response);
    }
}
