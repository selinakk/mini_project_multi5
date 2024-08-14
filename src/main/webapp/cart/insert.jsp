<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>::: 마켓멀티 ::: 장바구니 담기</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>장바구니 담기</h2>
    <form action="c_insertOK.do" method="post">
      <input type="hidden" id="user_id" name="user_id" value="${user_id}">
      <input type="hidden" id="product_id" name="product_id" value="${param.product_id}">
      <input type="hidden" id="price" name="price" value="${param.price}">
      <table>
        <tr>
          <td><label for="qty">수량</label></td>
        </tr>
        <tr>
          <td>
            <input type="number" id="qty" name="qty" value="1" min="1" max="100">
          </td>
        </tr>
        <tr>
          <td>
            <button type="button" onclick="history.back()">취소</button>
            <input type="submit" value="담기">
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>

</html>