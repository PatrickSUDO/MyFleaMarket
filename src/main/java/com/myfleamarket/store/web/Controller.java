package com.myfleamarket.store.web;

import com.myfleamarket.store.domain.Customer;
import com.myfleamarket.store.domain.Goods;
import com.myfleamarket.store.service.CustomerService;
import com.myfleamarket.store.service.GoodsService;
import com.myfleamarket.store.service.OrdersService;
import com.myfleamarket.store.service.ServiceException;
import com.myfleamarket.store.service.imp.CustomerServiceImp;
import com.myfleamarket.store.service.imp.GoodsServiceImp;
import com.myfleamarket.store.service.imp.OrdersServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Detail;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
    private GoodsService goodsService = new GoodsServiceImp();
    private OrdersService ordersService = new OrdersServiceImp();

    private int totalPageNumber = 0; // Total pages
    private int pageSize = 10; // Lines per page
    private int currentPage = 1; // current page number

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("reg".equals(action)) {
            String userid = request.getParameter("userid");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String birthday = request.getParameter("birthday");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            List<String> errors = new ArrayList<>();

            if (userid == null || userid.equals("")) {
                errors.add("User ID cannot be empty！");
            }
            if (name == null || name.equals("")) {
                errors.add("User Name cannot be empty！");
            }
            if (password == null || password2 == null || !password.equals(password2)) {
                errors.add("Passwords are inconsistent！");
            }

            String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";

            if (!Pattern.matches(pattern, birthday)) {
                errors.add("Invalid date format~");
            }

            //User Validation
            if (errors.size() > 0) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
            } else {
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setName(name);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setPhone(phone);
                try {
                    Date d = dateFormat.parse(birthday);
                    customer.setBirthday(d);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Registeration
                try {
                    customerService.register(customer);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (ServiceException e) {
                    errors.add("The user has already registered");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
                }
            }
        } else if ("login".equals(action)) {
            //USer login
            String userid = request.getParameter("userid");
            String password = request.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userid);
            customer.setPassword(password);

            //login successfully
            if (customerService.login(customer)) {
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
                List<String> errors = new ArrayList<>();
                errors.add("Incorrect ID or password! Login Fail!");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if ("list".equals(action)) {
            // Goods List
            List<Goods> goodsList = goodsService.queryAll();

            if (goodsList.size() % pageSize == 0) {
                totalPageNumber = goodsList.size() / pageSize;
            } else {
                totalPageNumber = goodsList.size() / pageSize + 1;
            }

            request.setAttribute("totalPageNumber", totalPageNumber);
            request.setAttribute("currentPage", currentPage);

            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;
            // if last page
            if (currentPage == totalPageNumber) {
                end = goodsList.size();
            }
            request.setAttribute("goodsList", goodsList.subList(start, end));
            request.getRequestDispatcher("goods_list.jsp").forward(request, response);

        } else if ("paging".equals(action)) {
            String page = request.getParameter("page");

            if (page.equals("prev")) {
                currentPage--;
                if (currentPage < 1) {
                    currentPage = 1;
                }
            } else if (page.equals("next")) {
                currentPage++;
                if (currentPage > totalPageNumber) {
                    currentPage = totalPageNumber;
                }
            } else {
                currentPage = Integer.valueOf(page);
            }

            int start = (currentPage - 1) * pageSize;
            int end = currentPage * pageSize;

            List<Goods> goodsList = goodsService.queryByStartEnd(start, end);

            request.setAttribute("totalPageNumber", totalPageNumber);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("goodsList", goodsList);
            request.getRequestDispatcher("goods_list.jsp").forward(request, response);

        } else if ("detail".equals(action)) {
            String goodsId = request.getParameter("id");
            Goods goods = goodsService.goodDetail(new Long(goodsId));

            request.setAttribute("goods", goods);
            request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
        } else if ("add".equals(action)) {
            //Add into cart
            Long goodsId = new Long(request.getParameter("id"));
            String goodsName = request.getParameter("name");
            Float price = new Float(request.getParameter("price"));

            //Get goods from session
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");


            if (cart == null) {
                cart = new ArrayList<Map<String, Object>>();
                request.getSession().setAttribute("cart", cart);
            }

            //Item chosen in the cart
            int flag = 0;
            for (Map<String, Object> item : cart) {
                Long goodsId2 = (Long) item.get("goodsid");
                if (goodsId.equals(goodsId2)) {
                    Integer quantity = (Integer) item.get("quantity");
                    quantity++;
                    item.put("quantity", quantity);
                    flag++;
                }
            }

            //No item chosen in the cart
            if (flag == 0) {
                Map<String, Object> item = new HashMap<>();
                item.put("goodsid", goodsId);
                item.put("goodsname", goodsName);
                item.put("quantity", 1);
                item.put("price", price);
                cart.add(item);
            }

            System.out.println(cart);

            String pagename = request.getParameter("pagename");

            if (pagename.equals("list")) {
                int start = (currentPage - 1) * pageSize;
                int end = currentPage * pageSize;

                List<Goods> goodsList = goodsService.queryByStartEnd(start, end);

                request.setAttribute("totalPageNumber", totalPageNumber);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("goodsList", goodsList);
                request.getRequestDispatcher("goods_list.jsp").forward(request, response);

            } else if (pagename.equals("detail")) {

                Goods goods = goodsService.goodDetail(new Long(goodsId));
                request.setAttribute("goods", goods);
                request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
            }
        } else if ("cart".equals(action)) {
            //---------查看购物车---------
            // 从Session中取出的购物车
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

            double total = 0.0;

            if (cart != null) {
                for (Map<String, Object> item : cart) {

                    Integer quantity = (Integer) item.get("quantity");
                    Float price = (Float) item.get("price");
                    double subtotal = price * quantity;
                    total += subtotal;
                }
            }

            request.setAttribute("total", total);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else if ("sub_ord".equals(action)) {
            //submit order
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                Long goodsId = (Long) item.get("goodsid");
                String strquantity = request.getParameter("quantity_" + goodsId);
                int quantity = 0;
                try {
                    quantity = new Integer(strquantity);
                } catch (Exception e) {
                }
                item.put("quantity", quantity);
            }

            //Submit Order
            String orderId = ordersService.submitOrders(cart);
            request.setAttribute("orderid", orderId);
            request.getRequestDispatcher("order_finish.jsp").forward(request, response);

            //Empty the cart in the session
            request.getSession().removeAttribute("cart");
        } else if ("main".equals(action)) {
            //Back to home
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else if ("logout".equals(action)){
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("customer");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if ("reg_init".equals(action)) {
            // User enter from registration
            request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
        }
    }
}
