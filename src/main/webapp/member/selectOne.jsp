<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>::: 마켓멀티 ::: 회원 상세</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>회원 상세</h2>
    <table>
      <tbody>
        <tr>
          <th>아이디</th>
          <td>${vo2.user_id}</td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td>${vo2.pw}</td>
        </tr>
        <tr>
          <th>이름</th>
          <td>${vo2.name}</td>
        </tr>
        <tr>
          <th>전화번호</th>
          <td>${vo2.tel}</td>
        </tr>
        <tr>
          <th>주소</th>
          <td>${vo2.addr}</td>
        </tr>
      </tbody>
    </table>
    <div class="view__opt">
      <a href="m_update.do?user_id=${vo2.user_id}">회원정보 수정</a>
      <a href="m_delete.do?user_id=${vo2.user_id}">회원 삭제</a>
    </div>
  </div>
</body>
</html>
