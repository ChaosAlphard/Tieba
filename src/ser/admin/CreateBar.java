package ser.admin;

import com.dao.BarDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateBar", urlPatterns = {"/CreateBar"})
public class CreateBar extends HttpServlet {
    private void print(Object o) {
        System.out.println("CreateBar[log]: " + o);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String result="err";
        String name=request.getParameter("barName");
        String cont=request.getParameter("barContent");

        if(name!=null&&name.length()>0&&name.length()<=12&&
          !name.matches("[^A-z0-9\u4e00-\u9fa5]")&&
          cont!=null&&cont.length()>0&&cont.length()<=280){
            BarDao dao = new BarDao();
            if(dao.isBarExist(name)){
                result="rep";
            } else {
                int i=dao.createNewBar(name,cont);
                if(i!=0){
                    result="suc";
                }
            }
        }
        webPrint(result,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    private void webPrint(String result, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
