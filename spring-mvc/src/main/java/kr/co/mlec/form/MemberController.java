package kr.co.mlec.form;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//@RequestMapping("/join.do") // /form/join.do
//
//@RequestMapping("/sign.do") // /form/sign.do
// 이렇게 공통적으로 사용되는 uri가 있다면 Controller 위에 붙일 수 있음!

@Controller
@RequestMapping("/form")
public class MemberController {
	
	
	// spring-mvc/form/joinForm.do 이런 형식임  
	@RequestMapping("/joinForm.do")
	public String joinForm() {
		
			return "form/joinForm";
	}
	
//	@RequestMapping("/join.do")
//	public String join(HttpServletRequest request) {
//		
//		// 날라온 인자를 받으려면.. rquest가 필요한데.. 인자를 그냥 써주면됨
//		// 스프링이 인자에 맞춰서 파라미터를 넘겨준다!
//		// 리턴타입도 내 마음대로 할 수 있고~
//		
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		
//		
//		MemberVO member = new MemberVO();
//		member.setId(id);
//		member.setPassword(password);
//		member.setName(name);
//		
//		//System.out.println(member);
//		
//		// request 객체를 알고있으니까 굳이 ModelAndView안써도됨
//		request.setAttribute("member", member);
//		
//		
//		return "form/memberInfo";
//	}
	
	
	//위에꺼 복사해서 변경
	// 디스패처 서블릿이 컨트롤러 호출하면서 name이 id, password, name인 인자를 전달함
	// 그럼 넘어오는 파라미터가 많으면 계속 넣어줘야 하는데?..
//	@RequestMapping("/join.do")
//	public String join(@RequestParam("id") String id, 
//					   @RequestParam("password") String password, 
//					   @RequestParam("name") String name,
//					   HttpServletRequest request) {
//		
//		
//		MemberVO member = new MemberVO();
//		member.setId(id);
//		member.setPassword(password);
//		member.setName(name);
//		
//		System.out.println(member);
//		
//		//공유영역에 올려야하는데 requestParam하면, reqeust객체 모르는데?
//		// 달라고 하면됨!
//		
//		request.setAttribute("member", member);
//		
//		
//		return "form/memberInfo";
//	}
	
	
	// RequestParam이 10개이면? 다 써주기 귀찮잖아
	// 가져온 데이터를 MemberVO 객체에 넣어주고 싶다
	// MemberVO의 멤버변수에 해당하는 값 name이 id, password, name인 것을 넣어줌!
//	@RequestMapping("/join.do")
//	public String join(@ModelAttribute("member") MemberVO member) {
//		
//		System.out.println(member);
//		
//		// MemberVO 클래스 첫 글자를 소문자로 만들어서
//		// "memberVO"라는 이름으로 등록시킨다
//		// 그러면.. memberInfo.jsp에 memberVO라는 이름으로 올려야함, ${memberVO.id}
//		// 엄청 간결해졌다!!
//		// request.setAttribute("memberVO", member);
//		// 공유영역에 따로 등록할 필요가없다!
//		// 근데, 내가 원하는 이름으로 공유영역에 등록시키고 싶다
//		// @ModelAttribute "member"이렇게 하면됨
//		
//		// 날아오는 파라미터의 name과 VO의 멤버변수와 동일해야 setter로 들어감
//		return "form/memberInfo";
//	}
	
	//ModelAndView
//	@RequestMapping("/join.do")
//	public String join(MemberVO member) {
//		
//		
//		ModelAndView mav = new ModelAndView("form/memberInfo");
//		
//		mav.setViewName("form/memberInfo"); //나 포워드 시킬꺼야 이 주소값으로
//		mav.addObject("member", member); //공유영역 등록
//		
//		
//		return "form/memberInfo";
//	}
//	
	
	//*공유영역에 등록하는 방법 3가지
	//직접 RequestParam
	//ModelAndView 객체에 등록해서 return
	//아예 Model 객체 받아와서 모델에 등록시킬 객체를 넣음
	
	// 나 공유영역에 등록시키기 위한 ModelAndView의 Model객체 좀 줘
	// Model 객체를 받아와서 공유영역에 등록시킬 수 있다!
	@RequestMapping("/join.do")
	public String join(MemberVO member, Model model) {
		
		// 공유영역에 등록시키는 방법이 1개만 있는게 아니다
		model.addAttribute("member", member);
		
		
		// 리다이렉트를 쓰면, request 공유영역 등록이 의미없음!
		return "form/memberInfo";
	}
	
	/*
	 * MemberVO의 멤버변수가 id / password /name 
	 * 그런데, 이 컨트롤러를 실행시키는 jsp에서 넘어오는 값들 name도 모두 name="id" name="password", name="name"
	 * 이렇게 되어 있어서 바로 MemberVO member해서 받아올 수 있다
	 */
}
