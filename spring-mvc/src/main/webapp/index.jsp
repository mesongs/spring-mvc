<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- *.do로 보내면, 해당하는 컨트롤러가 실행되도록 RequestMapping annotation으로 지정해놓음  -->
	<a href="<%= request.getContextPath() %>/hello/hello.do">hello</a><br>
	
	<a href="<%= request.getContextPath() %>/method/method.do">method</a><br>
	
	<!-- /form으로 시작하면 MemberController로 보냄 -->
	<a href="<%= request.getContextPath() %>/form/joinForm.do">form</a><br>
	
	<a href="<%= request.getContextPath() %>/ajax/resBody.do">문자열 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/resBody.json">json 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/resVOBody.json">json vo 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/resStringListBody.json">json list 문자열 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/resVOListBody.json">json list (VO) 응답</a><br>
	
	<a href="<%= request.getContextPath() %>/ajax/restBody.do">rest 문자열 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/restBody.json">rest json 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/restVOBody.json">rest json vo 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/restStringListBody.json">rest json list 문자열 응답</a><br>
	<a href="<%= request.getContextPath() %>/ajax/restVOListBody.json">rest json list (VO) 응답</a><br>
	
	<a href="<%= request.getContextPath() %>/file/uploadForm.do">파일 업로드</a><br>
	
	
	<a href="<%= request.getContextPath() %>/hello/hello2.do">ocr테스트</a>
	
	<a href="<%= request.getContextPath() %>/hello/hello4.do">jackson 테스트</a>
	
	<a href="<%= request.getContextPath() %>/hello/hello5.do">jackson 테스트2 성공한 것</a>
	
	<a href="<%= request.getContextPath() %>/hello/hello6.do">Jackson-Map</a>
	
	
	
	
	<%-- <a href="<%= request.getContextPath() %>/hello/hello3.do">parsing </a> --%>
	<%-- <a href="<%= request.getContextPath() %>/hello/hello2.json">ocr결과 json으로</a> --%>
	
	<!--플러그인으로 cors허용 실패-->
	<%-- <a href="<%= request.getContextPath() %>/ajax/ocr.do">ajax-OCR테스트</a> --%>
	
</body>
</html>