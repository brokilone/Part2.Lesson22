<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<table class="table table-hover">
   <thead>
   <tr>
      <th scope="col">login</th>
      <th scope="col">password</th>
      <th scope="col">rating</th>
   </tr>
   </thead>
   <tbody>
   <c:forEach var="user" items="${users}">
      <tr>
         <td>${user.login}</td>
         <td>${user.password}</td>
         <td>${user.rating}</td>
         <td><a href="${pageContext.request.contextPath}/showuser?login=${user.login}">Details</a></td>
      </tr>
   </c:forEach>
   </tbody>
</table>


<br>
<a href="/addUser">Add user</a><br>
<a href="/home">Main page</a> <br>
<a href="/allUsers">User's list</a>