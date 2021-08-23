package kr.co.mlec.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.mlec.form.MemberVO;

// ResBodyController와 차이나는 부분 @ResponseBody 모두 삭제
// 이렇게 해놓으면 처음부터 '메세지 기반'이 된 것이다 RestController라고 붙여주기만 하면됨
@RestController
@RequestMapping("/ajax")
public class RestBodyController {
	
	@RequestMapping("/restBody.do")
	public String resStringBody() {
		
		//포워드 시킬 주소를 넘기는게 아니라, 단순 문자열을 넘기네!
		//근데 이런 문자열을 무조건 .jsp로 인식한다
		//request.setAttribute(msg, "ok, 성공");
		// 예전에는 이렇게 공유영역에 저장하고 .jsp에 표현식으로 나타냈음
		// @ResponseBody !! => 바로 응답을 시키도록한다!
		// 한글 인코딩, 처리를 하기 위해서 메세지 컨버터가 필요함
		// sping mvc.xml 수정 
		
		return "OK, 성공" ;
	}
	
	
	// 오잉 .do가 아니고, .json? xml에 mapping추가
	@RequestMapping("/restBody.json")
	public Map<String,String> resJsonBody(){
		
		Map<String, String> result = new HashMap<String,String>();
		result.put("id", "hong");
		result.put("name", "홍길동");
		result.put("addr", "서울");
		
		// map을 json형으로 변경해주어야함, 한글 인코딩도 해야함
		// spring-mvc로
		// map을 json으로 변경하기 위해 메이븐 jackson
		
		return result;
	}
	
	@RequestMapping("/restVOBody.json")
	public MemberVO resJsonVOBody() {
		
		MemberVO vo = new MemberVO();
		vo.setId("jb8049");
		vo.setName("park");
		vo.setPassword("1234");
		
		return vo;
	}
	
	@RequestMapping("/restStringListBody.json")
	public List<String> resJsonStringListBody(){
		
		List<String> list = new ArrayList<String>();
		
		for(int i = 1; i <= 4; i++) {
			
			list.add(String.valueOf(i));
		}
		
		//자바스크립트 배열로 변환해준 것
		
		return list;
		
		
	}
	
	//위와 마찬가지로 VO도
	@RequestMapping("/restVOListBody.json")
	@ResponseBody
	public List<MemberVO> resVOListBody() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		for(int i = 0; i < 4; i++) {
			
			MemberVO vo = new MemberVO();
			vo.setId("jb8049");
			vo.setName("park");
			vo.setPassword("1234");
			
			list.add(vo);
		}
		
		
		
		return list;
	}
	
	
	

}
