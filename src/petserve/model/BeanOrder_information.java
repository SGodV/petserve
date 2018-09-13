package petserve.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BeanOrder_information {
	private int user_id;
	private int order_id;
	private int product_id;
	private Date begin_time;
	private Date end_time;
	private int quantity;
	private float order_price;
	private int transfer_state;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getOrder_price() {
		return order_price;
	}
	public void setOrder_price(float order_price) {
		this.order_price = order_price;
	}
	public int getTransfer_state() {
		return transfer_state;
	}
	public void setTransfer_state(int transfer_state) {
		this.transfer_state = transfer_state;
	}
	public static final String[] tblOrderTitle={"订单编号","商品编号","开始时间","完成时间","购买数量","订单价格","订单状态"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.order_id);
		else if(col==1)
			return String.valueOf(this.product_id);
		else if(col==2)
			return sdf.format(this.begin_time);
		else if(col==3)
			return sdf.format(this.end_time);
		if(col==4)
			return String.valueOf(this.quantity);
		else if(col==5)
			return String.valueOf(this.order_price);
		else if(col==6) {
			if(transfer_state==0)
				return "进行中";
			else if(transfer_state==1)
				return "已完成";
			else return "";
		}
		else return "";
	}
}
