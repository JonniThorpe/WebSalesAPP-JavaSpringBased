<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="com.theenglishcut.dto.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("products");

    String mensaje = (String) request.getAttribute("mensaje");

%>

<html>
<head>
    <title>Producto</title>
</head>
<body>
    <%@ include file="../bootstrap/css-js.jsp"%>
    <%@ include file = "../componentes/Navbar.jsp" %>

    <div class="p-5">
        <h1>Nuestra mejor coleccion productos</h1>
        <label>
            De esa forma, comúnmente podemos decir que un jamón 'pata negra'
            es aquel que procede de cerdos ibéricos 100% bellota, aunque es mucho
            más correcto denominarlo como jamón ibérico puro de bellota 100%
        </label>
        <%if(user == null){%>
        <div class="row">
            <div class="col">
                <img src="../../iconos/info.png" class="img-thumbnail" alt="...">
                <div>
                    <p>
                        No olvide hacer <b>Login</b> para poder realizar pedidos en nuestra web
                    </p>

                </div>
            </div>
        </div>
        <%} else if (tipo.equals("User")) {%>
        <div>
            <p>
                <br/><b>pulse el boton asignar productEntity y luego en el icono carrito podra realizar su pedido.
                !Gracias!</b>
            </p>
        </div>
        <%}%>
        <%if(tipo.equals("Admin")){%>
        <div>
            <a name="crearProducto" class="btn btn-primary" href="/CrearProducto">crear producto</a>
        </div>
        <%}%>
    </div>
    <%if(!products.isEmpty()){%>
    <div class="container text-center">
        <div class="row row-cols-xxl-4">
            <% for (Product productEntity : products) {%>
            <div class="col mt-4">
                <div class="card " style="width: 18rem;">
                    <a href="/Detail?id=<%=productEntity.getId()%>">
                        <img src="../../img/productos/<%=productEntity.getImage()%>" class="card-img-top" alt="...">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title"><%=productEntity.getName()%></h5>
                        <%--<p class="card-text"><%=productEntity.getDescripcion()%></p>--%>
                        <%if(user != null && !tipo.equals("Admin") && productEntity.getStock().getQuantity() > 0){%>
                            <a href="Basket/addProducto?id=<%=productEntity.getId()%>" class="btn btn-primary">Asignar productos al carrito</a>
                        <%}%>
                        <%
                            String disabled;
                            if(productEntity.getStock().getQuantity() > 0){
                            disabled ="";
                        %>
                        <div class="fw-bold">Cantidad disponible: <%=productEntity.getStock().getQuantity()%></div>
                        <%}else{
                            disabled = "disabled";
                        %>
                            <div class="fw-bold bg-danger-subtle rounded mb-1">Producto sin stock</div>
                        <%}%>
                        <%if(tipo.equals("Admin")){%>
                            <div>
                                <a href="/eliminarProducto?id=<%=productEntity.getId()%>">eliminar Producto</a>
                            </div>
                            <div>
                                <a href="/modificarProducto?id=<%=productEntity.getId()%>">modificar Producto</a>
                            </div>
                        <%}%>
                        <a href="/Detail?id=<%=productEntity.getId()%>" class="btn btn-primary <%=disabled%>">Ver producto</a>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
    <%}else{%>
        <h1 class="self-align-center"><%=mensaje%></h1>
    <%}%>
</body>
<footer>
    <%@ include file = "../componentes/Footer.jsp" %>
</footer>
</html>
