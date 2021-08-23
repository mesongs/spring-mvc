package kr.co.mlec.ocr;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Controller
public class OcrController2 {
	
	@RequestMapping("/hello/hello2.json")
	public ocrVO ocrTest() {
		
		ocrVO ocrResult = new ocrVO();
		// 값을 받아온 뒤 OCR VO에 setter로 값을 넣고 json형식으로 반환해주기
		
		OkHttpClient client = new OkHttpClient();

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
//            System.out.println(responseBody.string());
            String result = responseBody.string(); 
			
            System.out.println("rest-api로 들고온 값 그대로: " + result);
            
            ObjectMapper mapper = new ObjectMapper();
    		String mapperResult = mapper.writeValueAsString(result);
    		
    		System.out.println("mapper이용하면 어떻게 추출될까?" + mapperResult);
            
            
            // String형태를 json으로 변경한 뒤, 이것을 VO에 넣어주어야 하나?
            
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ocrResult;
		
	}
}
