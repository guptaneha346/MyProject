<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User List</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>

</head>
<body>
<div class="container">
    <h2>User List</h2>
    <table class="table table-striped">
        <thead>
        <th scope="row">#ID&nbsp;&nbsp;&nbsp;&nbsp;</th>
        <th scope="row">Name&nbsp;&nbsp;&nbsp;&nbsp;</th>
        <th scope="row">Department&nbsp;&nbsp;&nbsp;&nbsp;</th>
        <th scope="row">Number</th>

        </thead>
        <tbody>
        <c:forEach items="${userlist }" var="user">
            <tr>
                <td>${user.id }</td>
                <td>${user.name }</td>
                <td> ${user.department }</td>
                <td>${user.number }</td>
                <td>

                    <spring:url value="/user/updateUser/${user.id }" var="updateURL"/>
                        <%--  <a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>--%>
                    <a class="glyphicon glyphicon-edit" href="${updateURL }" role="button">

                    </a>
                </td>
                <td>
                    <spring:url value="/user/deleteUser/${user.id }" var="deleteURL"/>
                        <%--        <a class="btn btn-primary" href="${deleteURL }" role="button" >Delete</a> --%>
                    <a class="glyphicon glyphicon-trash" href="${deleteURL }" role="button">

                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/user/addUser/" var="addURL"/>
    <a class="btn btn-primary" href="${addURL }" role="button">Add New User&nbsp;&nbsp;&nbsp;&nbsp;</a>

    <spring:url value="/user/searchUser/" var="addURL"/>
    <a class="btn btn-primary" href="${addURL }" role="button">Search User</a>
</div>
</body>
</html>