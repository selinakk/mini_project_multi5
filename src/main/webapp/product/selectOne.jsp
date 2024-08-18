<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>::: 마켓멀티 ::: 상품 상세</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>상품 상세</h2>
  <table>
    <tbody>
    <tr>
      <th>상품코드</th>
      <td>${vo2.product_id}</td>
    </tr>
    <tr>
      <th>상품명</th>
      <td>${vo2.product_name}</td>
    </tr>
    <tr>
      <th>썸네일</th>
      <td><img src="assets/img/${vo2.thumbnail}" alt="${vo2.product_name} 이미지"></td>
    </tr>
    <tr>
      <th>제조사</th>
      <td>${vo2.mfr}</td>
    </tr>
    <tr>
      <th>가격</th>
      <td>${vo2.price}원</td>
    </tr>
    </tbody>
  </table>
  <c:if test="${!empty user_id}">
    <div class="view__opt">
      <a href="c_insert.do?product_id=${vo2.product_id}&price=${vo2.price}">장바구니 담기</a>
      <a href="w_insertOK.do?product_id=${vo2.product_id}&user_id=${user_id}">찜하기</a>
    </div>
  </c:if>
  <c:if test="${user_id=='admin'}">
    <div class="view__opt">
      <a href="p_update.do?product_id=${vo2.product_id}">상품 수정</a>
      <a href="p_delete.do?product_id=${vo2.product_id}">상품 삭제</a>
    </div>
  </c:if>
  <hr><br/><br/>
  <h3>리뷰 목록</h3>
  <c:forEach var="rvo" items="${rlist}">
    <a href="r_view.do?review_no=${rvo.review_no}" class="review-wrapper">
      <p>${rvo.review_no}번 | ${rvo.user_id} | ${rvo.rdate}</p>
      <p class="review__stars">${rvo.rating}점</p><br/>
      <p>${rvo.content}</p>
    </a>
  </c:forEach>
  </table>
</div>
</body>
</html>
