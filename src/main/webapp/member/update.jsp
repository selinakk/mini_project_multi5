<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>::: 마켓멀티 ::: 회원정보 수정</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>회원정보 수정</h2>
    <form action="m_updateOK.do" method="post">
      <input type="hidden" id="user_id" name="user_id" value="${vo2.user_id}">
      <table>
        <tr>
          <td>아이디</td>
          <td>
            ${vo2.user_id}
          </td>
        </tr>
        <tr>
          <td><label for="pw">비밀번호</label></td>
          <td><input type="password" id="pw" name="pw" value="${vo2.pw}" placeholder="비밀번호를 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="name">이름</label></td>
          <td><input type="text" id="name" name="name" value="${vo2.name}" placeholder="이름을 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="tel">전화번호</label></td>
          <td><input type="text" id="tel" name="tel" value="${vo2.tel}" placeholder="전화번호를 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="addr">주소</label></td>
          <td><input type="text" id="addr" name="addr" value="${vo2.addr}" placeholder="주소를 입력하세요"></td>
        </tr>
        <td colspan="2"><input type="submit" value="수정완료"></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>