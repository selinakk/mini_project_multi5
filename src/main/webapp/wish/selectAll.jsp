<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 찜 목록</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>찜 목록</h2>
        <table class="table--list">
            <thead>
            <tr>
                <th>썸네일</th>
                <th>상품명</th>
                <th>삭제</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vo" items="${list}">
                <tr>
                    <td><img src="assets/img/${vo.thumbnail}" alt="${vo.product_name} 이미지"></td>
                    <td><a href="p_view.do?product_id=${vo.product_id}">${vo.product_name}</a></td>
                    <td><a href="w_deleteOK.do?wish_no=${vo.wish_no}">삭제</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>