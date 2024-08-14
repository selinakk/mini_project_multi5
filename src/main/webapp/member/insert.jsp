<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>::: 마켓멀티 ::: 회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(function() {
            console.log("onloaded...");
            $("#btn_checkId").on("click", function(){
                console.log("btn_checkId clicked..");
                $.ajax({
                    url: "http://localhost:8090/mini_05_marketMulti_war_exploded/m_checkId.do",
                    type: "get",
                    data: {user_id: $("#user_id").val()},
                    dataType: "json",
                    success: function (response) {
                        console.log(response);
                        let resultMsg = "";
                        if (response.result == 'found') {
                            resultMsg = "❌중복된 아이디";
                        } else {
                            resultMsg = "사용 가능합니다";
                            $("#joinSubmit").removeAttr("disabled");
                        }
                        $("#resultTxt").html(resultMsg);
                    }, error: function (xhr, status, error) {
                        console.error("AJAX Error: ", status, error);
                    }

                });
            });
        });
    </script>
</head>
<body>
    <jsp:include page="../include/header.jsp"/>
    <div class="container">
        <h2>회원가입</h2>
        <form action="m_insertOK.do" method="post">
            <table>
                <tr>
                    <td><label for="user_id">아이디</label></td>
                    <td>
                        <input type="text" id="user_id" name="user_id" value="admin" placeholder="아이디를 입력하세요" style="width:10rem;">
                        <input type="button" id="btn_checkId" value="중복확인"/>
                        <small id="resultTxt">중복 체크 필수</small>
                    </td>
                </tr>
                <tr>
                    <td><label for="pw">비밀번호</label></td>
                    <td><input type="password" id="pw" name="pw" value="0000" placeholder="비밀번호를 입력하세요"></td>
                </tr>
                <tr>
                    <td><label for="name">이름</label></td>
                    <td><input type="text" id="name" name="name" value="김손님" placeholder="이름을 입력하세요"></td>
                </tr>
                <tr>
                    <td><label for="tel">전화번호</label></td>
                    <td><input type="text" id="tel" name="tel" value="01087654321" placeholder="전화번호를 입력하세요"></td>
                </tr>
                <tr>
                    <td><label for="addr">주소</label></td>
                    <td><input type="text" id="addr" name="addr" value="경기 성남시 분당구 분당내곡로 131" placeholder="주소를 입력하세요"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input id="joinSubmit" type="submit" value="가입완료" disabled></td>
                </tr>
            </table>
        </form>
    </div>
</body>

</html>