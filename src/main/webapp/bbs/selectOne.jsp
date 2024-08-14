<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="${vo2.title}"/>
<%
    String encodedTitle = java.net.URLEncoder.encode((String)pageContext.getAttribute("title"), "UTF-8");
%>
<html>
<head>
    <title>::: 마켓멀티 ::: ${param.bbs_no}번 게시글</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>${param.bbs_no}번 게시글</h2>
        <table class="table--bbs tx--cent">
            <thead>
                <tr>
                    <td><h2>${vo2.title}</h2></td>
                </tr>
                <tr>
                    <td>
                        <em>번호 </em><span>${vo2.bbs_no}</span> |
                        <em>작성자 </em><span>${vo2.writer}</span> |
                        <em>작성일 </em><span>${vo2.bdate}</span><br/><br/><br/>
                        <hr><br/><br/>
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${vo2.content}</td>
                </tr>
            </tbody>
        </table>
    <c:if test="${user_id=='admin'}">
        <div class="view__opt">
            <a href="b_update.do?bbs_no=${vo2.bbs_no}">글 수정</a>
            <a href="b_delete.do?bbs_no=${vo2.bbs_no}&title=<%= encodedTitle %>)">글 삭제</a>
        </div>
    </c:if>
    </div>
</body>
</html>
