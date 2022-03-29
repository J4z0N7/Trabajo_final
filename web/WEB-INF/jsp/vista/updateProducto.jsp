<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="cabecera.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
              <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"></link>
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
              <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0.min.js"></script>
              <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>
              
        <title>Formulario</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="row">
              
                        <h1>Actualizar el producto</h1>
                    </div>
                    <form:form commandName="producto" cssClass="navbar-form">
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>
                            <div class="form-group">
                                <form:label path="descripcion"> Nueva descripción: </form:label>
                                <form:input path="descripcion" placeholder="Digite la descripción del nuevo producto"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:label path="precio"> Ingrese el nuevo precio: </form:label>
                            <form:input path="precio" placeholder="Digite el precio"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:label path="foto_producto" cssClass="input-group-addon"> Ingrese una foto: </form:label>
                            <img style ="height: 50px; width: 50px;" src='<c:url value="${producto.foto_producto}"/>'/>
                            <form:input path="foto_producto" cssClass="form-control" type="file"></form:input>
                            </div>
                            <br>
                            <br>
                            <div class="form-group">
                            <form:button name="Enviar" class="btn btn-info">Enviar</form:button>
                            <br>
                            <br>
                            <a href="agregarProducto.htm" class="btn btn-warning"> Regresar </a>
                            <br>
                            <br>
                            <a href="listarProducto.htm" class="btn btn-info">Listar productos</a>
                            <br>
                            <br>
                            <input type="reset" value="Limpiar" class="btn btn-info">
                            <br>
                            <br>
                            <a href="deleteProducto.htm?producto=${producto.id_producto}" class=" btn btn-warning">Borrar</a>
                            </div>
                        </form:form>
                    
                </div>
            </div>
        </div>
    </body>
</html>
