<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="cabecera.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
        <link href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
              
           
        <title>Listado de Datos</title>
    </head>
    <body>
        <h1>Información recogida</h1>
        <table class="table table-bordered table-striped table-hover"
               <thead>
            
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Correo</th>
                </thead>
                <tbody>
                
                    <tr>
                        <td><c:out value="${nombre}"/></td>
                        <td><c:out value="${apellido}"/></td>
                        <td><c:out value="${edad} años"/></td>
                        <td><c:out value="${correo}"/></td>
                    </tr>
                
                </tbody>
             
        </table> 
            <ul>
            <a href="formJstl.htm" class="btn btn-success"> Regresar </a>
            </ul>        
    </body>
</html>
