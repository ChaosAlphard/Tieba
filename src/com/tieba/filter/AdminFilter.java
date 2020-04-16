package com.tieba.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/AdminPage/userManage.jsp", "/AdminPage/barManage,jsp"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) {}
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        String lv = (String)session.getAttribute("adminLV");
        System.out.println("com.tieba.filter.AdminFilter[log]: adminLV: "+lv);
        if(lv!=null&&lv.equals("3")){
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("/AdminPage/login.jsp");
        }
    }
}
