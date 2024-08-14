<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="assets/css/style.css"/>
<header>
  <div class="header__top">
    <span><c:if test="${!empty user_id}">${name}님, 환영합니다!</c:if></span>
    <ul>
      <li><a href="home.do">홈</a></li>
      <c:choose>
        <c:when test="${empty user_id}">
          <li><a href="m_insert.do">회원가입</a></li>
          <li><a href="login.do">로그인</a></li>
        </c:when>
        <c:when test="${user_id=='admin'}">
          <li><a href="m_list.do">회원목록</a></li>
          <li><a href="logout.do">로그아웃</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="m_view.do?user_id=${user_id}">회원정보</a></li>
          <li><a href="logout.do">로그아웃</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
  <nav>
    <ul>
      <li>
        <ul>
          <c:if test="${user_id=='admin'}">
            <li><a href="b_insert.do">글작성</a></li>
          </c:if>
          <li><a href="b_list.do">글목록</a></li>
        </ul>
      </li>
      <li>
        <ul>
          <c:if test="${user_id=='admin'}">
            <li><a href="p_insert.do">상품등록</a></li>
          </c:if>
          <li><a href="p_list.do">상품목록</a></li>
          <li><a href="o_list.do?user_id=${user_id}">주문목록</a></li>
          <li><a href="c_list.do?user_id=${user_id}">장바구니</a></li>
          <li><a href="w_list.do?user_id=${user_id}">찜</a></li>
        </ul>
      </li>
    </ul>
  </nav>
</header>
