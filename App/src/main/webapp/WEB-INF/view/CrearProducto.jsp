<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.theenglishcut.dto.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer quantity = (Integer) request.getAttribute("quantity");
%>
<html>
<head>
    <title></title>

</head>
<body>
<%@ include file = "../componentes/Navbar.jsp" %>
<h1 align="center">CREAR PRODUCTO</h1>
<div class="container-sm">
    <form:form action="/guardarProducto" method="post" modelAttribute="product">
        <form:hidden path="id"/>
        <div class="mb-3">
            <label >Nombre del productEntity</label>
            <from:input path="name" class="col-md-8 form-control input-md"/>
        </div>
        <div class="mb-3">
            <label for="description">Descripcion del productEntity</label>
            <form:textarea id="description" path="description" class="form-control" rows="3"></form:textarea>
        </div>
        <div>
            <label for="categoriesID">Categorias Disponibles</label>
            <form:select id="categoriesID" path="categories" items="${categories}" itemLabel="name" itemValue="id" multiple="true"/>
        </div>
        <div class="mb-3">
            <label>Precio del producto</label>
            <form:input path="price" class="col-md-8 form-control input-md" />
        </div>
        <form:hidden path="stock.id"/>
        <div class="form-group mb-3">
            <label for="cantidadProducto">Cantidad</label>
            <select name="quantity" class="form-control" id="cantidadProducto">
                <%--TODO set selected cantidad--%>
                <%
                for (int i = 0; i < 100; i++) {
                String selected = "";
                if(i==quantity){
                    selected = "selected";
                }
                    %>
                <option value="<%=i%>" <%=selected%>><%=i%></option>
                         <%
                    }%>
            </select>
        </div>
        <div class="form-group mb-3">
            <label >Imagen</label>
            <form:input path="image" type="text" class="form-control" />
        </div>
        <div class="input-group flex-nowrap mb-3">
            <button type="submit" class="btn btn-primary" >Enviar</button>
        </div>
        <a href="/" class="btn btn-primary ">Volver</a>
    </form:form>
</div>
</body>
</html>
