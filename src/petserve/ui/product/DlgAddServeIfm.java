package petserve.ui.product;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.model.BeanUser_information;
import petserve.ui.DlgLogin;
import petserve.ui.FrmManagerMain;
import petserve.util.BaseException;

public class DlgAddServeIfm extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private Button btnOk = new Button("新增");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPdtName = new JLabel("服务名称：");
//	private JLabel labelBrand = new JLabel("商品品牌：");
	private JLabel labelPdtPrice = new JLabel("服务定价：");
//	private JLabel labelPdtCode = new JLabel("服务条码：");
	private JLabel labelPrice = new JLabel("服务售价：");
	private JTextField edtPdtName = new JTextField(20);
//	private JTextField edtBrand = new JTextField(20);
	private JTextField edtPdtPrice = new JTextField(20);
//	private JTextField edtPdtCode = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	
	public DlgAddServeIfm() {
		// TODO Auto-generated constructor stub
		this.setTitle("新增商品条目");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPdtName);
		workPane.add(edtPdtName);
//		workPane.add(labelBrand);
//		workPane.add(edtBrand);
		workPane.add(labelPdtPrice);
		workPane.add(edtPdtPrice);
//		workPane.add(labelPdtCode);
//		workPane.add(edtPdtCode);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 170);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		this.validate();
		
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			try {
				int i=FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow();
				if(this.edtPdtPrice.getText()==null || "".equals(this.edtPdtPrice.getText()))
					throw new BaseException("服务定价不能为空");
				if(this.edtPrice.getText()==null || "".equals(this.edtPrice.getText()))
					throw new BaseException("服务售价不能为空");
				PetUtil.productInformationManager.addPdtIfm(FrmManagerMain.dlgServeView.allServeType.get(i).getType_code(), this.edtPdtName.getText(), 
						"本店提供", Float.parseFloat(this.edtPdtPrice.getText()), "", Float.parseFloat(this.edtPrice.getText()));
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.PLAIN_MESSAGE);
				if (FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow() >= 0)
					FrmManagerMain.dlgServeView.reloadServeIfmTable(FrmManagerMain.dlgServeView.dataTableServeType.getSelectedRow());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}


}
