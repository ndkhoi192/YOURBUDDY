package controller.admin;

import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Category;
import model.Product;

public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAllCates();
        session.setAttribute("categories", categories);

        response.sendRedirect("adminpage/addproduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            String productName = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int cateID = Integer.parseInt(request.getParameter("category"));
            int petID = Integer.parseInt(request.getParameter("pet"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String imageURL = request.getParameter("imageurl");

            Product newProduct = new Product(0, productName, price, description, cateID, petID, imageURL, discount, stock);

            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(newProduct);

            session.setAttribute("successMessage", "Thêm sản phẩm thành công!");
            response.sendRedirect("LoadDataProduct");

        } catch (NumberFormatException e) {
            HttpSession session = request.getSession();
            session.setAttribute("errorap", "Dữ liệu không hợp lệ!");
            response.sendRedirect("adminpage/addproduct.jsp");
        }
    }
}
