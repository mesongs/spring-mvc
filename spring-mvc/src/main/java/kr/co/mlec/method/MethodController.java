package kr.co.mlec.method;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//여기로 오는 모든 메소드들은 ~ => 밑에 각각에 붙은 value 생략 가능
@RequestMapping(value="/method/method.do" )
@Controller
public class MethodController {
	
//	@GetMapping("/method/method.do")
	@RequestMapping(method=RequestMethod.GET)
	// a태그를 통해 들어오면 GET방식, form방식은 POST
	// 리턴값을 String으로 쓰면, 포워드시킬 것만 날릴 거네?
	// 스프링이 이미 알고있다!, 매우 유연한 스프링의 디스패쳐
	// 굳이 ModelAndView일 필요없음
	public String callGet(){
		return "method/methodForm";
		// 포워드하는 실제 주소 => WEB-INF/jsp/method/methodForm.jsp
	}

//  @PostMapping("/method/method/do")
	@RequestMapping(method=RequestMethod.POST)
	public String callPost(){
		
		// POST방식으로 실행된 것은 이 jsp를 포워드함
		return "method/methodProcess";
	}
		
		
		
	
}
