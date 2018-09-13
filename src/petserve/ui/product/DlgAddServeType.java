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

public class DlgAddServeType extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private Button btnOk = new Button("新增");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelPdtTypeName = new JLabel("服务类别名称：");
	private JLabel labelPdtTpeDsc = new JLabel("服务类别描述：");
	private JTextField edtPdtTypeName = new JTextField(20);
	private JTextField edtPdtTpeDsc = new JTextField(20);
	
	public DlgAddServeType() {
		// TODO Auto-generated constructor stub
		this.setTitle("新增服务类别");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPdtTypeName);
		workPane.add(edtPdtTypeName);
		workPane.add(labelPdtTpeDsc);
		workPane.add(edtPdtTpeDsc);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 140);
		
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
				PetUtil.productTypeManager.addServeType(edtPdtTypeName.getText(), edtPdtTpeDsc.getText());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "新增成功", "提示", JOptionPane.PLAIN_MESSAGE);
				FrmManagerMain.dlgServeView.reloadServeTypeTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
