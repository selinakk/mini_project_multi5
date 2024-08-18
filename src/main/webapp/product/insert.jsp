<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>::: 마켓멀티 ::: 상품 등록</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container">
  <h2>상품 등록</h2>
    <form action="p_insertOK.do" method="post" enctype="multipart/form-data">
      <table>
        <tr>
          <td><label for="product_id">상품코드</label></td>
          <td>
            <input type="text" id="product_id" name="product_id" value="harim_twochick_1100g" placeholder="제조사+상품명+번호">
          </td>
        </tr>
        <tr>
          <td><label for="product_name">상품명</label></td>
          <td><input type="text" id="product_name" name="product_name" value="하림 두마리 복닭(1.1kg)" placeholder="상품명을 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="mfr">제조사</label></td>
          <td><input type="text" id="mfr" name="mfr" value="하림" placeholder="제조사를 입력하세요"></td>
        </tr>
        <tr>
          <td><label for="thumbnail">썸네일</label></td>
          <td><input type="file" name="thumbnail" id="thumbnail"></td>
        </tr>
        <tr>
          <td><label for="price">가격</label></td>
          <td><input type="number" id="price" name="price" value="8800" placeholder="가격을 입력하세요" min="100"></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="submit" value="등록">
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>