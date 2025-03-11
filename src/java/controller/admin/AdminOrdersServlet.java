package controller.admin;

import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import model.Order;
import model.Account;

public class AdminOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        if (account == null || account.getRole() != 0) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        String sort = request.getParameter("sort");

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllOrders(sort);

        if (orders == null) {
            orders = Collections.emptyList();
        }

        session.setAttribute("orders", orders);
        response.sendRedirect("adminpage/adminorders.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
