package kr.co.mlec.ocr;

import java.util.HashMap;
import java.util.List;
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
public class OcrControllerJackson3_success {
	
	@RequestMapping("/hello/hello5.do")
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
			
			System.out.println("api로 받아온 값 String : " + result);
             
			// ObjectMapper를 이용하면 JSON을 Java 객체로 변환할 수 있고, 
			// 반대로 Java 객체를 JSON 객체로 serialization 할 수 있음
			ObjectMapper mapper = new ObjectMapper();
			
					
			// tree형태로 읽어준다, 안에 어떤 값들이 있는지 아는 방법은?
			// JsonNode로 파싱하기 위해 mapper.readTree 사용
			JsonNode root = mapper.readTree(result);
			
			
			
			System.out.println("여기서부터 시작" + root);
//			{"version":"V1","requestId":"test2","timestamp":1629624968000,"images":[{"uid":"019a82199d9a43ca8781c3737567954e","name":"tesetT.JPG","inferResult":"SUCCESS","message":"SUCCESS","matchedTemplate":{"id":10242,"name":"계산서1"},"validationResult":{"result":"NO_REQUESTED"},"title":{"name":"계산서1","bounding":{"top":48.0,"left":149.0,"width":248.0,"height":68.0},"inferText":"계 산 서","inferConfidence":0.99959993},"fields":[{"name":"supplier_business_no","bounding":{"top":129.0,"left":231.0,"width":240.0,"height":15.0},"valueType":"ALL","inferText":"807-93-00062","inferConfidence":0.9938},{"name":"store_name","bounding":{"top":163.0,"left":227.0,"width":115.0,"height":40.0},"valueType":"ALL","inferText":"유창수산","inferConfidence":0.9999},{"name":"receipt_date","bounding":{"top":330.0,"left":90.0,"width":183.0,"height":43.0},"valueType":"ALL","inferText":"2021 630","inferConfidence":0.99004996},{"name":"amount","bounding":{"top":588.0,"left":77.0,"width":134.0,"height":34.0},"valueType":"ALL","inferText":"01.100 0~","inferConfidence":0.8885}]}]}

			
			JsonNode images = root.path("images");
			
			// 여기 안에 있는 fields라는 값을 다 찾는다? 이상한데..
			// List<String> imageList = root.path("images").findValuesAsText("fields");
			
			// 이 안에 들어있는 값 중 fileds를 찾는다
			System.out.println("images : " + images);
			
			// 배열이라 이렇게 get(0)으로 해야 접근이 되네
//			images.get(0).path("fields");
			
//			JsonNode fields = images.path("fields");
//			images : [{"uid":"9593c4e60fdd4494b5db45da68765a6f","name":"tesetT.JPG","inferResult":"SUCCESS","message":"SUCCESS","matchedTemplate":{"id":10242,"name":"계산서1"},"validationResult":{"result":"NO_REQUESTED"},"title":{"name":"계산서1","bounding":{"top":48.0,"left":149.0,"width":248.0,"height":68.0},"inferText":"계 산 서","inferConfidence":0.99959993},"fields":[{"name":"supplier_business_no","bounding":{"top":129.0,"left":231.0,"width":240.0,"height":15.0},"valueType":"ALL","inferText":"807-93-00062","inferConfidence":0.9938},{"name":"store_name","bounding":{"top":163.0,"left":227.0,"width":115.0,"height":40.0},"valueType":"ALL","inferText":"유창수산","inferConfidence":0.9999},{"name":"receipt_date","bounding":{"top":330.0,"left":90.0,"width":183.0,"height":43.0},"valueType":"ALL","inferText":"2021 630","inferConfidence":0.99004996},{"name":"amount","bounding":{"top":588.0,"left":77.0,"width":134.0,"height":34.0},"valueType":"ALL","inferText":"01.100 0~","inferConfidence":0.8885}]}]
			
//			System.out.println("fields : " + images.get(0).path("fields"));
//			fields : [{"name":"supplier_business_no","bounding":{"top":129.0,"left":231.0,"width":240.0,"height":15.0},"valueType":"ALL","inferText":"807-93-00062","inferConfidence":0.9938},{"name":"store_name","bounding":{"top":163.0,"left":227.0,"width":115.0,"height":40.0},"valueType":"ALL","inferText":"유창수산","inferConfidence":0.9999},{"name":"receipt_date","bounding":{"top":330.0,"left":90.0,"width":183.0,"height":43.0},"valueType":"ALL","inferText":"2021 630","inferConfidence":0.99004996},{"name":"amount","bounding":{"top":588.0,"left":77.0,"width":134.0,"height":34.0},"valueType":"ALL","inferText":"01.100 0~","inferConfidence":0.8885}]
			
			
			List<String> nameList = images.get(0).path("fields").findValuesAsText("inferText");
			
			System.out.println("그냥 출력 : " + nameList);
			System.out.println("list크기 : " + nameList.size());
			
			System.out.println("사업자 번호 : " + nameList.get(0));
			System.out.println("상호명 : " + nameList.get(1));
			System.out.println("영수일시 : " + nameList.get(2));
			System.out.println("영수금액 : " + nameList.get(3));
			
			ocrVO ocr = new ocrVO();
			
			ocr.setSupplierBusinessNo(nameList.get(0));
			ocr.setStoreName(nameList.get(1));
			ocr.setReceiptDate(nameList.get(2));
			ocr.setAmount(nameList.get(3));
			
			// map으로 key : value 형태로 저장하자
			// 들어가는 순서가 상관없도록, api 호출해서 데이터를 가져오는 순서가 달라지면, list보다는 map으로 해야할듯
			
			// 어떤 순서로 들어가든 상관없이 key : value 형태로 저장 => 꺼내서 쓸 때, key호출로 바로 사용
			
			mav.addObject("ocr",ocr);
			
//			mav.addObject("result", result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		
	}
}



