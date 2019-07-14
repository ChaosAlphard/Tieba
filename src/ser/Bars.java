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

@WebServlet(name = "Bars", urlPatterns = {"/Bars"})
public class Bars extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String barID = request.getParameter("id");
        String pageS = request.getParameter("page");
        //barID的值为null时调用equals()会产生空指针异常
        //String.valueOf()方法会将null字符串的值转换为"null"
        //从而避免空指针异常的产生
        if(barID==null||String.valueOf(barID).equals("")||!(barID.matches("[0-9]*"))){
            response.sendRedirect("search.jsp");
        } else {
            BarDao bdao = new BarDao();
            TieDao tdao = new TieDao();
            Bar b = new Bar();
            List<Tie> lis = new ArrayList<>();
            try {
                b = bdao.FindById(Integer.parseInt(barID));
                lis = tdao.FindByBarID(b.getBarID());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
            }

            if(b.getBarID()!=0&&b.getBarName()!=null){
                if(b.getVisible()!=1){
                    response.sendRedirect("errPage/barHidden.html");
                } else {
                    int curPage = 1;
                    if(pageS!=null&&pageS.matches("^[0-9]+$")){
                        curPage = Integer.parseInt(pageS);
                    }
                    ChangePage<Tie> cp = new ChangePage<>();
                    int pageCount = cp.countPage(lis);
                    if(pageCount==0){
                        pageCount=1;
                    }
                    if(curPage>pageCount||curPage==0){
                        curPage=1;
                    }
                    List<Tie> tLis = cp.getSubList(lis,curPage);

                    request.setAttribute("bar", b);
                    request.setAttribute("ties",tLis);
                    request.setAttribute("pageCount",pageCount);
                    request.setAttribute("curPage",curPage);
                    RequestDispatcher dis = request.getRequestDispatcher("bar.jsp");
                    dis.forward(request, response);
                }
            } else {
                response.sendRedirect("errPage/notFound.html");
            }
        }
    }
}
