
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%!
    public String createMessage (String arg) {
        if (arg == null || arg.isEmpty()) return "";
        int fst = arg.indexOf("=");
        int sec = arg.lastIndexOf("=");
        if (sec != fst) {
            return arg.substring(fst+1, arg.indexOf(',')) + "\n"
                    + arg.substring(sec+1, arg.length()-1);
        }
        return arg.substring(arg.indexOf("=")+1, arg.length()-1);
    }
%>
<h3>Login Page</h3>
<p><%= createMessage(request.getAttribute("attributes").toString())%></p>
<form method="POST" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <label for="login">Login</label>
            <input name="login" type="text" class="form-control" id="login" value="${user.login}">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input name="password" type="password" class="form-control" id="password" value="${user.password}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>

</form>
