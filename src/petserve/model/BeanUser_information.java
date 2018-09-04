package petserve.model;

public class BeanUser_information {
	public static BeanUser_information currentLoginUser = null;
	private int user_id;
	private int phone_number;
	private String passward;
	private String user_name;
	private String email;
	private String other_contact;
	private short authority;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassward() {
		return passward;
	}
	public void setPassward(String passward) {
		this.passward = passward;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOther_contact() {
		return other_contact;
	}
	public void setOther_contact(String other_contact) {
		this.other_contact = other_contact;
	}
	public short getAuthority() {
		return authority;
	}
	public void setAuthority(short authority) {
		this.authority = authority;
	}
}
