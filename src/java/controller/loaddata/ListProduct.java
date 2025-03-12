package controller.loaddata;

import dal.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;
import model.Product;

public class ListProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();
        List<Product> filteredProducts = new ArrayList<>(productList); 

        HttpSession session = request.getSession();
        String sortBy = request.getParameter("sortBy");
        String[] categoryParams = request.getParameterValues("category"); 
        String[] petParams = request.getParameterValues("pet"); 
        String[] priceParams = request.getParameterValues("price"); 
        String searchName = request.getParameter("searchName"); 

        if (sortBy != null) {
            switch (sortBy) {
                case "priceAsc":
                    filteredProducts.sort(Comparator.comparingDouble(Product::getFinalPrice));
                    break;
                case "priceDesc":
                    filteredProducts.sort(Comparator.comparingDouble(Product::getFinalPrice).reversed());
                    break;
                case "stockAsc":
                    filteredProducts.sort(Comparator.comparingInt(Product::getStock));
                    break;
                case "ratingDesc":
                    filteredProducts.sort(Comparator.comparingDouble(Product::getRating).reversed());
                    break;
            }
        }

        if (categoryParams != null) {
            List<Product> tempProducts = new ArrayList<>(filteredProducts);
            filteredProducts.clear();
            for (Product p : tempProducts) {
                for (String catId : categoryParams) {
                    if (String.valueOf(p.getCateID()).equals(catId)) {
                        filteredProducts.add(p);
                        break; 
                    }
                }
            }
        }

        if (petParams != null) {
            List<Product> tempProducts = new ArrayList<>(filteredProducts);
            filteredProducts.clear();
            for (Product p : tempProducts) {
                for (String petId : petParams) {
                    if (String.valueOf(p.getPetID()).equals(petId)) {
                        filteredProducts.add(p);
                        break;
                    }
                }
            }
        }

        if (priceParams != null) {
            List<Product> tempProducts = new ArrayList<>(filteredProducts);
            filteredProducts.clear();
            for (Product p : tempProducts) {
                double finalPrice = p.getFinalPrice();
                for (String priceRange : priceParams) {
                    if (priceRange.equals("0-100000") && finalPrice >= 0 && finalPrice <= 100000) {
                        filteredProducts.add(p);
                        break;
                    } else if (priceRange.equals("100000-200000") && finalPrice > 100000 && finalPrice <= 200000) {
                        filteredProducts.add(p);
                        break;
                    } else if (priceRange.equals("200000-500000") && finalPrice > 200000 && finalPrice <= 500000) {
                        filteredProducts.add(p);
                        break;
                    } else if (priceRange.equals("500000+") && finalPrice > 500000) {
                        filteredProducts.add(p);
                        break;
                    }
                }
            }
        }

        if (searchName != null && !searchName.trim().isEmpty()) {
            List<Product> tempProducts = new ArrayList<>(filteredProducts);
            filteredProducts.clear();
            String lowerSearchName = searchName.trim().toLowerCase();
            for (Product p : tempProducts) {
                if (p.getProductName().toLowerCase().contains(lowerSearchName)) {
                    filteredProducts.add(p);
                }
            }
        }


        session.setAttribute("products", filteredProducts);
        response.sendRedirect("shopproduct.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}