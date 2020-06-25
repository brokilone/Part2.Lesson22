<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <c:set target="${mobile}" property="id" value="${mobile.id}" />
    <c:set target="${mobile}" property="model" value="${mobile.model}" />
    <c:set target="${mobile}" property="price" value="${mobile.price}" />
    <c:set target="${mobile}" property="manufacturer" value="${mobile.manufacturer}" />


<h1>Updating mobile data</h1>
<form method="post" action="${pageContext.request.contextPath}/updateMobile?id=${mobile.id}" autocomplete="off">
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