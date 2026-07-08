<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.theenglishcut.dto.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer quantity = (Integer) request.getAttribute("quantity");
%>
<html>
<head>
    <title>CREAR PRODUCTO</title>
    <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
</head>
<body>
<%@ include file="../bootstrap/css-js.jsp"%>
<%@ include file = "../componentes/Navbar.jsp" %>
<h1 align="center">CREAR PRODUCTO</h1>
<div class="container-sm">
    <form:form action="/guardarProducto" method="post" modelAttribute="product">
        <form:hidden path="id"/>
        <div class="mb-3">
            <label>Nombre del Producto</label>
            <form:input path="name" class="col-md-8 form-control input-md"/>
        </div>
        <div class="mb-3">
            <label for="description">Descripción del Producto</label>
            <form:textarea id="description" path="description" class="form-control" rows="3"></form:textarea>
        </div>
        <div>
            <label for="categoriesID">Categorías Disponibles</label>
            <form:select id="categoriesID" path="categories" items="${categories}" itemLabel="name" itemValue="id" multiple="true"/>
        </div>
        <div class="mb-3">
            <label>Precio del Producto</label>
            <form:input path="price" class="col-md-8 form-control input-md"/>
        </div>
        <form:hidden path="stock.id"/>
        <div class="form-group mb-3">
            <label for="cantidadProducto">Cantidad</label>
            <select name="quantity" class="form-control" id="cantidadProducto">
                    <%-- TODO set selected cantidad --%>
                <%
                    for (int i = 0; i < 100; i++) {
                        String selected = (i == quantity) ? "selected" : "";
                %>
                <option value="<%=i%>" <%=selected%>><%=i%></option>
                <%
                    }
                %>
            </select>
        </div>
        <div class="form-group mb-3">
            <label>Imagen</label>
            <form:input path="image" type="text" class="form-control"/>
        </div>
        <div class="input-group flex-nowrap mb-3">
            <button type="submit" class="btn btn-primary">Enviar</button>
        </div>
        <a href="/" class="btn btn-primary">Volver</a>
    </form:form>
</div>

<script>
    CKEDITOR.replace('description');
</script>

</body>
</html>
<%@ include file = "../componentes/Footer.jsp" %>
