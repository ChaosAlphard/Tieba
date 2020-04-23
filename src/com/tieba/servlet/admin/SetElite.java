package com.tieba.servlet.admin;

import com.tieba.dao.TieDao;
import com.tieba.tools.LogTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SetElite", urlPatterns = {"/SetElite"})
public class SetElite extends HttpServlet {
    private static final LogTool log = LogTool.of(SetElite.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String tieID = request.getParameter("tieID");
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        String lv = (String)session.getAttribute("adminLV");

        if(lv!=null&&(lv.equals("2")||lv.equals("3"))) {
            if (tieID != null && tieID.matches("^[0-9]+$")) {
                log.info("status: "+status);
                int id = Integer.parseInt(tieID);
                switch (status) {
                    case "0":
                        result = unsetElite(id);
                        break;
                    case "1":
                        result = setElite(id);
                        break;
                    default:
                        result = "err";
                        break;
                }
            }
        } else {
            result = "lvErr";
        }
        webPrint(result, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    private String setElite(int tieID) {
        int i = new TieDao().setTieElite(tieID);
        if(i > 0){
            return "suc";
        } else {
            return "dataErr";
        }
    }
    private String unsetElite(int tieID) {
        int i = new TieDao().unsetTieElite(tieID);
        if(i > 0){
            return "suc";
        } else {
            return "dataErr";
        }
    }

    private void webPrint(String result,HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
