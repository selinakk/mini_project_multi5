<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 주문내역 삭제</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>주문내역 삭제</h2>
    <form action="o_deleteOK.do" method="post">
      <input type="hidden" id="order_no" name="order_no" value="${param.order_no}">
      <table>
        <tbody>
        <tr>
          <td colspan="2">주문번호 '${param.order_no}' 주문 내역을 삭제합니다.</td>
        </tr>
        <tr class="right">
          <td>
            <button type="button" onclick="history.back()">취소</button>
            <input type="submit" value="삭제">
          </td>
        </tr>
        </tbody>
      </table>
    </form>
  </div>
</body>
</html>
