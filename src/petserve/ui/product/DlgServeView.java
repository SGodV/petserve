package petserve.ui.product;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import petserve.action.PetUtil;
import petserve.model.BeanProducts_information;
import petserve.model.BeanProducts_types;
import petserve.ui.DlgLogin;
import petserve.ui.FrmManagerMain;
import petserve.util.BaseException;

public class DlgServeView extends JDialog implements ActionListener {
	
	private JLabel label = new JLabel("������Ʒ��");
	private JTextField jTextField = new JTextField(20);
	private JButton jButton = new JButton("��ѯ");
	private JButton addType = new JButton("������������");
	private JButton addDetail = new JButton("����������Ŀ");
	private JButton cgTypeName = new JButton("���ķ�����������");
	private JButton cgTypeDsc = new JButton("���ķ�����������");
	private JButton cgPdtName = new JButton("���ķ�����");
//	private JButton cgBrand = new JButton("����Ʒ��");
	private JButton cgPrice = new JButton("�����ۼ�");
//	private JButton cgPdtCode = new JButton("������Ʒ����");
	private JButton deleteType = new JButton("ɾ���������");
	private JButton deleteDetail = new JButton("ɾ��������Ŀ");
	
	private JPanel workPane = new JPanel();
	private JPanel toolbar = new JPanel();
	
	private Object tblServeTypeTitle[]=BeanProducts_types.tblPdtTypeTitle;
	private Object tblServeTypeData[][];
	DefaultTableModel tabPdtServeModel=new DefaultTableModel();
	public JTable dataTableServeType=new JTable(tabPdtServeModel);
	
	private Object tblServeIfmTitle[]=BeanProducts_information.tblPdtIfmTitle;
	private Object tblServeIfmData[][];
	DefaultTableModel tabServeIfmModel=new DefaultTableModel();
	public JTable dataTableServeIfm=new JTable(tabServeIfmModel);
	
	public List<BeanProducts_types> allServeType = null;
	public List<BeanProducts_information> allServeIfm = null;
	public void reloadServeTypeTable() {
		try {
			allServeType = PetUtil.productTypeManager.loadServeType();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblServeTypeData =  new Object[allServeType.size()][BeanProducts_types.tblPdtTypeTitle.length];
		for(int i=0;i<allServeType.size();i++){
			for(int j=0;j<BeanProducts_types.tblPdtTypeTitle.length;j++)
				tblServeTypeData[i][j]=allServeType.get(i).getCell(j);
		}
		tabPdtServeModel.setDataVector(tblServeTypeData,tblServeTypeTitle);
		this.dataTableServeType.validate();
		this.dataTableServeType.repaint();
	}
	
	public void reloadServeIfmTable(int positionid) {
		try {
			allServeIfm = PetUtil.productInformationManager.loadProductInformation(allServeIfm.get(positionid).getType_code());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblServeIfmData =  new Object[allServeIfm.size()][BeanProducts_information.tblPdtIfmTitle.length];
		for(int i=0;i<allServeIfm.size();i++){
			for(int j=0;j<BeanProducts_information.tblPdtIfmTitle.length;j++)
				tblServeIfmData[i][j]=allServeIfm.get(i).getCell(j);
		}
		tabServeIfmModel.setDataVector(tblServeIfmData,tblServeIfmTitle);
		this.dataTableServeIfm.validate();
		this.dataTableServeIfm.repaint();
	}
	
	public void reloadServeIfmTable() {
		tblServeIfmData =  new Object[allServeIfm.size()][BeanProducts_information.tblPdtIfmTitle.length];
		for(int i=0;i<allServeIfm.size();i++){
			for(int j=0;j<BeanProducts_information.tblPdtIfmTitle.length;j++)
				tblServeIfmData[i][j]=allServeIfm.get(i).getCell(j);
		}
		tabServeIfmModel.setDataVector(tblServeIfmData,tblServeIfmTitle);
		this.dataTableServeIfm.validate();
		this.dataTableServeIfm.repaint();
	}
	
	public DlgServeView() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("�������");	
		
		this.workPane.add(label);
		this.workPane.add(jTextField);
		this.workPane.add(jButton);
		this.getContentPane().add(workPane, BorderLayout.NORTH);
		
		this.toolbar.add(addType);
		this.toolbar.add(cgTypeName);
		this.toolbar.add(cgTypeDsc);
		this.toolbar.add(addDetail);
		this.toolbar.add(cgPdtName);
//		this.toolbar.add(cgBrand);
		this.toolbar.add(cgPrice);
//		this.toolbar.add(cgPdtCode);
		this.toolbar.add(deleteType);
		this.toolbar.add(deleteDetail);
		this.getContentPane().add(toolbar, BorderLayout.SOUTH);
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableServeType), BorderLayout.WEST);
	    this.dataTableServeType.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=DlgServeView.this.dataTableServeType.getSelectedRow();
				if(i<0) {
					return;
				}
				DlgServeView.this.reloadServeIfmTable(i);
			}
	    	
	    });

		this.getContentPane().add(new JScrollPane(this.dataTableServeIfm), BorderLayout.EAST);
	    
	    this.reloadServeTypeTable();
		this.setSize(1600, 900);
		
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	
		
		this.addType.addActionListener(this);
		this.cgTypeName.addActionListener(this);
		this.cgTypeDsc.addActionListener(this);
		this.addDetail.addActionListener(this);
		this.cgPdtName.addActionListener(this);
//		this.cgBrand.addActionListener(this);
		this.cgPrice.addActionListener(this);
//		this.cgPdtCode.addActionListener(this);
		this.deleteType.addActionListener(this);
		this.deleteDetail.addActionListener(this);
		this.jButton.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.jButton) {
			try {
				allServeIfm = PetUtil.productInformationManager.selectServeInformation(this.jTextField.getText());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			}
			FrmManagerMain.dlgServeView.reloadServeIfmTable();
		}
		else if(e.getSource() == this.addType) {
			DlgAddServeType dlgAddServeType = new DlgAddServeType();
			dlgAddServeType.setVisible(true);
		}
		else if(e.getSource() == this.cgTypeName) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��Ҫ�޸ĵ����");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgCgTypeName2 dlgCgTypeName2 = new DlgCgTypeName2();
			dlgCgTypeName2.setVisible(true);
		}
		else if(e.getSource() == this.cgTypeDsc) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��Ҫ�޸ĵ����");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgCgTypeDetail2 dlgCgTypeDetail2 = new DlgCgTypeDetail2();
			dlgCgTypeDetail2.setVisible(true);
		}
		else if(e.getSource() == this.addDetail) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ������������");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			DlgAddServeIfm dlgAddServeIfm = new DlgAddServeIfm();
			dlgAddServeIfm.setVisible(true);
		}
		else if(e.getSource() == this.cgPdtName) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeIfm.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ�������Ŀ");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			DlgCgPdtName2 dlgCgPdtName2 = new DlgCgPdtName2();
			dlgCgPdtName2.setVisible(true);
		}
		else if(e.getSource() == this.cgPrice) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeIfm.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ�������Ŀ");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			DlgCgPrice2 dlgCgPrice2 = new DlgCgPrice2();
			dlgCgPrice2.setVisible(true);
		}
		else if(e.getSource() == this.deleteType) {
			try {
				int i = FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ������������");
				PetUtil.productTypeManager.deletePdtType(allServeType.get(i).getType_code());
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmManagerMain.dlgServeView.reloadServeTypeTable();
			
		}
		else if(e.getSource() == this.deleteDetail) {
			int i;
			try {
				i = FrmManagerMain.dlgServeView.dataTableServeIfm.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ�������Ŀ");
				PetUtil.productInformationManager.deletePdtIfm(allServeIfm.get(i).getProduct_id());
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow() >= 0)
				FrmManagerMain.dlgServeView.reloadServeIfmTable(FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow());
		}
	}

}
