<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/7/22
    Time: 12:33
    -->
    <title>${bar.barName}吧</title>
    <link rel="stylesheet" href="./plugins/fontawesome.css">
    <link rel="stylesheet" href="./css/logAndReg.css">
    <link rel="stylesheet" href="./css/barStyle.css">
</head>
<body>
<div class="topnav"><%@ include file="logAndReg.jsp"%></div>
<script>
    const barID="${bar.barID}";
    const barName="${bar.barName}";
    const pageCount="${pageCount}";
    const curPage="${curPage}";
</script>

<div class="base">
    <div class="barTitle">
        <div>${bar.barName}吧</div>
        <div class="follow">关注贴吧</div>
    </div>
    <div class="barContent">贴吧简介:<br>${bar.barContent}</div>
    <div class="horizon">帖子列表<a href="BarsElite?id=${bar.barID}" class="elite">精品区</a></div>
    <div class="barMain">
        <c:if test="${ties.size()==0}">
        <div style="font-size: 24px">还没有人在这个贴吧发帖哦</div>
        </c:if>
        <c:forEach var="t" items="${ties}">
        <div class="tie">
            <div class="tieTitle"><a href="Ties?post=${t.tieID}" target="_blank" rel="noopener">${t.tieTitle}</a></div>
            <div class="tieMain">${t.tieMain}</div>
            <div class="tieInfo">
                <div class="postUser fa fa-user-o">${t.tieUser}</div>
                <div class="postTime fa fa-history">${t.postTime}</div>
            </div>
        </div>
        </c:forEach>
    </div>
    <div class="barPage">
        <a href="Bars?id=${bar.barID}&page=1">首页</a>
        <a href="Bars?id=${bar.barID}&page=${curPage-1}">上一页</a>
        <div>共${pageCount}页, 当前第${curPage}页</div>
        <a href="Bars?id=${bar.barID}&page=${curPage+1}">下一页</a>
        <a href="Bars?id=${bar.barID}&page=${pageCount}">尾页</a>
        <div>跳转至<input type="text" class="pageSet">页<button type="button" class="pagePort">跳页</button></div>
    </div>
    <div class="barNewTie">
        <input type="text" class="newTieTitle" placeholder="请输入标题">
        <div class="newTieExpression">
            <div class="eps fa fa-meh-o">
                <div class="epses" style="display: none">
                    <c:forEach var="i" begin="0" end="49" step="1">
                    <img src="img/eps/${i}.png">
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="newTieContent" contenteditable="true"></div>
        <button type="button" class="tieSubmit fa fa-paper-plane">提交</button>
    </div>
    <div class="submitInfo" style="text-align: center">标题长度不超过20字符，内容不超过640字符，不能含有特殊字符 < >将视作4个字符, 表情视作27个字符</div>
</div>

<div class="fa fa-arrow-circle-up toTop"></div>

<%--<script src="js/customFunction.js"></script>--%>
<%--<script src="js/logAndReg.js"></script>--%>
<script src="js/barMain.js"></script>
</body>
</html>