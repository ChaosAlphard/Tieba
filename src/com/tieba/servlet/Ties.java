package com.tieba.servlet;

import com.tieba.dao.BarDao;
import com.tieba.dao.TieDao;
import com.tieba.dao.TieReplyDao;
import com.tieba.model.Bar;
import com.tieba.model.Tie;
import com.tieba.model.TieReply;
import com.tieba.tools.ChangePage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        if(tieID==null||!(tieID.matches("^[0-9]+$"))) {
            response.sendRedirect("errPage/notFound.html");
            return;
        }

        Tie t = new TieDao().FindByID(Integer.parseInt(tieID));
        if(t == null) {
            response.sendRedirect("errPage/notFound.html");
            return;
        }

        Bar b = new BarDao().FindById(t.getBarID());
        if(b==null||b.getBarID()==0||b.getBarName()==null||t.getVisible()!=1){
            response.sendRedirect("errPage/notFound.html");
            return;
        }
        if(b.getVisible()!=1){
            response.sendRedirect("errPage/barHidden.html");
            return;
        }

        List<TieReply> lis = new TieReplyDao().FindByTieID(Integer.parseInt(tieID));

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
    }
}
