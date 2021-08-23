package kr.co.mlec.ocr;

import java.sql.Array;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
public class OcrController {
	
	@RequestMapping("/hello/hello2.do")
	public ModelAndView ocrTest() {
		
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
			
            JSONParser jsonParser = new JSONParser();

            
            // 문자열을 JSONObject 객체로 변환시키는 jsonParser 
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            
//          java.lang.ClassCastException: class java.lang.String cannot be cast to class org.json.simple.JSONObject
//          requestId는 String형이라 JSONObject형태로 받을 수 없음
//          JSONObject requestId = (JSONObject)jsonObject.get("requestId");
            
//          JSONObject형으로 받아서 key : value로 접근하려했는데..
            
//          String으로 받으니 잘됨
//          String requestId = (String)jsonObject.get("requestId");
            
            
            // 받아온 값(String형)을 jsonObject형으로 변환했음 => 이 안에 있는 요소 중 images안에 있는 값을 가공하고 싶음
            // images값을 JSONArray로 받아오는데 성공했음 => 여기서 fields에 해당하는 값만 다시 가져오싶음
            
            
            JSONArray images = (JSONArray)jsonObject.get("images");
                        
            
            // images.get(0)으로 전체 출력됨
            
			mav.addObject("result", images);
			
			// images.get(0)안에 있는 필드 배열을 가져와야함
			
			
			

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		
	}
}



