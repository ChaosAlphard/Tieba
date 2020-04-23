package com.tieba.servlet;

import com.tieba.dao.BarFollowDao;
import com.tieba.model.BarFollow;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FavBarsServlet", urlPatterns = {"/FavBarsServlet"})
public class FavBarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("uid");

        if(uid==null||!(uid.matches("^[0-9]+$"))) {
            webPrint(result, response);
            return;
        }

        List<BarFollow> lis = new BarFollowDao().findFollowBars(Integer.parseInt(uid));
        if(lis!=null){
            JSONArray json = JSONArray.fromObject(lis);
            result = String.valueOf(json);
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
