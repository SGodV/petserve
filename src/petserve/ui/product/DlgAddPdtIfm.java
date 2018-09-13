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

public class DlgAddPdtIfm extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private Button btnOk = new Button("����");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelPdtName = new JLabel("��Ʒ���ƣ�");
	private JLabel labelBrand = new JLabel("��ƷƷ�ƣ�");
	private JLabel labelPdtPrice = new JLabel("��Ʒ���ۣ�");
	private JLabel labelPdtCode = new JLabel("��Ʒ���룺");
	private JLabel labelPrice = new JLabel("��Ʒ�ۼۣ�");
	private JTextField edtPdtName = new JTextField(20);
	private JTextField edtBrand = new JTextField(20);
	private JTextField edtPdtPrice = new JTextField(20);
	private JTextField edtPdtCode = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	
	public DlgAddPdtIfm() {
		// TODO Auto-generated constructor stub
		this.setTitle("������Ʒ��Ŀ");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPdtName);
		workPane.add(edtPdtName);
		workPane.add(labelBrand);
		workPane.add(edtBrand);
		workPane.add(labelPdtPrice);
		workPane.add(edtPdtPrice);
		workPane.add(labelPdtCode);
		workPane.add(edtPdtCode);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 240);
		
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
				int i=DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow();
				if(this.edtPdtPrice.getText()==null || "".equals(this.edtPdtPrice.getText()))
					throw new BaseException("��Ʒ���۲���Ϊ��");
				if(this.edtPrice.getText()==null || "".equals(this.edtPrice.getText()))
					throw new BaseException("��Ʒ�ۼ۲���Ϊ��");
				PetUtil.productInformationManager.addPdtIfm(DlgLogin.frmManagerMain.allPdtType.get(i).getType_code(), this.edtPdtName.getText(), 
						this.edtBrand.getText(), Float.parseFloat(this.edtPdtPrice.getText()), this.edtPdtCode.getText(), 
						Float.parseFloat(this.edtPrice.getText()));
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "�����ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				if (DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow() >= 0)
					DlgLogin.frmManagerMain.reloadPdtIfmTable(DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}


}
