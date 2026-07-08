<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="com.theenglishcut.dto.Product" %><%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 02/07/2024
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product product = (Product) request.getAttribute("product");
    List<Product> productsByCategory = (List<Product>) request.getAttribute("products");
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ProductDetailStyle.css">
</head>
<body>
<%@ include file="../bootstrap/css-js.jsp"%>
<%@ include file = "../componentes/Navbar.jsp" %>
<div class="container-fluid h-100 d-flex  justify-content-center">
    <div class="row border border-4 w-100">
        <div  class="col border border-1 border-danger">
            anuncio
        </div>
        <div  class="col-8">
            <div class="object-wrapper mb-3">
                <div class="row object-div">
                    <div class="col object">
                        <div class=" text-white">
                            <img src="../../img/productos/<%=product.getImage()%>" class="" alt="...">
                        </div>
                    </div>
                    <div class="col">
                        <div class="row">
                            <h1> <%=product.getName()%></h1>
                            <p class="fs-3 fw-semibold">Precio: <%=product.getPrice()%></p>
                        </div>
                        <div class="row">
                            <h3> Descripcion</h3>
                            <p>
                                <%=product.getDescription()%>
                            </p>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="mb-2">
                                <span for="selectQuantity">Selecciona una cantidad</span>
                                <select id="selectQuantity">
                                    <option value="0">0</option>
                                    <%
                                        for (int i = 0; i < product.getStock().getQuantity(); i++) {

                                    %>
                                    <option value="<%=i%>"><%=i%></option>
                                    <%}%>
                                </select>

                                <div id="liveAlertPlaceholder"></div>
                                <a href="Basket/addProducto?id=<%=product.getId()%>" class="btn btn-primary">
                                    Asignar productos al carrito
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="productCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <%
                        // Iteración principal: recorre la lista de productos de tres en tres
                        for (int i = 0; i < productsByCategory.size(); i += 3) {
                    %>
                        <div class="carousel-item <%= i == 0 ? "active" : "" %>">
                            <div class="grid-container">
                            <%
                                // Iteración secundaria: añade hasta tres productos a cada 'carousel-item'
                                for (int j = i; j < i + 3 && j < productsByCategory.size(); j++) {
                            %>

                                <div class="element-grid card-container">
                                    <div class="card">
                                        <a href="/Detail?id=<%= productsByCategory.get(j).getId() %>">
                                            <img src="../../img/productos/<%= productsByCategory.get(j).getImage() %>" class="card-img-top fixed-img" alt="...">
                                        </a>
                                        <div class="card-body">
                                            <h5 class="card-title"><%= productsByCategory.get(j).getName() %></h5>
                                            <a href="/Detail?id=<%=productsByCategory.get(j).getId()%>" class="btn btn-primary">Ver producto</a>
                                        </div>
                                    </div>
                                </div>

                            <%
                                } // Fin de la iteración secundaria
                            %>
                            </div>
                        </div>
                    <%
                        } // Fin de la iteración principal
                    %>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

        </div>
        <div  class="col border border-1 border-danger">
            anuncio
        </div>
    </div>
</div>
<%@ include file = "../componentes/Footer.jsp" %>
<script src="../data/js/ProductDetail_.js"></script>
</body>
</html>
