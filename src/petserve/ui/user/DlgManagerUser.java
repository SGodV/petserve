package petserve.ui.user;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import petserve.action.PetUtil;
import petserve.control.PetManager;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.ui.FrmManagerMain;
import petserve.ui.pet.DlgManagerPet;
import petserve.util.BaseException;

public class DlgManagerUser extends JDialog implements ActionListener {
	private JLabel lable = new JLabel("请输入用户名：");
	private JTextField jTextField = new JTextField(20);
	
	private JButton userButton = new JButton("查询用户");
	private JButton petButton = new JButton("查询用户宠物信息");
	private JButton orderButton = new JButton("查询用户订单信息");
	private JButton appoimentButton = new JButton("查询用户预约单信息");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblUserTitle[]=BeanUser_information.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	public JTable dataUserType=new JTable(tabUserModel);
	
	public List<BeanUser_information> allUser = null;
	public static DlgManagerPet dlgManagerPet;
	public void reloadPdtTypeTable() {
		try {
			allUser = PetUtil.userManager.loadUser();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[allUser.size()][BeanProducts_types.tblPdtTypeTitle.length];
		for(int i=0;i<allUser.size();i++){
			for(int j=0;j<BeanProducts_types.tblPdtTypeTitle.length;j++)
				tblUserData[i][j]=allUser.get(i).getCell(j);
		}
		tabUserModel.setDataVector(tblUserData,tblUserTitle);
		this.dataUserType.validate();
		this.dataUserType.repaint();
	}
	public DlgManagerUser() {
		this.setTitle("用户管理");
		this.workPanel.add(lable);
		this.workPanel.add(jTextField);
		this.workPanel.add(userButton);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(petButton);
		this.toolBar.add(orderButton);
		this.toolBar.add(appoimentButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataUserType), BorderLayout.CENTER);
		
		this.reloadPdtTypeTable();
		this.setSize(1600, 900);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		 
		this.validate();
		
		this.userButton.addActionListener(this);
		this.petButton.addActionListener(this);
		this.orderButton.addActionListener(this);
		this.appoimentButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == userButton) {
			try {
				allUser = PetUtil.userManager.selectUser(this.jTextField.getText());
				this.reloadPdtTypeTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == petButton) {
				try {
					if(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow() < 0)
						throw new BaseException("请选择用户");
					dlgManagerPet = new DlgManagerPet();
					dlgManagerPet.setVisible(true);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		else if(e.getSource() == orderButton) {
			try {
				if(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow() < 0)
					throw new BaseException("请选择用户");
				//new PetManager();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == appoimentButton) {
			try {
				if(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow() < 0)
					throw new BaseException("请选择用户");
				//new PetManager();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
