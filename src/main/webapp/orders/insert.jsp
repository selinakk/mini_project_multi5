<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>::: 마켓멀티 ::: 상품 구입</title>
  <script>

  </script>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>상품 구입</h2>
    <form action="o_insertOK.do" method="post">
      <input type="hidden" id="user_id" name="user_id" value="${user_id}">
      <input type="hidden" id="product_id" name="product_id" value="${param.product_id}">
      <input type="hidden" id="price" name="price" value="${param.price}">
      <input type="hidden" id="status" name="status" value="결제완료">
      <input type="hidden" id="qty" name="qty" value="${param.qty}">
      <table>
        <tr>
          <th>수량</th>
        </tr>
        <tr>
          <td>
            ${param.qty}개
          </td>
        </tr>
        <tr>
          <th>가격</th>
        </tr>
        <tr>
          <td>
            ${param.price}원
          </td>
        </tr>
        <tr>
          <td>
            <hr>
            <br/>
            <h3>총 <strong>${param.price*param.qty}</strong>원</h3>
            <br/><br/>
          </td>
        </tr>
        <tr>
          <th><label for="shipping_addr">배송지</label></th>
        </tr>
        <tr>
          <td>
            <input type="text" id="shipping_addr" name="shipping_addr" value="서울특별시 구로구 123번길 45">
          </td>
        </tr>
        <tr class="right">
          <td>
            <button type="button" onclick="history.back()">취소</button>
            <input type="submit" value="구매완료">
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>

</html>