<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>::: 마켓멀티 ::: 회원 목록</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>회원 목록</h2>
    <form action="m_searchList.do">
      <select name="searchKey" id="searchKey">
        <option value="name">이름</option>
        <option value="user_id">아이디</option>
      </select>
      <input type="text" name="searchWord" placeholder="검색어를 입력하세요">
      <input type="submit" value="검색">
    </form>
    <table class="table--list">
      <thead>
      <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>전화번호</th>
        <th>주소</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="vo" items="${list}">
        <tr>
          <td>${vo.user_id}</td>
          <td><a href="m_view.do?user_id=${vo.user_id}">${vo.name}</a></td>
          <td>${vo.tel}</td>
          <td>${vo.addr}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  </body>
</html>
