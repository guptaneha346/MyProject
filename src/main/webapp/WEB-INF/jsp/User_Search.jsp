<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<ol>
    <li th:each="user : ${searchResults}">
        <b><span th:text="${user.name}"></span></b> -
        <span th:text="${user.department}"></span> -
        <span th:text="${user.number}"></span>
    </li>
</ol>
</body>
</html>