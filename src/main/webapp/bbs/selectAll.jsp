<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>::: 마켓멀티 ::: 게시글 목록</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>게시글 목록</h2>
        <form action="b_searchList.do">
            <select name="searchKey">
                <option value="title">제목</option>
                <option value="content">내용</option>
            </select>
            <input type="text" name="searchWord" placeholder="검색어를 입력해 주세요">
            <input type="submit" value="검색">
        </form>
        <table class="table--list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="vo" items="${list}">
                <tr>
                    <td>${vo.bbs_no}</td>
                    <td><a href="b_view.do?bbs_no=${vo.bbs_no}">${vo.title}</a></td>
                    <td>${vo.writer}</td>
                    <td>${vo.bdate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
