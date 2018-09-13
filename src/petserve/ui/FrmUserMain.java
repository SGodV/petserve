package petserve.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.action.PetUtil;
import petserve.model.BeanPet_information;
import petserve.model.BeanProducts_information;
import petserve.ui.pet.DlgAddPet;
import petserve.ui.pet.DlgChangePetAge;
import petserve.ui.pet.DlgChangePetName;
import petserve.ui.pet.DlgChangePetPicture;
import petserve.ui.pet.DlgDeletePet;
import petserve.ui.user.DlgChangeEmail;
import petserve.ui.user.DlgChangeOthContact;
import petserve.ui.user.DlgChangePassword;
import petserve.ui.user.DlgChangePhone;
import petserve.ui.user.DlgChangeUserName;
import petserve.ui.user.DlgCurrentUserInformation;
import petserve.util.BaseException;

public class FrmUserMain extends JFrame implements ActionListener{
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu userMenu = new JMenu("用户");
	private JMenu petMenu = new JMenu("宠物");
	private JMenu appointmentMenu = new JMenu("预约");
	private JMenu orderMenu = new JMenu("我的订单");
	private JMenu buyMenu = new JMenu("商品及服务");

	private JMenuItem userMenuItem_1 = new JMenuItem("我的信息");
	private JMenuItem userMenuItem_2 = new JMenuItem("修改用户昵称");
	private JMenuItem userMenuItem_3 = new JMenuItem("修改用户密码");
	private JMenuItem userMenuItem_4 = new JMenuItem("修改手机号码");
	private JMenuItem userMenuItem_5 = new JMenuItem("修改电子邮箱");
	private JMenuItem userMenuItem_6 = new JMenuItem("修改其他联系方式");
	
	private JMenuItem petMenuItem_1 = new JMenuItem("我的宠物");
	private JMenuItem petMenuItem_2 = new JMenuItem("新增宠物");
	private JMenuItem petMenuItem_3 = new JMenuItem("删除宠物");
	private JMenuItem petMenuItem_4 = new JMenuItem("修改宠物年龄");
	private JMenuItem petMenuItem_5 = new JMenuItem("修改宠物昵称");
	private JMenuItem petMenuItem_6 = new JMenuItem("修改宠物照片");
	
	private JMenuItem appointmentMenuItem_1 = new JMenuItem("新增预约");
	private JMenuItem appointmentMenuItem_2 = new JMenuItem("修改预约时间");
	private JMenuItem appointmentMenuItem_3 = new JMenuItem("预约信息查询");
	private JMenuItem appointmentMenuItem_4 = new JMenuItem("取消预约");
	
	private JMenuItem orderMenuItem_1 = new JMenuItem("未支付订单");
	private JMenuItem orderMenuItem_2 = new JMenuItem("进行中订单");
	private JMenuItem orderMenuItem_3 = new JMenuItem("已完成订单");
	private JMenuItem orderMenuItem_4 = new JMenuItem("修改商品种类");
	private JMenuItem orderMenuItem_5 = new JMenuItem("修改商品价格");

	private JMenuItem buyMenuItem_1 = new JMenuItem("查看商品");
	private JMenuItem buyMenuItem_2 = new JMenuItem("查看服务");
	
	
	private JPanel titlePane = new JPanel();
//	private JPanel workPane = new JPanel();
	private JPanel toolbar = new JPanel();
	
	private JLabel titleLabel = new JLabel("宠物服务系统（用户版）");
	
	private Object tblPdtTypeTitle[]=BeanProducts_types.tblPdtTypeTitle;
	private Object tblPdtTypeData[][];
	DefaultTableModel tabPdtTypeModel=new DefaultTableModel();
	private JTable dataTablePdtType=new JTable(tabPdtTypeModel);
	
	private Object tblPdtIfmTitle[]=BeanProducts_information.tblPdtIfmTitle;
	private Object tblPdtIfmData[][];
	DefaultTableModel tabPdtIfmModel=new DefaultTableModel();
	private JTable dataTablePdtIfm=new JTable(tabPdtIfmModel);
	
	List<BeanProducts_types> allPdtType = null;
	List<BeanProducts_information> allPdtIfm = null;
	public void reloadPdtTypeTable() {
		try {
			allPdtType = PetUtil.productTypeManager.loadProductType();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPdtTypeData =  new Object[allPdtType.size()][BeanProducts_types.tblPdtTypeTitle.length];
		for(int i=0;i<allPdtType.size();i++){
			for(int j=0;j<BeanProducts_types.tblPdtTypeTitle.length;j++)
				tblPdtTypeData[i][j]=allPdtType.get(i).getCell(j);
		}
		tabPdtTypeModel.setDataVector(tblPdtTypeData,tblPdtTypeTitle);
		this.dataTablePdtType.validate();
		this.dataTablePdtType.repaint();
	}
	
	public void reloadPdtIfmTable() {
		try {
			allPdtIfm = PetUtil.productInformationManager.loadProductInformation(1);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPdtIfmData =  new Object[allPdtIfm.size()][BeanProducts_information.tblPdtIfmTitle.length];
		for(int i=0;i<allPdtIfm.size();i++){
			for(int j=0;j<BeanProducts_information.tblPdtIfmTitle.length;j++)
				tblPdtIfmData[i][j]=allPdtIfm.get(i).getCell(j);
		}
		tabPdtIfmModel.setDataVector(tblPdtIfmData,tblPdtIfmTitle);
		this.dataTablePdtIfm.validate();
		this.dataTablePdtIfm.repaint();
	}
	
	public FrmUserMain() {
//		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("宠物服务系统");	
		setJMenuBar(menuBar);
		this.menuBar.add(userMenu);
		this.menuBar.add(petMenu);
		this.menuBar.add(appointmentMenu);
		this.menuBar.add(orderMenu);
		this.menuBar.add(buyMenu);
		this.userMenu.add(userMenuItem_1);
		this.userMenu.add(userMenuItem_2);
		this.userMenu.add(userMenuItem_3);
		this.userMenu.add(userMenuItem_4);
		this.userMenu.add(userMenuItem_5);
		this.userMenu.add(userMenuItem_6);	
		this.petMenu.add(petMenuItem_1);
		this.petMenu.add(petMenuItem_2);
		this.petMenu.add(petMenuItem_3);
		this.petMenu.add(petMenuItem_4);
		this.petMenu.add(petMenuItem_5);
		this.petMenu.add(petMenuItem_6);	
		this.appointmentMenu.add(appointmentMenuItem_1);
		this.appointmentMenu.add(appointmentMenuItem_2);
		this.appointmentMenu.add(appointmentMenuItem_3);
		this.appointmentMenu.add(appointmentMenuItem_4);
		this.orderMenu.add(orderMenuItem_1);
		this.orderMenu.add(orderMenuItem_2);
		this.orderMenu.add(orderMenuItem_3);
		this.orderMenu.add(orderMenuItem_4);
		this.orderMenu.add(orderMenuItem_5);
		this.buyMenu.add(buyMenuItem_1);
		this.buyMenu.add(buyMenuItem_2);
		
		this.titlePane.add(titleLabel);
		this.getContentPane().add(titlePane, BorderLayout.NORTH);
		
		this.getContentPane().add(new JScrollPane(this.dataTablePdtType), BorderLayout.WEST);
	    this.dataTablePdtType.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmUserMain.this.dataTablePdtType.getSelectedRow();
				if(i<0) {
					return;
				}
				//FrmUserMain.this.reloadPlanStepTabel(i);
			}
	    	
	    });
	    
	    this.getContentPane().add(new JScrollPane(this.dataTablePdtIfm), BorderLayout.EAST);
	    this.dataTablePdtIfm.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmUserMain.this.dataTablePdtIfm.getSelectedRow();
				if(i<0) {
					return;
				}
				//FrmUserMain.this.reloadPlanStepTabel(i);
			}
	    	
	    });
	    
	    
	    this.reloadPdtTypeTable();
		this.reloadPdtIfmTable();
		this.setSize(1600, 900);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		
		this.userMenuItem_1.addActionListener(this);
		this.userMenuItem_2.addActionListener(this);
		this.userMenuItem_3.addActionListener(this);
		this.userMenuItem_4.addActionListener(this);
		this.userMenuItem_5.addActionListener(this);
		this.userMenuItem_6.addActionListener(this);
		this.petMenuItem_1.addActionListener(this);
		this.petMenuItem_2.addActionListener(this);
		this.petMenuItem_3.addActionListener(this);
		this.petMenuItem_4.addActionListener(this);
		this.petMenuItem_5.addActionListener(this);
		this.petMenuItem_6.addActionListener(this);
		this.appointmentMenuItem_1.addActionListener(this);
		this.appointmentMenuItem_2.addActionListener(this);
		this.appointmentMenuItem_3.addActionListener(this);
		this.appointmentMenuItem_4.addActionListener(this);
		this.orderMenuItem_1.addActionListener(this);
		this.orderMenuItem_2.addActionListener(this);
		this.orderMenuItem_3.addActionListener(this);
		this.orderMenuItem_4.addActionListener(this);
		this.orderMenuItem_5.addActionListener(this);
		this.buyMenuItem_1.addActionListener(this);
		this.buyMenuItem_2.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.userMenuItem_1) {
			DlgCurrentUserInformation dlgCurrentUserInformation = new DlgCurrentUserInformation();
			dlgCurrentUserInformation.setVisible(true);
		}
		else if(e.getSource() == this.userMenuItem_2) {
			DlgChangeUserName dlgChangeUserName = new DlgChangeUserName();
			dlgChangeUserName.setVisible(true);
		}
		else if(e.getSource() == this.userMenuItem_3) {
			DlgChangePassword dlgChangePassword = new DlgChangePassword();
			dlgChangePassword.setVisible(true);
		}
		else if(e.getSource() == this.userMenuItem_4) {
			DlgChangePhone dlgChangePhone = new DlgChangePhone();
			dlgChangePhone.setVisible(true);
		}
		else if(e.getSource() == this.userMenuItem_5) {
			DlgChangeEmail dlgChangeEmail = new DlgChangeEmail();
			dlgChangeEmail.setVisible(true);
		}
		else if(e.getSource() == this.userMenuItem_6) {
			DlgChangeOthContact dlgChangeOthContact = new DlgChangeOthContact();
			dlgChangeOthContact.setVisible(true);
		}
		else if(e.getSource() == this.petMenuItem_1) {
			
		}
		else if(e.getSource() == this.petMenuItem_2)
			new DlgAddPet();
		else if(e.getSource() == this.petMenuItem_3)
			new DlgDeletePet();
		else if(e.getSource() == this.petMenuItem_4)
			new DlgChangePetAge();
		else if(e.getSource() == this.petMenuItem_5)
			new DlgChangePetName();
		else if(e.getSource() == this.petMenuItem_6)
			new DlgChangePetPicture();
		else if(e.getSource() == this.appointmentMenuItem_1)
			;
		else if(e.getSource() == this.appointmentMenuItem_2)
			;
		else if(e.getSource() == this.appointmentMenuItem_3)
			;
		else if(e.getSource() == this.appointmentMenuItem_4)
			;
		else if(e.getSource() == this.orderMenuItem_1)
			;
		else if(e.getSource() == this.orderMenuItem_2)
			;
		else if(e.getSource() == this.orderMenuItem_3)
			;
		else if(e.getSource() == this.orderMenuItem_4)
			;
		else if(e.getSource() == this.orderMenuItem_5)
			;
	}

}
