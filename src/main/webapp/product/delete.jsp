<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 상품 삭제</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>상품 삭제</h2>
    <form action="p_deleteOK.do" method="post">
      <input type="hidden" id="product_id" name="product_id" value="${param.product_id}">
      <table>
        <tbody>
        <tr>
          <td colspan="2">${param.product_id} 상품을 삭제합니다.</td>
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
