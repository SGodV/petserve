package petserve.model;

import java.text.SimpleDateFormat;

public class BeanProducts_information {
	private int type_code;
	private int product_id;
	private String product_name;
	private String brand;
	private float retail_price;
	private String product_code;
	private float price;
	

	public int getType_code() {
		return type_code;
	}
	public void setType_code(int type_code) {
		this.type_code = type_code;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getRetail_price() {
		return retail_price;
	}
	public void setRetail_price(float retail_price) {
		this.retail_price = retail_price;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public static final String[] tblPdtIfmTitle={"编号","名称","品牌","定价","条码","售价"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.product_id);
		else if(col==1)
			return this.product_name;
		else if(col==2)
			return this.brand;
		else if(col==3)
			return String.valueOf(this.retail_price);
		else if(col==4)
			return this.product_code;
		else if(col==5)
			return String.valueOf(this.price);
		else
			return "";
	}
}
