package kr.co.mlec.form;

public class MemberVO {
	
	// 컨트롤러에서 MemberVO에 바로 set을 한 채로 값을 들고 올 수 있음
	// form에서 넘어오는 파라미터의 name과 VO의 멤버변수와 동일해야함
	
	private String id;
	private String password;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + "]";
	}
	
	
}
