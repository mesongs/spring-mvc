<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 업로드 테스트</h2>
	<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/file/upload.do">
		<input type="text" name="id" value="test" /><br/>  
		<input type="file" name="attachFile1" /><br/>
		<input type="file" name="attachFile2" /><br/>
		<input type="submit" value="업로드" /><br/>  
	</form>
	
	<!--mvc.xml 수정, 컨트롤러 없이 이것을 포워드 시켜  -->
	<!--<mvc:view-controller path="/file/uploadForm.do" view-name="file/fileUploadForm"/>  -->
	<!--실제 업로드하기 위해 또 spring mvc에 추가  -->
</body>
</html>