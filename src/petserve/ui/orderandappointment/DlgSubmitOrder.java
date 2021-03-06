package petserve.ui.orderandappointment;

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
import petserve.ui.FrmUserMain;
import petserve.util.BaseException;

public class DlgSubmitOrder extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("提交");
	private JButton btnClose = new JButton("关闭");
	
	private JLabel label2 = new JLabel("购买数量：");
	private JTextField edt2 = new JTextField(20);

	public DlgSubmitOrder() {
		// TODO Auto-generated constructor stub
		this.setTitle("提交订单");
		
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label2);
		this.workPane.add(edt2);
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
			try {
				PetUtil.orderManager.submitOrder(DlgLogin.frmUserMain.allPdtIfm.get(DlgLogin.frmUserMain.dataTablePdtIfm.getSelectedRow()).getProduct_id(), 
						Integer.parseInt(this.edt2.getText()), DlgLogin.frmUserMain.allPdtIfm.get(DlgLogin.frmUserMain.dataTablePdtIfm.getSelectedRow()).getPrice());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "购买成功", "提示", JOptionPane.PLAIN_MESSAGE);
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
