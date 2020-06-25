<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="task22.model.User" />
    <c:set target="${user}" property="login" value="newuser" />
    <c:set target="${user}" property="password" value="" />
    <c:set target="${user}" property="rating" value="0" />


<h1>Adding a new user</h1>
<form method="post" action="${pageContext.request.contextPath}/addUser" autocomplete="off">
    <div class="form-group">
        <label for="login">Login</label>
        <input name="login" type="text" class="form-control" id="login" value="${user.login}">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="password" class="form-control" id="password" value="${user.password}">
    </div>
    <div class="form-group">
        <label for="rating">Rating</label>
        <input name="rating" type="number" class="form-control" id="rating" value="${user.rating}">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<br>
<a href="/home">Main page</a>
