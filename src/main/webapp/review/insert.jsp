<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>::: 마켓멀티 ::: 리뷰 작성</title>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>리뷰 작성</h2>
        <form action="r_insertOK.do" method="post">
            <input type="hidden" id="product_id" name="product_id" value="${param.product_id}">
            <input type="hidden" id="user_id" name="user_id" value="${user_id}">
            <table>
                <tr>
                    <td><label for="rating">별점</label></td>
                    <td><input type="number" name="rating" id="rating" step="1" min="1" max="5" value="5"></td>
                </tr>
                <tr>
                    <td><label for="content">내용</label></td>
                    <td><input type="text" id="content" name="content"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="리뷰 등록">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>


