<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h2>Are you sure you want to delete?</h2>
<ul class="list-group">
   <li class="list-group-item">${user.login}</li>
   <li class="list-group-item">${user.password}</li>
   <li class="list-group-item">${user.rating}</li>
</ul>
<form method="POST" action="${pageContext.request.contextPath}/deleteUser?login=${user.login}">
   <input type="submit" value="Delete user">
</form>
<br>
<a href="/home">Main page</a>
<br>
<a href="/allUsers">Back to user's list</a>
