package petserve.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
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
import petserve.ui.product.DlgAddPdtIfm;
import petserve.ui.product.DlgAddPdtType;
import petserve.ui.product.DlgCgTypeDetail;
import petserve.ui.product.DlgCgTypeName;
import petserve.ui.user.DlgChangeEmail;
import petserve.ui.user.DlgChangeOthContact;
import petserve.ui.user.DlgChangePassword;
import petserve.ui.user.DlgChangePhone;
import petserve.ui.user.DlgChangeUserName;
import petserve.ui.user.DlgCurrentUserInformation;
import petserve.util.BaseException;

public class FrmManagerMain extends JFrame implements ActionListener{
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu userMenu = new JMenu("菜单");
	private JMenu buyMenu = new JMenu("商品及服务");

	private JMenuItem userMenuItem_1 = new JMenuItem("用户信息管理");
	private JMenuItem userMenuItem_2 = new JMenuItem("宠物信息管理");
	private JMenuItem userMenuItem_3 = new JMenuItem("预约信息管理");
	private JMenuItem userMenuItem_4 = new JMenuItem("订单信息管理");

//	private JMenuItem buyMenuItem_1 = new JMenuItem("查看商品");
//	private JMenuItem buyMenuItem_2 = new JMenuItem("查看服务");
	
	private JButton slDetail = new JButton("查看商品条目");
	private JButton addType = new JButton("新增商品类型");
	private JButton addDetail = new JButton("新增商品条目");
	private JButton cgTypeName = new JButton("更改商品类型名称");
	private JButton cgTypeDsc = new JButton("更改商品类型描述");
	private JButton cgPdtName = new JButton("更改商品名");
	private JButton cgBrand = new JButton("更改品牌");
	private JButton cgPrice = new JButton("更改售价");
	private JButton cgPdtCode = new JButton("更改商品条码");
	private JButton deleteType = new JButton("删除商品类别");
	private JButton deleteDetail = new JButton("删除商品条目");
	
	
	private JPanel titlePane = new JPanel();
//	private JPanel workPane = new JPanel();
	private JPanel toolbar = new JPanel();
	
	private JLabel titleLabel = new JLabel("宠物服务系统（管理员）");
	
	private Object tblPdtTypeTitle[]=BeanProducts_types.tblPdtTypeTitle;
	private Object tblPdtTypeData[][];
	DefaultTableModel tabPdtTypeModel=new DefaultTableModel();
	public JTable dataTablePdtType=new JTable(tabPdtTypeModel);
	
	private Object tblPdtIfmTitle[]=BeanProducts_information.tblPdtIfmTitle;
	private Object tblPdtIfmData[][];
	DefaultTableModel tabPdtIfmModel=new DefaultTableModel();
	private JTable dataTablePdtIfm=new JTable(tabPdtIfmModel);
	
	public List<BeanProducts_types> allPdtType = null;
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
	
	public void reloadPdtIfmTable(int positionid) {
		try {
			allPdtIfm = PetUtil.productInformationManager.loadProductInformation(allPdtType.get(positionid).getType_code());
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
	
	public FrmManagerMain() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("宠物服务系统");	
		setJMenuBar(menuBar);
		this.menuBar.add(userMenu);
		this.menuBar.add(buyMenu);
		this.userMenu.add(userMenuItem_1);
		this.userMenu.add(userMenuItem_2);
		this.userMenu.add(userMenuItem_3);
		this.userMenu.add(userMenuItem_4);
		
		this.toolbar.add(slDetail);
		this.toolbar.add(addType);
		this.toolbar.add(cgTypeName);
		this.toolbar.add(cgTypeDsc);
		this.toolbar.add(addDetail);
		this.toolbar.add(cgPdtName);
		this.toolbar.add(cgBrand);
		this.toolbar.add(cgPrice);
		this.toolbar.add(cgPdtCode);
		this.toolbar.add(deleteType);
		this.toolbar.add(deleteDetail);
		this.getContentPane().add(toolbar, BorderLayout.SOUTH);
		
		this.titlePane.add(titleLabel);
		this.getContentPane().add(titlePane, BorderLayout.NORTH);
		
		this.getContentPane().add(new JScrollPane(this.dataTablePdtType), BorderLayout.WEST);
	    this.dataTablePdtType.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmManagerMain.this.dataTablePdtType.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmManagerMain.this.reloadPdtIfmTable(i);
			}
	    	
	    });

		this.getContentPane().add(new JScrollPane(this.dataTablePdtIfm), BorderLayout.EAST);
	    
	    this.reloadPdtTypeTable();
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
		this.slDetail.addActionListener(this);
		this.addType.addActionListener(this);
		this.cgTypeName.addActionListener(this);
		this.cgTypeDsc.addActionListener(this);
		this.addDetail.addActionListener(this);
		this.cgPdtName.addActionListener(this);
		this.cgBrand.addActionListener(this);
		this.cgPrice.addActionListener(this);
		this.cgPdtCode.addActionListener(this);
		this.deleteType.addActionListener(this);
		this.deleteDetail.addActionListener(this);
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
		else if(e.getSource() == this.slDetail) {
			
		}
		else if(e.getSource() == this.addType) {
			DlgAddPdtType dlgAddPdtType = new DlgAddPdtType();
			dlgAddPdtType.setVisible(true);
		}
		else if(e.getSource() == this.cgTypeName) {
			int i;
			try {
				i = DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择要修改的类别");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "请选择要修改的类别",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgCgTypeName dlgCgTypeName = new DlgCgTypeName();
			dlgCgTypeName.setVisible(true);
		}
		else if(e.getSource() == this.cgTypeDsc) {
			int i;
			try {
				i = DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择要修改的类别");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "请选择要修改的类别",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgCgTypeDetail dlgCgTypeDetail = new DlgCgTypeDetail();
			dlgCgTypeDetail.setVisible(true);
		}
		else if(e.getSource() == this.addDetail) {
			int i;
			try {
				i = DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择商品所属类别");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "请选择商品所属类别",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgAddPdtIfm dlgAddPdtIfm = new DlgAddPdtIfm();
			dlgAddPdtIfm.setVisible(true);
		}
		else if(e.getSource() == this.cgPdtName) {
			
		}
		else if(e.getSource() == this.cgBrand) {
			
		}
		else if(e.getSource() == this.cgPrice) {
			
		}
		else if(e.getSource() == this.cgPdtCode) {
			
		}
		else if(e.getSource() == this.deleteType) {
			try {
				int i = DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择商品所属类别");
				PetUtil.productTypeManager.deletePdtType(allPdtType.get(i).getType_code());
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			DlgLogin.frmManagerMain.reloadPdtTypeTable();
			
		}
		else if(e.getSource() == this.deleteDetail) {
			int i;
			try {
				i = DlgLogin.frmManagerMain.dataTablePdtIfm.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择商品条目");
				PetUtil.productInformationManager.deletePdtIfm(allPdtIfm.get(i).getProduct_id());
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow() >= 0)
				DlgLogin.frmManagerMain.reloadPdtIfmTable(DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow());
		}
	}

}
