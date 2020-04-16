<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <!--
    Created by IntelliJ IDEA.
    User: Ilirus
    Date: 2018/10/19
    Time: 12:02
    -->
    <title>${bar.barName}吧 - 精品贴</title>
    <link rel="stylesheet" href="plugins/fontawesome.css">
    <link rel="stylesheet" href="css/logAndReg.css">
    <link rel="stylesheet" href="css/barStyle.css">
</head>
<body>
<div class="topnav"><%@ include file="logAndReg.jsp"%></div>
<script>
    const barID="${bar.barID}";
    const barName="${bar.barName}";
</script>

<div class="base">
    <div class="barTitle">
        <div>${bar.barName}吧</div>
    </div>
    <div class="barContent">贴吧简介:<br>${bar.barContent}</div>
    <div class="horizon">帖子列表</div>
    <div class="barMain">
        <c:if test="${ties.size()==0}">
        <div style="font-size: 36px;">没有记录</div>
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
    <%--<div class="barPage">--%>
        <%--<a href="Bars?id=${bar.barID}&page=1">首页</a>--%>
        <%--<a href="Bars?id=${bar.barID}&page=${curPage-1}">上一页</a>--%>
        <%--<div>共${pageCount}页, 当前第${curPage}页</div>--%>
        <%--<a href="Bars?id=${bar.barID}&page=${curPage+1}">下一页</a>--%>
        <%--<a href="Bars?id=${bar.barID}&page=${pageCount}">尾页</a>--%>
        <%--<div>跳转至<input type="text" class="pageSet">页<button type="button" class="pagePort">跳页</button></div>--%>
    <%--</div>--%>
</div>

<div class="fa fa-arrow-circle-up toTop"></div>
<script>
    const toTop = sel(".toTop");
    window.addEventListener("scroll",()=>{
        if(window.pageYOffset>250){
            toTop.style.opacity="1";
            toTop.style.transform="translateX(0px)";
        } else {
            toTop.style.opacity="0";
            toTop.style.transform="translateX(100px)";
        }
    });
    toTop.onclick=()=>{
        sel("html").scrollIntoView({behavior:"smooth",block:"start"})
    };
</script>
<%--<script src="js/barMain.js"></script>--%>
</body>
</html>