<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>::: 마켓멀티 ::: 주문 수정</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
    <h2>주문 수정</h2>
        <form action="o_updateOK.do" method="post">
            <input type="hidden" id="order_no" name="order_no" value="${param.order_no}">
            <table>
                <tr>
                    <td><label for="status">상태</label></td>
                </tr>
                <tr>
                    <td>
                        <select name="status" id="status">
                            <option value="결제완료">결제완료</option>
                            <option value="배송중">배송중</option>
                            <option value="배송완료">배송완료</option>ㄴ
                            <option value="주문취소">주문취소</option>
                        </select>
                    </td>
                </tr>
                <tr class="right">
                    <td>
                        <button type="button" onclick="history.back()">취소</button>
                        <input type="submit" value="수정완료">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>