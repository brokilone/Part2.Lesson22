<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Error details</h2>
<h3>${statusCode}</h3>

<ul class="list-group">
    <li class="list-group-item-danger">Requested URI: ${requestUri} </li>
    <li class="list-group-item-danger">Servlet name: ${servletName}</li>
    <li class="list-group-item-danger">Message: ${message}</li>

</ul>
<br>
<a href="/home">Main page</a>
