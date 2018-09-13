package petserve.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanAppointment {
	private int appointment_id;
	private int pet_id;
	private int product_id;
	private Date order_begin_time;
	private Date order_end_time;
	private String circumstance;
	private int user_id;
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Date getOrder_begin_time() {
		return order_begin_time;
	}
	public void setOrder_begin_time(Date order_begin_time) {
		this.order_begin_time = order_begin_time;
	}
	public Date getOrder_end_time() {
		return order_end_time;
	}
	public void setOrder_end_time(Date order_end_time) {
		this.order_end_time = order_end_time;
	}
	public String getCircumstance() {
		return circumstance;
	}
	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public static final String[] tblAppointmentTitle={"预约编号","宠物编号","开始时间","完成时间","状态"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.appointment_id);
		else if(col==1)
			return String.valueOf(this.pet_id);
		else if(col==2)
			return sdf.format(this.order_begin_time);
		else if(col==3)
			return sdf.format(this.order_end_time);
		else if(col==4)
			return this.circumstance;
		else return "";
	}
}
