package com.tieba.servlet;

import com.tieba.dao.BarFollowDao;
import com.tieba.tools.LogTool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FollowBar", urlPatterns = {"/FollowBar"})
public class FollowBar extends HttpServlet {
    private static final LogTool log = LogTool.of(FollowBar.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("UID");
        String barID = request.getParameter("barID");
        String barName = request.getParameter("barName");
        String follow = request.getParameter("follow");

        if(uid!=null&&uid.matches("^[0-9]+$")&&
        barID!=null&&barID.matches("^[0-9]+$")&&barName!=null){

            log.debug("follow: "+follow);
            int id = Integer.parseInt(uid);
            int bid= Integer.parseInt(barID);
            BarFollowDao dao = new BarFollowDao();
            int i=0;
            switch(follow) {
                case "0":
                    i=dao.followBar(id,bid,barName);
                    break;
                case "1":
                    i=dao.unfollowBar(id,bid);
                    break;
            }
            if(i!=0){
                result = "suc";
            } else {
                result = "dataErr";
            }
        }
        webPrint(result,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("UID");
        String barID = request.getParameter("barID");

        if(uid!=null&&uid.matches("^[0-9]+$")&&
          barID!=null&&barID.matches("^[0-9]+$")){
            int id = Integer.parseInt(uid);
            int bid= Integer.parseInt(barID);

            boolean isFollow = new BarFollowDao().isFollowBar(id,bid);

            if(isFollow){
                result = "followed";
            } else {
                result = "unfollow";
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
