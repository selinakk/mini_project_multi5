<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 리뷰 수정</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>리뷰 수정</h2>
    <form action="r_updateOK.do" method="post">
        <input type="hidden" name="review_no" id="review_no" value="${param.review_no}">
      <div class="review-wrapper">
        <p>${vo2.review_no}번 | ${vo2.user_id} | ${vo2.rdate}</p><br/>
        <input type="number" name="rating" id="rating" value="${vo2.rating}" placeholder="별점을 정수 1~5 범위에서 입력하세요"> 점<br/><br/>
        <input type="text" name="content" id="content" value="${vo2.content}" placeholder="리뷰 내용을 입력하세요" maxlength="250" size="100"><br/><br/><br/>
        <div class="tx--cent">
          <input type="submit" value="수정완료">
        </div>
      </div>
    </form>
  </div>
</body>
</html>
