<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 장바구니 목록</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <table class="table--list">
    <h2>장바구니 목록</h2>
    <thead>
      <tr>
        <th>썸네일</th>
        <th>상품명</th>
        <th>수량</th>
        <th>가격</th>
        <th>가격</th>
        <th>구매</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="vo" items="${list}">
      <tr>
        <td><img src="assets/img/${vo.thumbnail}" alt="${vo.product_name} 이미지"></td>
        <td><a href="p_view.do?product_id=${vo.product_id}">${vo.product_name}</a></td>
        <td>${vo.qty}</td>
        <td>${vo.price}원</td>
        <td>${vo.price*vo.qty}원</td>
        <td><a href="o_insert.do?product_id=${vo.product_id}&price=${vo.price}&qty=${vo.qty}">구매하기</a></td>
        <td><a href="c_deleteOK.do?cart_no=${vo.cart_no}">삭제</a></td>
    </c:forEach>
    </tbody>
  </table>
  </div>
</body>
</html>