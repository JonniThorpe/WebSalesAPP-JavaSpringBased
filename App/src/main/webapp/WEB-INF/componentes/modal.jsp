<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 15/07/2024
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modal</title>
</head>
<body>
<form:form action="modifyBasketProduct" method="post" modelAttribute="productQuantity">
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h2 id="product-name" class="fs-5">Popover in a modal</h2>
                    <p>Precio: <span id="product-price"></span></p>
                    <img id="product-img" src="" alt="Imagen del Producto" width="100" height="100">
                    <form:select path="quantity" id="unidades">
                        <form:options items="${quantityOptions}" />
                    </form:select>
                    <form:hidden path="productId" id="productId"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</form:form>
</body>
</html>
