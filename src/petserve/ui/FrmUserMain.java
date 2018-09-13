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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.action.PetUtil;
import petserve.model.BeanPet_information;
import petserve.model.BeanProducts_information;
import petserve.ui.orderandappointment.DlgMyAppointment;
import petserve.ui.orderandappointment.DlgMyOrder;
import petserve.ui.orderandappointment.DlgSubmitOrder;
import petserve.ui.pet.DlgAddPet;
import petserve.ui.pet.DlgChangePetAgeM;
import petserve.ui.pet.DlgChangePetNameM;
import petserve.ui.pet.DlgChangePetPicture;
import petserve.ui.pet.DlgDeletePetU;
import petserve.ui.pet.DlgUserPet;
import petserve.ui.pet.DlgAppointmentPet;
import petserve.ui.product.DlgServeViewU;
import petserve.ui.user.DlgChangeEmail;
import petserve.ui.user.DlgChangeOthContact;
import petserve.ui.user.DlgChangePassword;
import petserve.ui.user.DlgChangePhone;
import petserve.ui.user.DlgChangeUserName;
import petserve.ui.user.DlgCurrentUserInformation;
import petserve.util.BaseException;

public class FrmUserMain extends JFrame implements ActionListener{
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu userMenu = new JMenu("�û�");
	private JMenu petMenu = new JMenu("�˵�");

	private JMenuItem userMenuItem_1 = new JMenuItem("�ҵ���Ϣ");
	private JMenuItem userMenuItem_2 = new JMenuItem("�޸��û��ǳ�");
	private JMenuItem userMenuItem_3 = new JMenuItem("�޸��û�����");
	private JMenuItem userMenuItem_4 = new JMenuItem("�޸��ֻ�����");
	private JMenuItem userMenuItem_5 = new JMenuItem("�޸ĵ�������");
	private JMenuItem userMenuItem_6 = new JMenuItem("�޸�������ϵ��ʽ");
	
	private JMenuItem petMenuItem_1 = new JMenuItem("�鿴����");
	private JMenuItem petMenuItem_2 = new JMenuItem("�ҵĳ���");
	private JMenuItem petMenuItem_3 = new JMenuItem("�ҵ�ԤԼ");
	private JMenuItem petMenuItem_4 = new JMenuItem("�ҵĶ���");
	
	
	private JLabel label = new JLabel("������Ʒ��");
	private JTextField jTextField = new JTextField(20);
	private JButton selectButton = new JButton("��ѯ");
	private JLabel label1 = new JLabel("<html><br><html>");
	private JLabel label2 = new JLabel("");
	private JButton buyButton = new JButton("����");
	
	private JPanel titlePane = new JPanel();
	private JPanel workPane = new JPanel();
//	private JPanel toolbar = new JPanel();
	
	private JLabel titleLabel = new JLabel("��Ʒ���");
	
	private Object tblPdtTypeTitle[]=BeanProducts_types.tblPdtTypeTitle;
	private Object tblPdtTypeData[][];
	DefaultTableModel tabPdtTypeModel=new DefaultTableModel();
	private JTable dataTablePdtType=new JTable(tabPdtTypeModel);
	
	private Object tblPdtIfmTitle[]=BeanProducts_information.tblPdtIfmTitle;
	private Object tblPdtIfmData[][];
	DefaultTableModel tabPdtIfmModel=new DefaultTableModel();
	public JTable dataTablePdtIfm=new JTable(tabPdtIfmModel);
	
	List<BeanProducts_types> allPdtType = null;
	public List<BeanProducts_information> allPdtIfm = null;
	
	public static DlgUserPet dlgUserPet;
	public static DlgServeViewU dlgServeViewU = new DlgServeViewU();
	public void reloadPdtTypeTable() {
		try {
			allPdtType = PetUtil.productTypeManager.loadProductType();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	
	public void reloadPdtIfmTable() {
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
		this.setTitle("�������ϵͳ���û��棩");	
		setJMenuBar(menuBar);
		this.menuBar.add(userMenu);
		this.menuBar.add(petMenu);
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

		this.titlePane.add(titleLabel);
		this.getContentPane().add(titlePane, BorderLayout.NORTH);
		
		this.workPane.add(label);
		this.workPane.add(jTextField);
		this.workPane.add(selectButton);
		this.workPane.add(label1);
		this.workPane.add(label2);
		this.workPane.add(buyButton);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		
		this.getContentPane().add(new JScrollPane(this.dataTablePdtType), BorderLayout.WEST);
	    this.dataTablePdtType.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmUserMain.this.dataTablePdtType.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmUserMain.this.reloadPdtIfmTable(i);
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTablePdtIfm), BorderLayout.EAST);
	    
	    
	    this.reloadPdtTypeTable();
		this.setSize(1600, 900);
		
		// ��Ļ������ʾ
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
		this.selectButton.addActionListener(this);
		this.buyButton.addActionListener(this);
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
		else if(e.getSource() == this.petMenuItem_1)
			dlgServeViewU.setVisible(true);
		
		else if(e.getSource() == this.petMenuItem_2) {
			dlgUserPet = new DlgUserPet();
			dlgUserPet.setVisible(true);
		}
		else if(e.getSource() == this.petMenuItem_3) {
			DlgMyAppointment dlgMyAppointment = new DlgMyAppointment();
			dlgMyAppointment.setVisible(true);
		}
			
		else if(e.getSource() == this.petMenuItem_4) {
			DlgMyOrder dlgMyOrder = new DlgMyOrder();
			dlgMyOrder.setVisible(true);
		}
		else if(e.getSource() == this.selectButton) {
			try {
				allPdtIfm = PetUtil.productInformationManager.selectProductInformation(this.jTextField.getText());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			DlgLogin.frmUserMain.reloadPdtIfmTable();
		}
		else if(e.getSource() == this.buyButton) {
			int i;
			try {
				i = DlgLogin.frmUserMain.dataTablePdtIfm.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��һ����Ʒ");
				DlgSubmitOrder dlgSubmitOrder = new DlgSubmitOrder();
				dlgSubmitOrder.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
