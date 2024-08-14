<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>::: 마켓멀티 ::: 상품 수정</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
    <div class="container">
      <h2>상품 수정</h2>
    <form action="p_updateOK.do" method="post">
      <input type="hidden" id="product_id" name="product_id" value="${vo2.product_id}">
      <table>
        <tr>
          <td><label for="product_id">상품코드</label></td>
          <td>${vo2.product_id}</td>
        </tr>
        <tr>
          <td><label for="product_name">상품명</label></td>
          <td><input type="text" id="product_name" name="product_name" value="${vo2.product_name}" placeholder="상품명을 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="mfr">제조사</label></td>
          <td><input type="text" id="mfr" name="mfr" value="${vo2.mfr}" placeholder="제조사를 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="thumbnail">썸네일</label></td>
          <td><input type="text" id="thumbnail" name="thumbnail" value="${vo2.thumbnail}"></td>
        </tr>
        <tr>
          <td><label for="price">가격</label></td>
          <td><input type="number" id="price" name="price" value="${vo2.price}" placeholder="주소를 입력하세요" min="100"></td>
        </tr>
        <td colspan="2"><input type="submit" value="수정완료"></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>