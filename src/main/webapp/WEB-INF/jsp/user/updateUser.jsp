<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <c:set target="${user}" property="login" value="${user.login}" />
    <c:set target="${user}" property="password" value="${user.password}" />
    <c:set target="${user}" property="rating" value="${user.rating}" />



<h1>Updating user data - ${user.login}</h1>
<form method="post" action="${pageContext.request.contextPath}/updateUser?login=${user.login}" autocomplete="off">
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