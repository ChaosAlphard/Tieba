package com.tieba.servlet.admin;

import com.tieba.dao.UserDao;
import com.tieba.model.User;
import com.tieba.tools.LogTool;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BannedUser", urlPatterns = {"/BannedUser"})
public class BannedUser extends HttpServlet {
    private static final LogTool log = LogTool.of(BannedUser.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String uid = request.getParameter("userID");
        String status = request.getParameter("status");
        HttpSession session = request.getSession();
        String lv = (String)session.getAttribute("adminLV");

        if(uid==null||!uid.matches("^[0-9]+$")){
            webPrint("err",response);
            return;
        }
        if("1".equals(uid)) {
            webPrint("lock", response);
            return;
        }
        if(!"3".equals(lv)){
            webPrint("lvErr", response);
            return;
        }

        log.info("status: "+status);
        int id = Integer.parseInt(uid);
        int i=0;
        UserDao dao = new UserDao();
        switch (status) {
            case "0":
                i=dao.bannedUser(id);
                break;
            case "1":
                i=dao.unbannedUser(id);
                break;
            case "2":
                i=dao.setAsAdmin(id);
                break;
        }
        String result;
        if(i!=0){
            result="suc";
        } else {
            result="dataErr";
        }
        webPrint(result,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String result = "err";
        String uid = request.getParameter("userID");

        if(uid!=null&&uid.matches("^[0-9]+$")){

            User u = new UserDao().findByID(Integer.parseInt(uid));
            if(u!=null&&u.getUID()!=0&&u.getAccount()!=null){
                result = String.valueOf(JSONArray.fromObject(u));
            } else {
                result = "dataErr";
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
