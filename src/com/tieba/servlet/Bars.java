package com.tieba.servlet;

import com.tieba.dao.BarDao;
import com.tieba.dao.TieDao;
import com.tieba.model.Bar;
import com.tieba.model.Tie;
import com.tieba.tools.ChangePage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        if(barID==null||"".equals(barID)||!(barID.matches("[0-9]*"))){
            response.sendRedirect("search.jsp");
            return;
        }

        Bar b = new BarDao().FindById(Integer.parseInt(barID));
        if(b==null||b.getBarID()==0||b.getBarName()==null){
            response.sendRedirect("errPage/notFound.html");
            return;
        }
        if(b.getVisible()!=1){
            response.sendRedirect("errPage/barHidden.html");
            return;
        }

        List<Tie> lis = new TieDao().FindByBarID(b.getBarID());
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
}
