<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/7/20
    Time: 21:35
    -->
    <title>贴吧搜索</title>
    <link rel="stylesheet" href="plugins/fontawesome.css">
    <link rel="stylesheet" href="css/searchStyle.css">
</head>
<body>
<div class="base">
    <div class="ruleDiv">
        <p>下方输入框输入关键词搜索</p>
        <p>输入xxx即可，不要输入xxx吧</p>
        <p>什么都不输入直接搜索即为查询所有贴吧</p>
    </div>
    <div class="searchForm">
        <form action="SearchServlet">
            <input type="text" name="search" placeholder="搜索贴吧">
            <button type="submit" class="fa fa-search"></button>
        </form>
    </div>
    <div class="resultDiv">
        <c:forEach var="rs" items="${list}">
            <a href="Bars?id=${rs.barID}">${rs.barName}吧</a>
        </c:forEach>
    </div>
</div>
</body>
</html>