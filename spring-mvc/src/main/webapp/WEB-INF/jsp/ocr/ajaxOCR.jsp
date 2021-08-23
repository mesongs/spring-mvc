<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="jquery.ajax-cross-origin.min.js"></script>


<script>
		

$.ajax({

	async : true,
    crossOrigin : true,
    dataType : "json",
    url : "https://55c7547061ee4d91891f615ee5e7c101.apigw.ntruss.com/custom/v1/10633/1ffc0406b9891fcd265a225e83a668fa045f1282588f80c8d11c029bad156d85/infer",
    method : "POST",
    headers: {
	    "content-type": "application/json",
	    "x-ocr-secret": "dFpna0dQcVlGT3JDb1hQY2hYZnlYTkRwTUtsd0lIbko=",
	    "cache-control": "no-cache",
	    "postman-token": "c0e3d495-8a57-7824-9785-1bfef6bd7150"
	},
	processData: false,
	data: "{\n\t\"version\" : \"V1\",\n\t\"requestId\" : \"test2\",\n\t\"timestamp\" : 0,\n\t\"images\" :[{\n\t\t\n\t\t\"format\" : \"JPG\",\n\t\t\"url\" : \"https://kr.object.ncloudstorage.com/testbucke/tesetT.JPG\",\n\t\t\"name\" : \"tesetT\"\n\t}]\n}",
	success : function(data) {
		
        console.log(data);
    }
});

		
		

</script>

</head>
<body>

</body>
</html>