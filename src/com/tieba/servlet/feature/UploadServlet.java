package com.tieba.servlet.feature;

import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        /* 获取项目路径 */
        /*!因为服务器部署策略不同,Eclipse和IDEA可能返回不同结果!*/
        /*   IDEA返回([Workspace]\项目名\out\artifacts\项目名_war_exploded\)*/
        /*Eclipse返回([Workspace]\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\项目名\)*/
        String path=request.getSession().getServletContext().getRealPath("/");

        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder uidtmp=new StringBuilder();
        StringBuilder imgtmp=new StringBuilder();
        String s;
        while((s=br.readLine())!=null){
            if(s.contains("data:image/jpeg;base64,")){
                imgtmp.append(s);
            } else {
                uidtmp.append(s);
            }
        }

        String imgt=String.valueOf(imgtmp);
        String img=imgt.substring("data:image/jpeg;base64,".length());

        String uidt=String.valueOf(uidtmp);
        final String mark="name=\"uid\"";
        int begin=uidt.indexOf(mark)+mark.length();
        String imgtm=uidt.substring(begin);
        int end = imgtm.indexOf("-");
        String uid=imgtm.substring(0,end);

        /* 生成图像文件 */
        BASE64Decoder decoder = new BASE64Decoder();
        String uploadFlag;
        try {
            byte[] b = decoder.decodeBuffer(img);
            for(int i=0;i<b.length;i++){
                if(b[i]<0){
                    b[i]+=256;
                }
            }
            String imgFile=path+ "/img/avatar/" +uid+".jpg";
            System.out.println("保存至"+imgFile);
            OutputStream outs = new FileOutputStream(imgFile);
            outs.write(b);
            outs.flush();
            outs.close();
            uploadFlag="suc";
        } catch (IOException e) {
            uploadFlag="err";
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(uploadFlag);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
    }
}
