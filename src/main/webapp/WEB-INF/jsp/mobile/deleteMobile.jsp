<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Are you sure you want to delete?</h2>
<ul class="list-group">
   <li class="list-group-item">${mobile.id}</li>
   <li class="list-group-item">${mobile.model}</li>
   <li class="list-group-item">${mobile.price}</li>
   <li class="list-group-item">${mobile.manufacturer}</li>
</ul>
<form method="POST" action="${pageContext.request.contextPath}/deleteMobile?id=${mobile.id}">
   <input type="submit" value="Delete mobile">
</form>
<br>
<a href="/home">Main page</a>
<br>
<a href="/allmobiles">Back to mobile's list</a>
