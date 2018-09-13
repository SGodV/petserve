package petserve.model;

import java.text.SimpleDateFormat;

public class BeanUser_information {
	public static BeanUser_information currentLoginUser = null;
	private int user_id;
	private String phone_number;
	private String password;
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public static final String[] tblUserTitle={"用户序号","电话号码","用户昵称","用户邮箱","其他联系方式"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.user_id);
		else if(col==1)
			return this.phone_number;
		else if(col==2)
			return this.user_name;
		else if(col==3)
			return this.email;
		else if(col==4)
			return this.other_contact;
		else
			return "";
	}
}
