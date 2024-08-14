<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 게시글 수정</title>
</head>
<body>
  <jsp:include page="../include/header.jsp"/>
  <div class="container">
    <h2>${param.bbs_no}번 게시글 수정</h2>
    <form action="b_updateOK.do" method="post">
      <input type="hidden" id="bbs_no" name="bbs_no" value="${param.bbs_no}">
      <table>
        <tr>
          <th><label for="title">제목</label></th>
        </tr>
        <tr>
          <td><input type="text" id="title" name="title" value="${vo2.title}" maxlength="25"></td>
        </tr>
        <tr>
          <th><label for="writer">작성자</label></th>
        </tr>
        <tr>
          <td>${user_id}<input type="hidden" id="writer" name="writer" maxlength="10" value="${vo2.writer}"></td>
        </tr>
        <tr>
          <th><label for="content">내용</label></th>
        </tr>
        <tr>
          <td>
            <textarea name="content" id="content" rows="10" maxlength="250">${vo2.content}</textarea>
          </td>
        </tr>
        <tr>
          <td class="right">
            <button type="button" onclick="history.back()">취소</button>
            <input type="submit" value="수정완료">
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>
