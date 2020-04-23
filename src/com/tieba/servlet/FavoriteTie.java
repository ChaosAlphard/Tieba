package com.tieba.servlet;

import com.tieba.dao.TieFavoriteDao;
import com.tieba.tools.LogTool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FavoriteTie", urlPatterns = {"/FavoriteTie"})
public class FavoriteTie extends HttpServlet {
    private static final LogTool log = LogTool.of(FavoriteTie.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result="err";
        String uid = request.getParameter("UID");
        String tieID = request.getParameter("tieID");
        String tieTitle = request.getParameter("tieTitle");
        String favorite = request.getParameter("favorite");

        if(uid!=null&&uid.matches("^[0-9]+$")&&
        tieID!=null&&tieID.matches("^[0-9]+$")&&tieTitle!=null){

            log.debug("fav: "+favorite);
            int id = Integer.parseInt(uid);
            int tid= Integer.parseInt(tieID);
            TieFavoriteDao dao = new TieFavoriteDao();
            int i=0;
            switch(favorite) {
                case "0":
                    i=dao.favoriteTie(id,tid,tieTitle);
                    break;
                case "1":
                    i=dao.unfavoriteTie(id,tid);
                    break;
            }
            if(i!=0){
                result="suc";
            } else {
                result="dataErr";
            }
        }
        webPrint(result,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result="err";
        String uid = request.getParameter("UID");
        String tieID = request.getParameter("tieID");

        if(uid!=null&&uid.matches("^[0-9]+$")&&
          tieID!=null&&tieID.matches("^[0-9]+$")){
            int id = Integer.parseInt(uid);
            int tid= Integer.parseInt(tieID);
            TieFavoriteDao dao = new TieFavoriteDao();
            if(dao.isFavoriteTie(id,tid)){
                result="favorited";
            } else {
                result="unfavorite";
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
