<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<h2>User info</h2>
<ul class="list-group list-group-flush">
   <li class="list-group-item">${user.login}</li>
   <li class="list-group-item">${user.password}</li>
   <li class="list-group-item">${user.rating}</li>
</ul>

<a href="/deleteUser?login=${user.login}">Delete user</a>
<br>
<a href="/updateUser?login=${user.login}">Update user</a>
<br>
<a href="/home">Main page</a>
<br>
<a href="/allUsers">Back to user's list</a>
