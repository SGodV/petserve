package petserve.ui.user;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import petserve.action.PetUtil;
import petserve.util.BaseException;

public class DlgChangePassword extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("修改");
	private JButton btnClose = new JButton("关闭");
	
	private JLabel pwdLabel1 = new JLabel("原密码：    ");
	private JLabel pwdLabel2 = new JLabel("新密码：    ");
	private JLabel pwdLabel3 = new JLabel("确认密码：");
	private JPasswordField edtPwd1 = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JPasswordField edtPwd3 = new JPasswordField(20);
	
	public DlgChangePassword() {
		this.setTitle("修改密码");
		
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(pwdLabel1);
		this.workPane.add(edtPwd1);
		this.workPane.add(pwdLabel2);
		this.workPane.add(edtPwd2);
		this.workPane.add(pwdLabel3);
		this.workPane.add(edtPwd3);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 200);
		
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
			String oldPwd = new  String(this.edtPwd1.getPassword());
			String newPwd = new  String(this.edtPwd2.getPassword());
			String newPwd2 = new  String(this.edtPwd3.getPassword());
			try {
				PetUtil.userManager.changePwd(oldPwd, newPwd, newPwd2);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
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
