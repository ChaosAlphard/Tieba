package com.tieba.servlet.feature;

import com.tieba.dao.BarDao;
import com.tieba.model.Bar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String bar = request.getParameter("search");
        if (bar!=null) {
            String search = String.join("%",bar.split(" "));

            List<Bar> lis = new BarDao().FindBars(search);

            request.setAttribute("list",lis);
            RequestDispatcher dis = request.getRequestDispatcher("search.jsp");
            dis.forward(request,response);
        }
    }
}
