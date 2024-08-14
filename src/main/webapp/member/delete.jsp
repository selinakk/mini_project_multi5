<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 회원 삭제</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>회원 삭제</h2>
    <form action="m_deleteOK.do" method="post">
      <input type="hidden" id="user_id" name="user_id" value="${param.user_id}">
      <table>
        <tbody>
        <tr>
          <td colspan="2">${param.user_id} 회원을 삭제합니다.</td>
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
