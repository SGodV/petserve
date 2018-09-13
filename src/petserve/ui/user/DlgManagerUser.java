package petserve.ui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import petserve.model.BeanUser_information;

public class DlgManagerUser extends JDialog implements ActionListener {
	private JLabel lable = new JLabel("请选择查询方式");
	private JTextField jTextField = new JTextField(20);
	
	private Object tblUserTitle[]=BeanUser_information.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	public JTable dataUserType=new JTable(tabUserModel);
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
