<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecera.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
              <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"></link>
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
              <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0.min.js"></script>
              <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
              
        <title>Listado</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="row">
              
                        <h1>Listado de Ventas</h1>
                   </div>
                    <form:form commandName="persona" cssClass="navbar-form" method="post">
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                            <div class="form-group">
                            <form:label path="Ctd_Producto" cssClass="input-group-addon"> Cantidad Ventas: </form:label>
                            <form:input path="Ctd_Producto" cssClass="form-control"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:label path="Nombre_Producto"> Nombre del Producto: </form:label>
                            <form:input path="Nombre_Producto" placeholder="Ingrese el nombre del producto nuevo"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:select path="Nombre_Producto" cssClass="form-control"> 
                                <c:forEach var="dato" items="$('')">
                                    <option value="%(dato.id)">$(dato.nombre)</option>
                                </c:forEach>
                            </form:select>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:label path="Fecha_Venta" cssClass="input-group-addon"> Fecha Venta: </form:label>
                            <form:input path="Fecha_Venta" cssClass="form-control"></form:input>
                            </div>
                            <div class="form-group">
                            <form:label path="Nom_Cliente"> Nombre del Cliente: </form:label>
                            <form:input path="Nom_Cliente" placeholder="Ingrese el nombre del producto nuevo"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:select path="Nom_Cliente" cssClass="form-control"> 
                                <c:forEach var="datos" items="$('')">
                                    <option value="%(datos.id)">$(datos.nombre)</option>
                                </c:forEach>
                            </form:select>
                            </div>
                            <br>
                            <br>
                            <form:button name="Enviar" class="btn btn-info">Enviar</form:button>
                            <br>
                            <br>
                            <a href="index.htm" class="btn btn-warning"> Regresar </a>
                            <br>
                            <br>
                            <a href="listarJstl.htm" class="btn btn-info">Listar usuarios</a>
                            <br>
                            <br>
                            <a href="agregarCliente.htm" class="btn btn-info">Agregar usuarios</a>
                            

                        </form:form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
