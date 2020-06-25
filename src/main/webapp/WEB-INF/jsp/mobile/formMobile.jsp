<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="mobile" class="task22.model.Mobile" />
    <c:set target="${mobile}" property="model" value="model" />
    <c:set target="${mobile}" property="price" value="0" />
    <c:set target="${mobile}" property="manufacturer" value="some_factory" />


<h1>Adding a new mobile</h1>
<form method="post" action="${pageContext.request.contextPath}/addmobile" autocomplete="off">
    <div class="form-group">
        <label for="model">Model</label>
        <input name="model" type="text" class="form-control" id="model" value="${mobile.model}">
    </div>
    <div class="form-group">
        <label for="price">Price</label>
        <input name="price" type="text" class="form-control" id="price" value="${mobile.price}">
    </div>
    <div class="form-group">
        <label for="manufacturer">Manufacturer</label>
        <input name="manufacturer" type="text" class="form-control" id="manufacturer" value="${mobile.manufacturer}">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<br>
<a href="/home">Main page</a>
