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
import petserve.ui.FrmUserMain;
import petserve.util.BaseException;

public class DlgServeViewU extends JDialog implements ActionListener {
	
	private JLabel label = new JLabel("输入商品名");
	private JTextField jTextField = new JTextField(20);
	private JButton jButton = new JButton("查询");
	private JButton submitButton = new JButton("提交预约");
	
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
	
	public DlgServeViewU() {
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("服务浏览");	
		
		this.workPane.add(label);
		this.workPane.add(jTextField);
		this.workPane.add(jButton);
		this.workPane.add(submitButton);
		this.getContentPane().add(workPane, BorderLayout.NORTH);		
		
		this.getContentPane().add(new JScrollPane(this.dataTableServeType), BorderLayout.WEST);
	    this.dataTableServeType.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=DlgServeViewU.this.dataTableServeType.getSelectedRow();
				if(i<0) {
					return;
				}
				DlgServeViewU.this.reloadServeIfmTable(i);
			}
	    	
	    });

		this.getContentPane().add(new JScrollPane(this.dataTableServeIfm), BorderLayout.EAST);
	    
	    this.reloadServeTypeTable();
		this.setSize(1600, 900);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
	
		this.submitButton.addActionListener(this);
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
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			}
			FrmUserMain.dlgServeViewU.reloadServeIfmTable();
		}
		else if(e.getSource() == this.submitButton) {
			
		}
	}

}
