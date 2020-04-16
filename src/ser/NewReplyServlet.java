package ser;

import com.dao.NewTie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "NewReplyServlet", urlPatterns = {"/NewReplyServlet"})
public class NewReplyServlet extends HttpServlet {
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String result = "err";
        HttpSession session = request.getSession();
        String suid = String.valueOf(session.getAttribute("uid"));
        String susr = String.valueOf(session.getAttribute("usr"));

        String tieID = request.getParameter("tieID");
        String uid = request.getParameter("uid");
        String usr = request.getParameter("usr");
        String main = request.getParameter("main");

        System.out.println("CreateNewReply: \n\tTieId: "
                +tieID+"\n\tUserId: "+uid+
                "\n\tUserName: "+usr+
                "\n\tReplyContent: "+main);

        if(suid.equals(uid)&&susr.equals(usr)&&tieID!=null&&main!=null
        &&tieID.matches("^[0-9]+$")&&uid.matches("^[0-9]+$")
        &&main.length()>0&&main.length()<10000){

            NewTie dao = new NewTie();
            int i = dao.CreateNewReply(Integer.parseInt(tieID),main,usr,Integer.parseInt(uid));
            if(i==2){
                result = "suc";
            }
        } else {
            result = "dataErr";
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    }
}
