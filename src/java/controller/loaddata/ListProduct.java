package controller.loaddata;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import model.Product;

public class ListProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        HttpSession session = request.getSession();
        String sortBy = request.getParameter("sortBy");

        if (sortBy != null) {
            switch (sortBy) {
                case "priceAsc":
                    productList.sort(Comparator.comparingDouble(Product::getFinalPrice));
                    break;
                case "priceDesc":
                    productList.sort(Comparator.comparingDouble(Product::getFinalPrice).reversed());
                    break;
                case "stockAsc":
                    productList.sort(Comparator.comparingInt(Product::getStock));
                    break;
                case "ratingDesc":
                    productList.sort(Comparator.comparingDouble(Product::getRating).reversed());
                    break;
            }
        }
        session.setAttribute("products", productList);
        response.sendRedirect("shopproduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
