<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 게시글 삭제</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>${param.bbs_no}번 게시글 삭제</h2>
        <form action="b_deleteOK.do" method="post">
            <input type="hidden" id="bbs_no" name="bbs_no" value="${param.bbs_no}">
            <table>
                <tbody>
                    <tr>
                        <td colspan="2">${param.bbs_no}번 게시글 ⟪${param.title}⟫을(를) 삭제합니다.</td>
                    </tr>
                    <tr>
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
