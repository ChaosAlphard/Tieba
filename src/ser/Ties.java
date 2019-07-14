package ser;

import com.common.ConnSQL;
import com.dao.BarDao;
import com.dao.TieDao;
import com.dao.TieReplyDao;
import com.model.Bar;
import com.model.Tie;
import com.model.TieReply;
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

@WebServlet(name = "Ties", urlPatterns = {"/Ties"})
public class Ties extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String tieID=request.getParameter("post");
        String pageS=request.getParameter("page");

        if(tieID!=null&&tieID.matches("^[0-9]+$")){
            BarDao bDao = new BarDao();
            TieDao tDao = new TieDao();
            TieReplyDao trDao = new TieReplyDao();
            Bar b = new Bar();
            Tie t = new Tie();
            List<TieReply> lis = new ArrayList<>();

            try {
                t = tDao.FindByID(Integer.parseInt(tieID));
                b = bDao.FindById(t.getBarID());
                lis = trDao.FindByTieID(Integer.parseInt(tieID));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }

            if(b.getBarID()!=0&&b.getBarName()!=null&&t.getVisible()==1){
                if(b.getVisible()==1){
                    int curPage = 1;
                    if(pageS!=null&&pageS.matches("^[0-9]+$")){
                        curPage = Integer.parseInt(pageS);
                    }
                    ChangePage<TieReply> cp = new ChangePage<>();
                    int pageCount = cp.countPage(lis);
                    if(pageCount==0){
                        pageCount=1;
                    }
                    if(curPage>pageCount||curPage==0){
                        curPage=1;
                    }
                    List<TieReply> trLis = cp.getSubList(lis,curPage);

                    request.setAttribute("bar",b);
                    request.setAttribute("tie",t);
                    request.setAttribute("tieReply",trLis);
                    request.setAttribute("pageCount",pageCount);
                    request.setAttribute("curPage",curPage);

                    RequestDispatcher dis = request.getRequestDispatcher("tie.jsp");
                    dis.forward(request,response);
                } else {
                    response.sendRedirect("errPage/barHidden.html");
                }
            } else {
                response.sendRedirect("errPage/notFound.html");
            }
        } else {
            response.sendRedirect("errPage/notFound.html");
        }
    }
}
