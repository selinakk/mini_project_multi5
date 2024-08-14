<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 게시글 작성</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>게시글 작성</h2>
        <form action="b_insertOK.do" method="post">
            <table>
                <tr>
                    <th><label for="title">제목</label></th>
                </tr>
                <tr>
                    <td><input type="text" id="title" name="title" value="글 작성 테스트 중입니다" maxlength="25"></td>
                </tr>
                <tr>
                    <th><label for="writer">작성자</label></th>
                </tr>
                <tr>
                    <td>${user_id}<input type="hidden" id="writer" name="writer" maxlength="10" value="${user_id}"></td>
                </tr>
                <tr>
                    <th><label for="content">내용</label></th>
                </tr>
                <tr>
                    <td>
                        <textarea name="content" id="content" rows="10" maxlength="250">안녕하세요.</textarea>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="등록"></td>
                </tr>
            </table>
        </form>
        </div>
</body>
</html>
