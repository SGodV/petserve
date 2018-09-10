package petserve.model;

import java.sql.Date;

public class BeanAppointment {
	private int appointment_id;
	private int pet_id;
	private int product_id;
	private Date order_begin_time;
	private Date order_end_time;
	private String circumstance;
	
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
	
}
