<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Mobile info</h2>
<ul class="list-group list-group-flush">
   <li class="list-group-item">${mobile.id}</li>
   <li class="list-group-item">${mobile.model}</li>
   <li class="list-group-item">${mobile.price}</li>
   <li class="list-group-item">${mobile.manufacturer}</li>
</ul>
<a href="/deleteMobile?id=${mobile.id}">Delete mobile</a>
<br>
<a href="/updateMobile?id=${mobile.id}">Update mobile</a>
<br>
<a href="/home">Main page</a>
<br>
<a href="/allmobiles">Back to mobile's list</a>
