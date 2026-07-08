<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%@ page import="java.util.*" %>
<%@ page import="com.theenglishcut.entity.ProductEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Jonni
  Date: 21/05/2024
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../bootstrap/css-js.jsp"%>
<%@ include file = "../componentes/Navbar.jsp" %>

<form method="post" action="confirmarPedido">
    <div class="container-fluid">
        <table class="table">
            <tr>
                <th></th>
                <th class="col">Nombre</th>
                <th class="col">Precio/Unidad</th>
                <th class="col">Número Unidades</th>
                <th class="col">Precio Total</th>
            </tr>
            <%

                double total = 0;
                int num_productos;
                for(Map.Entry<Product, Integer> mapaProducto: basketProducts.entrySet()){
                    Product product = mapaProducto.getKey();
                    num_productos= mapaProducto.getValue();

                    double precio = product.getPrice();
                    double precio_total=precio * num_productos;
            %>
            <tr>
                <td><img src="../../img/productos/<%=product.getImage()%>" width="64" height="64"></td>
                <td><%=product.getName()%></td>
                <td><%=product.getPrice()%>€</td>
                <td>
                    <select name="cantidad" id="unidades">
                        <%
                            for (int i = 0; i <= 64; i++) {
                                String selected = "";
                                if(i == num_productos){
                                    selected = "selected";
                                }
                        %>
                        <option value="<%=i%>" <%=selected%> ><%=i%></option>
                        <%
                            }%>
                    </select>
                    <label for="unidades">uds</label>
                    <a href="/Basket/deleteProduct?idProduct=<%=product.getId()%>"><img src="../../iconos/bin.png" width="32" height="32"></a>
                </td>
                <td><%=precio_total%></td>
                <td>

                    <a type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal"
                       data-product-id="<%=product.getId()%>"
                       data-product-name="<%=product.getName()%>"
                       data-product-price="<%=product.getPrice()%>"
                       data-product-quantity="<%=num_productos%>"
                       data-product-img="<%=product.getImage()%>">
                        Modificar Pedido
                    </a>
                </td>
            </tr>
            <%total = total+precio_total;}%>
            <tr>
                <td colspan="2">Total</td>
                <td ><%=total%>€</td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary">Realizar orderEntity</button>
    </div>
</form>
<%@ include file="../componentes/modal.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Botón que disparó el modal
        var productId = button.data('product-id');
        var productName = button.data('product-name');
        var productPrice = button.data('product-price');
        var productQuantity = button.data('product-quantity');
        var productImg = button.data('product-img'); // Obtener el valor de data-product-img

        // Actualizar el contenido del modal
        var modal = $(this);
        modal.find('.modal-title').text('Modificar Pedido para ' + productName);
        modal.find('#productId').val(productId);
        modal.find('#product-name').text(productName);
        modal.find('#product-price').text(productPrice + '€');
        modal.find('#product-img').attr('src', '../../img/productos/' + productImg); // Actualizar la imagen

        modal.find('#unidades').val(productQuantity);
    });
</script>
</body>
<footer>
    <%@ include file = "../componentes/Footer.jsp" %>
</footer>
</html>
