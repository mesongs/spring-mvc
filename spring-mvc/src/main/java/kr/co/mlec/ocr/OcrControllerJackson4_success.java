package kr.co.mlec.ocr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Controller
public class OcrControllerJackson4_success {
	
	@RequestMapping("/hello/hello6.do")
	public ModelAndView ocrTest() {
		
		
		// 지금 url로 사진 파일을 전달하고 있는데.. 그냥 파일로 전달하면 좋을 것 같아서..
		// data요청할 때 base64인코딩해서 보내야하는 걸로 알고있어서 인코딩 시도해보는중
		String strBase64 = "";
		String fileName = "test.JPG";
		String filePath = "C:\\Users\\HP\\git\\spring-mvc\\spring-mvc\\src\\main\\webapp\\resources\\img\\";
		
		String fileFull = filePath+fileName; 
		
		
	    File f = new File(fileFull);
	    if (f.exists() && f.isFile() && f.length() > 0) {
	        
	    	byte[] bt = new byte[(int) f.length()];
	        FileInputStream fis = null;

	        try {
	            fis = new FileInputStream(f);
	            fis.read(bt);
	            strBase64 = new String(Base64.encodeBase64(bt));

	        } catch (Exception e) {
	            throw e;
	           
	        } finally {
	            try {
	                if (fis != null) {
	                    fis.close();
	                }
	            } catch (IOException e) {
	            } catch (Exception e) {
	        }
	    }
		
		
		
		
		
		
		
		
		
		OkHttpClient client = new OkHttpClient();

		// 추가
		ModelAndView mav = new ModelAndView("ocr/ocr"); 
		
		MediaType mediaType = MediaType.parse("application/json");
//		RequestBody body = RequestBody.create(mediaType, "{\n\t\"version\" : \"V1\",\n\t\"requestId\" : \"test2\",\n\t\"timestamp\" : 0,\n\t\"images\" :[{\n\t\t\n\t\t\"format\" : \"png\",\n\t\t\"url\" : \"https://kr.object.ncloudstorage.com/testbucke/KakaoTalk_20210815_174210497.png\",\n\t\t\"name\" : \"KakaoTalk_20210815_174210497\"\n\t}]\n}");
		RequestBody body = RequestBody.create(mediaType, "{\n\t\"version\" : \"V1\","
				+ "\n\t\"requestId\" : \"test2\","
				+ "\n\t\"timestamp\" : 0,"
				+ "\n\t\"images\" :[{\n\t\t\n\t\t\"format\" : \"png\","
				+ "\n\t\t\"url\" : \"https://kr.object.ncloudstorage.com/testbucke/tesetT.JPG\","
				+ "\n\t\t\"name\" : \"tesetT.JPG\"\n\t}]\n}");
		
		
		
		Request request = new Request.Builder()
		  .url("https://55c7547061ee4d91891f615ee5e7c101.apigw.ntruss.com/custom/v1/10633/1ffc0406b9891fcd265a225e83a668fa045f1282588f80c8d11c029bad156d85/infer")
		  .post(body)
		  .addHeader("content-type", "application/json")
		  .addHeader("x-ocr-secret", "dFpna0dQcVlGT3JDb1hQY2hYZnlYTkRwTUtsd0lIbko=")
		  .addHeader("cache-control", "no-cache")
//		  .addHeader("postman-token", "31917011-c262-8273-4761-7a87eef0d3fa")
		  .build();
		
		try {
			
			Response response = client.newCall(request).execute();
			
			ResponseBody responseBody = response.body();

			
//			System.out.println(responseBody.string());
            
			String result = responseBody.string();
			
			System.out.println("api로 받아온 값 String : " + result);
             
			// ObjectMapper를 이용하면 JSON을 Java 객체로 변환할 수 있고, 
			// 반대로 Java 객체를 JSON 객체로 serialization 할 수 있음
			ObjectMapper mapper = new ObjectMapper();
			
			
			// tree형태로 읽어준다, 안에 어떤 값들이 있는지 아는 방법은?
			// JsonNode로 파싱하기 위해 mapper.readTree 사용
			// JsonNode 는 값을 읽을 수만 있고, ObjectNode는 값을 읽고 쓸 수 있다.
			
			// String형을 Json형으로 바꿔서
			
			JsonNode root = mapper.readTree(result);
			
			System.out.println("여기서부터 시작(readTree 한 것) : " + root);
//			{"version":"V1","requestId":"test2","timestamp":1629624968000,"images":[{"uid":"019a82199d9a43ca8781c3737567954e","name":"tesetT.JPG","inferResult":"SUCCESS","message":"SUCCESS","matchedTemplate":{"id":10242,"name":"계산서1"},"validationResult":{"result":"NO_REQUESTED"},"title":{"name":"계산서1","bounding":{"top":48.0,"left":149.0,"width":248.0,"height":68.0},"inferText":"계 산 서","inferConfidence":0.99959993},"fields":[{"name":"supplier_business_no","bounding":{"top":129.0,"left":231.0,"width":240.0,"height":15.0},"valueType":"ALL","inferText":"807-93-00062","inferConfidence":0.9938},{"name":"store_name","bounding":{"top":163.0,"left":227.0,"width":115.0,"height":40.0},"valueType":"ALL","inferText":"유창수산","inferConfidence":0.9999},{"name":"receipt_date","bounding":{"top":330.0,"left":90.0,"width":183.0,"height":43.0},"valueType":"ALL","inferText":"2021 630","inferConfidence":0.99004996},{"name":"amount","bounding":{"top":588.0,"left":77.0,"width":134.0,"height":34.0},"valueType":"ALL","inferText":"01.100 0~","inferConfidence":0.8885}]}]}

			JsonNode images = root.path("images");
			
			System.out.println("images : " + images);
//			[{"uid":"019a82199d9a43ca8781c3737567954e","name":"tesetT.JPG","inferResult":"SUCCESS","message":"SUCCESS","matchedTemplate":{"id":10242,"name":"계산서1"},"validationResult":{"result":"NO_REQUESTED"},"title":{"name":"계산서1","bounding":{"top":48.0,"left":149.0,"width":248.0,"height":68.0},"inferText":"계 산 서","inferConfidence":0.99959993},"fields":[{"name":"supplier_business_no","bounding":{"top":129.0,"left":231.0,"width":240.0,"height":15.0},"valueType":"ALL","inferText":"807-93-00062","inferConfidence":0.9938},{"name":"store_name","bounding":{"top":163.0,"left":227.0,"width":115.0,"height":40.0},"valueType":"ALL","inferText":"유창수산","inferConfidence":0.9999},{"name":"receipt_date","bounding":{"top":330.0,"left":90.0,"width":183.0,"height":43.0},"valueType":"ALL","inferText":"2021 630","inferConfidence":0.99004996},{"name":"amount","bounding":{"top":588.0,"left":77.0,"width":134.0,"height":34.0},"valueType":"ALL","inferText":"01.100 0~","inferConfidence":0.8885}]}]
			
			System.out.println("fields : " + images.get(0).path("fields") );
			
			// 필드이름이 fields인 것에서 inferText 이름을 가진 값을 찾음
//			List<String> inferTextList = images.get(0).path("fields").findValuesAsText("inferText");
			
			List<String> nameList = images.get(0).path("fields").findValuesAsText("name");
			List<String> inferTextList = images.get(0).path("fields").findValuesAsText("inferText");
			
			Map<String, String> map = new HashMap<String, String>();
			
			for(int i=0; i< nameList.size(); i++) {
				map.put(nameList.get(i), inferTextList.get(i));
			}
			
			System.out.println("맵으로 저장한 값 : " + map);
//			{amount=01.100 0~, supplier_business_no=807-93-00062, receipt_date=2021 630, store_name=유창수산}

			System.out.println("amount : " + map.get("amount"));
			System.out.println("supplier_business_no : " + map.get("supplier_business_no"));
			System.out.println("receipt_date : " + map.get("receipt_date"));
			System.out.println("store_name : " + map.get("store_name"));
			
			
			mav.addObject("map", map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		
	}
}



