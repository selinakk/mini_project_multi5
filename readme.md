#### 개선할 점
**As is**: 현재 미리 저장해둔 이미지 폴더의 이미지 파일명을 입력받아 이미지를 노출하는 불편한 방식 =>

**To be**: input:file 업로드 방식으로
- - -
**As is**: 로그인을 안한 상태에서 일부 기능 실행 시 null을 sql안에 삽입할 수 없다고 500에러 발생 =>

**To be**: $(user_id)가 null이 아닐 때만 기능 버튼을 노출하기
**To be**: Controller에서 user_id가 null인 경우 동작 안하도록 수정(사용자에게는 안내)
- - -
**As is**: 코드 및 로그 정돈 =>

**To be**: 검색 수행할 때 검색 결과 n건 로그 찍기