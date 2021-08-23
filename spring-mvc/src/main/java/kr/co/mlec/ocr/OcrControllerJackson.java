package kr.co.mlec.ocr;

import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Controller
public class OcrControllerJackson {
	
	@RequestMapping("/hello/hello4.do")
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
			
             
			
			ObjectMapper mapper = new ObjectMapper();
			// 이미 받아온 결과 result가 String형인데 할 필요있나? 형태가 달라지긴함 
			// writeValueAsString 이거는 자바객체, map을 문자열로 변경할 때 사용하는거니까 사용할 필요없음
			// String proResult = mapper.writeValueAsString(result);
			
			// 받은 값을 자바객체로 변경해서 이것을 공유영역에 저장하고싶다, 이렇게 편하게 할 수 있는 것 같은데
			// 그전에 필드에 있는 데이터만 뽑아내야함 => OCRVO ocr = mapper.readValue(필드데이터, OCRVO.class)
			// Board board = mapper.readValue("{\"title\":\"제목1\",\"content\":\"내용1\"}", Board.class);
			
			// key : value값으로 나오나?, jsonArray(배열도 포함되어있어서 안되는 듯)
//			System.out.println( "key : value값으로 나오나? : "  +map);
			
						
			//tree형태로 읽어준다, 안에 어떤 값들이 있는지 아는 방법은?
			JsonNode root = mapper.readTree(result);
			
			// 안에 있는 배열을 뽑아내는 것
	        ArrayNode array = (ArrayNode) root.get("images");
	        
	        // fields값 뽑아내기 성공 => [] 배열로 되어있어서, get(0)으로 접근한 뒤, 다시 필드이름으로 접근해야함
	        System.out.println("원하는 값 :" + array.get(0).get("fields"));
	        
	        // 반환값이 jsonNode
	        JsonNode node = array.get(0).get("fields");
	        
	        // 이렇게 해야 필드이름으로 접근할 수 있음, 안에 있는 값을 배열로 사용하고 싶은데 => 각각의 name과 inferText를 뽑아내면 됨
	        System.out.println("이 값들을 배열로 변환하면 될 것 같은데.. : " + node.get(0));
	        
	        // ArrayNode아래에 있는 값에 접근하는 법을 알아야함     
	        
			
			mav.addObject("result", result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		
	}
}



