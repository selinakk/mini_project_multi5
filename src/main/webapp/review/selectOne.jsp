<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 리뷰 ${param.review_no}번</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>리뷰 보기</h2>
    <div class="review-wrapper">
      <p>${vo2.review_no}번 | ${vo2.user_id} | ${vo2.rdate}</p>
      <p class="review__stars">${vo2.rating}점</p><br/>
      <p>${vo2.content}</p>
    </div>
      <c:if test="${vo2.user_id == user_id}">
      <div class="view__opt">
        <a href="r_update.do?review_no=${vo2.review_no}">리뷰 수정</a>
        <a href="r_deleteOK.do?review_no=${vo2.review_no}&product_id=${vo2.product_id}">리뷰 삭제</a>
      </div>
      </c:if>
    </table>
  </div>
</body>
</html>
