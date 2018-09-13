package petserve.ui.product;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.ui.DlgLogin;
import petserve.util.BaseException;

public class DlgCgBrand extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("修改");
	private JButton btnClose = new JButton("关闭");
	
	private JLabel label1 = new JLabel("品牌：");
	private JTextField edt1 = new JTextField(20);

	public DlgCgBrand() {
		// TODO Auto-generated constructor stub
		this.setTitle("修改商品品牌");
		
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label1);
		this.workPane.add(edt1);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnConfirm.addActionListener(this);
		this.btnClose.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnConfirm) {
			int i = DlgLogin.frmManagerMain.dataTablePdtIfm.getSelectedRow();
			try {
				PetUtil.productInformationManager.changeBrand(DlgLogin.frmManagerMain.allPdtIfm.get(i).getProduct_id(), 
						this.edt1.getText());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
				if (DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow() >= 0)
					DlgLogin.frmManagerMain.reloadPdtIfmTable(DlgLogin.frmManagerMain.dataTablePdtType.getSelectedRow());
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == btnClose)
			this.setVisible(false);
	}

}
