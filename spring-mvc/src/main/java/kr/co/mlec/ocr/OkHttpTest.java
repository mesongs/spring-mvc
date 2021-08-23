package kr.co.mlec.ocr;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpTest {

	public static void main(String[] args) {
		
		
		String fileName = "test.JPG";
		String apiURL = "https://55c7547061ee4d91891f615ee5e7c101.apigw.ntruss.com/custom/v1/10633/1ffc0406b9891fcd265a225e83a668fa045f1282588f80c8d11c029bad156d85/infer";
		String filePath = "C:\\Users\\HP\\git\\spring-mvc\\spring-mvc\\src\\main\\webapp\\resources\\img\\";
		String apiKey = "dFpna0dQcVlGT3JDb1hQY2hYZnlYTkRwTUtsd0lIbko=";
		
		File jpgFile = new File(filePath + fileName);
		
		System.out.println(jpgFile.getName());
		System.out.println(jpgFile.getPath());
		
		OkHttpClient client = new OkHttpClient();
		
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType,
				"{\n\t\"version\" : \"V1\","
				+ "\n\t\"requestId\" : \"test2\","
				+ "\n\t\"timestamp\" : 0,"
				+ "\n\t\"images\" :[{\n\t\t\n\t\t\"format\" : \"png\","
				+ "\n\t\t\"url\" : " + jpgFile
				+ ",\n\t\t\"name\" :" + fileName );
		
		
		Request request = new Request.Builder()
				.addHeader("content-type", "application/json")
				.addHeader("x-ocr-secret", apiKey)
				.addHeader("cache-control", "no-cache")
				.url(apiURL)
				.post(body)
				.build();
		
		try {
			
			Response response = client.newCall(request).execute();
			
			System.out.println(response);
			
			System.out.println("일단 요청은 가네?");
			
			ResponseBody responseBody = response.body();
			System.out.println(responseBody.string());	
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}



















