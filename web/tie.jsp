<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/9/18
    Time: 19:33
    -->
    <title>${tie.tieTitle} - ${bar.barName}吧</title>
    <link rel="stylesheet" href="plugins/fontawesome.css">
    <link rel="stylesheet" href="css/logAndReg.css">
    <link rel="stylesheet" href="css/tieStyle.css">
</head>
<body>
<div class="topnav"><%@include file="logAndReg.jsp"%></div>
<script>
    const tieID="${tie.tieID}";
    const tieTitle="${tie.tieTitle}";
    const pageCount="${pageCount}";
    const curPage="${curPage}";
</script>

<div class="base">
    <div class="barName">
        <a href="Bars?id=${bar.barID}">${bar.barName}吧</a>
        <div class="favorite">收藏帖子</div>
    </div>
    <div class="tieTitle">${tie.tieTitle}</div>
    <div class="reContent">
        <c:if test="${curPage == 1}">
        <div class="reBox">
            <div class="boxLeft">
                <img src="img/avatar/${tie.tieUserID}.jpg" onerror="javascript:this.src='img/avatar/0.jpg'" title="${tie.tieUserID}">
                <span>${tie.tieUser}</span>
                <span>楼主</span>
            </div>
            <div class="boxRight">
                <div class="reMain">${tie.tieMain}</div>
                <div class="reTime"><div>${tie.postTime}</div></div>
            </div>
        </div>
        </c:if>
        <c:forEach var="r" items="${tieReply}">
        <div class="reBox">
            <div class="boxLeft">
                <img src="img/avatar/${r.reUserID}.jpg" onerror="javascript:this.src='img/avatar/0.jpg'" title="${r.reUserID}">
                <span>${r.reUser}</span>
                <span class="dfloor">${r.floor}楼</span>
                <span class="dreply">回复</span>
            </div>
            <div class="boxRight">
                <div class="reMain dmain">${r.reply}</div>
                <div class="reTime"><div>${r.reTime}</div></div>
            </div>
        </div>
        </c:forEach>
    </div>
    <div class="rePage">
        <a href="Ties?post=${tie.tieID}&page=1">首页</a>
        <a href="Ties?post=${tie.tieID}&page=${curPage-1}">上一页</a>
        <div>共${pageCount}页, 当前第${curPage}页</div>
        <a href="Ties?post=${tie.tieID}&page=${curPage+1}">下一页</a>
        <a href="Ties?post=${tie.tieID}&page=${pageCount}">尾页</a>
        <div>跳转至<input type="text" class="pageSet">页<button type="button" class="pagePort">跳页</button></div>
    </div>
    <div class="tieReply">
        <div class="replyExpression">
            <div class="eps fa fa-meh-o">
                <div class="epses" style="display: none">
                    <c:forEach var="i" begin="0" end="49" step="1">
                    <img src="img/eps/${i}.png">
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="replyContent" contenteditable="true"></div>
        <button type="button" class="replySubmit fa fa-paper-plane">提交</button>
    </div>
    <div class="submitInfo" style="text-align: center">长度不超过10000字符，不能含有特殊字符, 表情视作27个字符</div>
</div>

<div class="fa fa-arrow-circle-up toTop"></div>

<script src="js/tieMain.js"></script>
</body>
</html>