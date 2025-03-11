<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>YOUR BUDDY - Trang chủ</title>
        <link rel="stylesheet" href="css/home.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Nunito:ital,wght@0,200..1000;1,200..1000&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    </head>
    <body>
        <header>
            <div class="container">
                <div class="header-top">
                    <nav class="top-nav">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/HomeProduct">Trang chủ</a></li>
                            <li><a href="mailto:info@yourbuddy.com">Liên hệ</a></li>
                        </ul>
                    </nav>
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}/HomeProduct"><img src="css/images/2.png" alt="Logo"></a>
                    </div>
                    <nav class="top-nav right-nav">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/ListProduct">Sản phẩm</a></li>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.acc}">
                                    <li><a href="cartpage.jsp"><i class="fas fa-shopping-bag"></i></a></li>
                                    <li><a href="userpage.jsp"><i class="far fa-user"></i></a></li>
                                    <li><a href="${pageContext.request.contextPath}/signout">Đăng xuất</a></li>
                                    </c:when>
                                    <c:otherwise>
                                    <li><a href="signin.jsp">Đăng nhập</a></li>
                                    <li><a href="signup.jsp">Đăng ký</a></li>
                                    </c:otherwise>
                                </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

        <section class="heroimagehome hero">
            <div class="container">
                <div class="hero-content">
                    <p>Your Buddy – Nơi mang đến những sản phẩm chất lượng dành cho thú cưng, để mỗi ngày bên nhau trở nên trọn vẹn hơn!</p>
                    <a href="${pageContext.request.contextPath}/ListProduct" class="btn btn-primary">Tìm kiếm thêm</a>
                </div>
            </div>
        </section>

        <section class="smooth-foam-section">
            <div class="container">
                <div class="foam-image">
                    <img src="css/images/homeimage.png" alt="Home Image">
                </div>
                <div class="foam-content">
                    <h2>Mục tiêu YOUR BUDDY</h2>
                    <p>Chúng tôi cung cấp đa dạng các loại thức ăn, phụ kiện và đồ chơi, giúp thú cưng của bạn luôn khỏe mạnh và hạnh phúc. Đồng hành cùng Your Buddy, bạn không chỉ chăm sóc thú cưng tốt hơn mà còn tạo nên những khoảnh khắc đáng nhớ bên người bạn nhỏ đáng yêu của mình! 🐶🐱✨</p>
                    <div class="foam-actions">
                        <a href="${pageContext.request.contextPath}/ListProduct" class="view-ingredients">Xem sản phẩm ></a>
                    </div>
                </div>
            </div>
        </section>

        <section class="popular-products">
            <div class="container">
                <center><h2>SẢN PHẨM MỚI</h2></center>
                <div class="products-slider">
                    <c:forEach var="p" items="${products}" varStatus="loop" begin="${fn:length(products) - 3}" end="${fn:length(products) - 1}">

                        <div class="product-card">
                            <a style="text-decoration: none;" href="ProductDetail?productID=${p.productID}" class="recipe-card-link">
                                <img src="${p.imageURL}" alt="${p.productName}">
                                <h3>${p.productName}</h3>
                                <p class="price">
                                    <strong>
                                        <fmt:formatNumber type="currency" currencySymbol="₫" value="${p.price}" maxFractionDigits="0" />
                                    </strong>
                                </p>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>

        <section class="ice-cream-lovers">
            <div class="container">
                <center><h2>MỘT SỐ ĐÁNH GIÁ TỪ NGƯỜI MUA</h2></center>
                <div class="testimonials-grid">
                    <div class="testimonial-card">
                        <img src="https://i.pinimg.com/736x/1b/e1/f8/1be1f863f7174f9feb4b7c0264bfd274.jpg" alt="cmt">
                        <p class="quote">Mèo nhà mình rất thích loại pate này, ăn hết sạch mỗi lần cho ăn. Trước đây nó khá kén ăn, nhưng giờ thì không còn lo nữa!</p>
                        <p class="author">Nguyễn Duy Khang</p>
                        <div class="stars"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></div>
                    </div>
                    <div class="testimonial-card">
                        <img src="https://i.pinimg.com/736x/ef/c9/1e/efc91ed4b0c3d0c7f432c717b755a650.jpg" alt="cmt">
                        <p class="quote">Thức ăn cho cún ở đây rất tốt, cún nhà tôi ăn rất ngon miệng và không còn kén ăn như trước. Lông cũng mượt hơn hẳn!</p>
                        <p class="author">Dương Linh</p>
                        <div class="stars"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></div>
                    </div>
                    <div class="testimonial-card">
                        <img src="https://i.pinimg.com/736x/b1/d9/da/b1d9da92e9d02c07875cb61d4205f76b.jpg" alt="cmt">
                        <p class="quote">Mình đã thử nhiều loại thức ăn khô cho cún nhưng đây là loại tốt nhất. Cún của mình rất thích và không gặp vấn đề tiêu hóa.</p>
                        <p class="author">Lê Phan</p>
                        <div class="stars"><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i></div>
                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp" />
        <script src="js/home.js"></script>
    </body>
</html>