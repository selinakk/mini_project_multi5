<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>::: 마켓멀티 ::: 주문 내역</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>주문 내역</h2>
    <table>
      <tbody>
      <tr>
        <th>주문번호</th>
        <td>${param.order_no}</td>
      </tr>
      <tr>
        <th>회원아이디</th>
        <td>${vo2.user_id}</td>
      </tr>
      <tr>
        <th>상품명 | 상품코드</th>
        <td>${vo2.product_name}  |  ${vo2.product_id}</td>
      </tr>
      <tr>
        <th>주문금액</th>
        <td>${vo2.qty*vo2.price}원</td>
      </tr>
      <tr>
        <th>배송지</th>
        <td>${vo2.shipping_addr}</td>
      </tr>
      <tr>
        <th>주문일시</th>
        <td>${vo2.odate}</td>
      </tr>
      <tr>
        <th>상태</th>
        <td>${vo2.status}</td>
      </tr>
      </tbody>
    </table>
    <div class="view__opt">
      <a href="o_update.do?order_no=${vo2.order_no}">주문 수정</a>
      <a href="o_delete.do?order_no=${vo2.order_no}">주문 삭제</a>
    </div>
    </table>
  </div>
</body>
</html>
