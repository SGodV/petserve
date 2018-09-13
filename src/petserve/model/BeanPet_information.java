package petserve.model;

import java.text.SimpleDateFormat;

public class BeanPet_information {
	private int user_id;
	private int pet_id;
	private String pet_name;
	private String name;
	private byte[] picture;
	private short appointment_state;
	private short age;
	private short healthy;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public short isAppointment_state() {
		return appointment_state;
	}
	public void setAppointment_state(short appointment_state) {
		this.appointment_state = appointment_state;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public short getHealthy() {
		return healthy;
	}
	public void setHealthy(short healthy) {
		this.healthy = healthy;
	}
	
	public static final String[] tblPetTitle={"宠物编号","宠物名称","宠物学名","图片","预约状态","年龄","健康状态"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.pet_id);
		else if(col==1)
			return this.pet_name;
		else if(col==2)
			return this.name;
	 	else if(col==3)
	 		return String.valueOf(picture);
		else if(col==4)
			if (this.appointment_state == 0)
				return "健康";
			else if (this.appointment_state == 1)
				return "不健康";
			else
				return "";
		else if(col==5)
			return String.valueOf(this.age);
		else
			return "";
	}
}
