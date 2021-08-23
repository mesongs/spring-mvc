package kr.co.mlec.file;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/file")
@Controller
public class UploadController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public ModelAndView fileUpload(MultipartHttpServletRequest mRequest) throws Exception {
		
		// 내가 원하는 경로에 파일을 업로드할 수 있음
		//String file_path = "C://Users//HP//uploadFile//" ;
		
		// 실행되는 웹어플리케이션의 실제 경로 가져오기, 파일을 업로드하는 경우 저장되는 실제 위치
		String uploadDir = servletContext.getRealPath("/upload/");
		System.out.println("파일 경로 : " + uploadDir);
		
		
		// 내가 지정한 경로로 업로드하게 하기, 업로드한 파일 관리를 위해
//		String uploadDir2 = "C:\\Lecture\\spring-workspace\\newUpload\\";
//		System.out.println("파일 경로 : " + uploadDir2);
		
		
		ModelAndView mav = new ModelAndView("file/uploadResult");
		
		String id = mRequest.getParameter("id");
		System.out.println("id : " + id);
		
		// 첨부할 수 있는 파일이 2개 일 수도 있음
		Iterator<String> iter = mRequest.getFileNames();
		
		while(iter.hasNext()) {
			
			String formFileName = iter.next();
			
			// 폼에서 파일을 선택하지 않아도 객체 생성됨
			// 해당 파일을 파일 객체로
			MultipartFile mFile = mRequest.getFile(formFileName);
			
			// 원본 파일명
			String oriFileName = mFile.getOriginalFilename();
			System.out.println("원본 파일명 : " + oriFileName);
			
			if(oriFileName != null && !oriFileName.equals("")) {
			
				// 확장자 처리
				String ext = "";
				
				// 뒤쪽에 있는 . 의 위치 
				int index = oriFileName.lastIndexOf(".");
				
				// 위 조건 .이 없으면 -1
				if (index != -1) {
					// 파일명에서 확장자명(.포함)을 추출
					ext = oriFileName.substring(index);
				}
				
				// 파일 사이즈
				long fileSize = mFile.getSize();
				System.out.println("파일 사이즈 : " + fileSize);
				
				// 고유한 파일명 만들기, 여러 명의 사용자가 동일한 이름의 파일을 올리는 경우 구분해주어야함
				String saveFileName = "mlec-" + UUID.randomUUID().toString() + ext;
				System.out.println("저장할 파일명 : " + saveFileName);
			
				// 임시저장된 파일을 내가 만들어둔 원하는 경로에 저장
				mFile.transferTo(new File(uploadDir + saveFileName));
			} 
		} 
		return mav;
	}
	/*
	@RequestMapping("/uploadForm.do")
	public String upload() {
		
		return "file/fileUploadForm";
	}
	*/
}
