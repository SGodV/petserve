package petserve.model;

import java.text.SimpleDateFormat;

public class BeanProducts_types {
	private int type_code;
	private String type_name;
	private String type_describe;
	private short pdt_serve;
	
	public int getType_code() {
		return type_code;
	}
	public void setType_code(int type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getType_describe() {
		return type_describe;
	}
	public void setType_describe(String type_describe) {
		this.type_describe = type_describe;
	}
	public short getPdt_serve() {
		return pdt_serve;
	}
	public void setPdt_serve(short pdt_serve) {
		this.pdt_serve = pdt_serve;
	}

	public static final String[] tblPdtTypeTitle={"类别序号","类别名称","类别描述"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0)
			return String.valueOf(this.type_code);
		else if(col==1)
			return this.type_name;
		else if(col==2)
			return this.type_describe;
		else
			return "";
	}
}
