<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 로그인</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>로그인</h2>
    <form action="loginOK.do" method="post">
      <table>
        <tr>
          <th><label for="user_id">아이디</label></th>
          <td><input type="text" id="user_id" name="user_id" value="admin"></td>
        </tr>
        <tr>
          <th><label for="pw">비밀번호</label></th>
          <td><input type="password" id="pw" name="pw" value="1234"></td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="로그인"></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>
