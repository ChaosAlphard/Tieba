package com.tieba.servlet;

import com.tieba.dao.NewTie;
import com.tieba.tools.LogTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NewTieServlet", urlPatterns = {"/NewTieServlet"})
public class NewTieServlet extends HttpServlet {
    private static final LogTool log = LogTool.of(NewTieServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        log.info("NewTieServlet>>post");

        String result = "err";
        HttpSession session = request.getSession();
        String suid = String.valueOf(session.getAttribute("uid"));
        String susr = String.valueOf(session.getAttribute("usr"));

        String barID = request.getParameter("barID");
        String uid = request.getParameter("uid");
        String usr = request.getParameter("usr");
        String title = request.getParameter("title");
        String main = request.getParameter("main");

        log.info("CreateNewTie: \n\tBarId: "
                +barID+"\n\tUserId: "+uid+
                "\n\tUserName: "+usr+
                "\n\tTieTitle: "+title+
                "\n\tTieContent: "+main);

        if(suid.equals(uid)&&susr.equals(usr)&&barID!=null&&title!=null&&main!=null
        &&barID.matches("^[0-9]+$")&&uid.matches("^[0-9]+$")&&
        title.length()>0&&title.length()<21&&main.length()>0&&main.length()<1000){

            int i = new NewTie().CreateNewTie(Integer.parseInt(barID),title,main,usr,Integer.parseInt(uid));
            if(i!=0){
                result = "suc";
            }
        } else {
            result = "dataErr";
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();

        log.info("NewTieServlet<<post");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
