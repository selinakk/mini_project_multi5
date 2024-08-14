<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>::: 마켓멀티 ::: 상품 목록</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>상품 목록</h2>
    <form action="p_searchList.do">
      <select name="searchKey">
        <option value="product_name">상품명</option>
        <option value="mfr">제조사</option>
      </select>
      <input type="text" name="searchWord" placeholder="검색어를 입력하세요">
      <input type="submit" value="검색">
    </form>
    <table class="table--list">
      <thead>
      <tr>
        <th>썸네일</th>
        <th>상품코드</th>
        <th>상품명</th>
        <th>제조사</th>
        <th>가격</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="vo" items="${list}">
        <tr>
          <td><img src="assets/img/${vo.thumbnail}" alt="${vo.product_name} 이미지"></td>
          <td>${vo.product_id}</td>
          <td><a href="p_view.do?product_id=${vo.product_id}">${vo.product_name}</a></td>
          <td>${vo.mfr}</td>
          <td>${vo.price}원</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>
