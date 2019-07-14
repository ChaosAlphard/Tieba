package ser;

import com.common.ConnSQL;
import com.dao.BarFollowDao;
import com.model.BarFollow;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "FollowBar", urlPatterns = {"/FollowBar"})
public class FollowBar extends HttpServlet {
    private void print(Object o) {
        System.out.println("FollowBar[log]: " + o);
    }

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
            try {
                print("follow: "+follow);
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
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
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
            try {
                int id = Integer.parseInt(uid);
                int bid= Integer.parseInt(barID);
                BarFollowDao dao = new BarFollowDao();
                BarFollow bf = dao.isFollowBar(id,bid);
                if(bf.getBarID()!=0&&bf.getBarName()!=null){
                    result = "followed";
                } else {
                    result = "unfollow";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnSQL.closeSQL();
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
