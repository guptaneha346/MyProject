<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Form</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
    <link href="src/main/webapp/WEB-INF/jsp/css/login.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <spring:url value="/user/Login/" var="addURL"/>

    <%--@elvariable id="loginForm" type="antlr"--%>
    <form:form modelAttribute="loginForm" method="post" action="${saveURL }" cssClass="form">
        <form:hidden path="id"/>
        <div class="row justify-content-sm-center">
            <div class="container pt-3">
                <div class="row justify-content-sm-center">
                    <div class="col-sm-6 col-md-4">

                        <br><br>
                        <br><br><br><br>

                        <div class="card-body">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;<img
                                src="https://media.licdn.com/dms/image/C4E0BAQErzHJr7lA-uQ/company-logo_200_200/0?e=2159024400&v=beta&t=SNMUVGNKZompTKxWPdbrZt-Z0lleQ6y4nJ2MqfRs7jI">

                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="username" type="text" class="form-control" name="username"
                                       placeholder="UserName">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="password" type="password" class="form-control" name="password"
                                       placeholder="Password">
                            </div>
                            <br>
                            <button class="btn btn-lg btn-primary btn-block mb-1" type="submit">Login</button>
                            <label class="checkbox float-left">
                                <input type="checkbox" value="remember-me">
                                Remember me
                            </label>

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>


    </form:form>

</div>
</body>
</html>