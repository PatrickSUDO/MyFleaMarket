package com.myfleamarket.store.web;

import com.myfleamarket.store.domain.Customer;
import sun.misc.Request;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter")
public class LoginCheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Customer customer = (Customer) request.getSession().getAttribute("customer");

        String action = req.getParameter("action");
        //Haven't login
        if(customer == null && !"login".equals(action) && !"reg_init".equals(action)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
