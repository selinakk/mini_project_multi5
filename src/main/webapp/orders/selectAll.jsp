<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>::: 마켓멀티 ::: 주문 목록</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>주문 목록</h2>
    <form action="o_searchList.do">
      <select name="searchKey">
        <option value="order_no">주문번호</option>
        <option value="user_id">회원아이디</option>
      </select>
      <input type="text" name="searchWord">
      <input type="submit" value="검색">
    </form>
    <table class="table--list">
      <thead>
      <tr>
        <th>주문번호</th>
        <th>회원아이디</th>
        <th>상품명</th>
        <th>주문금액</th>
        <th>주문일시</th>
        <th>상태</th>
        <th>리뷰</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="vo" items="${list}">
        <tr>
          <td><a href="o_view.do?order_no=${vo.order_no}">${vo.order_no}</a></td>
          <td>${vo.user_id}</td>
          <td><a href="p_view.do?product_id=${vo.product_id}">${vo.product_name}</a></td>
          <td>${vo.qty*vo.price}원</td>
          <td>${vo.odate}</td>
          <td>${vo.status}</td>
          <td><a href="r_insert.do?product_id=${vo.product_id}">리뷰작성</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>