package petserve.ui.user;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;

public class DlgCurrentUserInformation extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnClose = new JButton("关闭");
	
	private JLabel labelUser = new JLabel();
	private JLabel labelPwd = new JLabel();
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	
	public DlgCurrentUserInformation() {
		this.setTitle("我的信息");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.labelUser.setText("<html>昵称：" + BeanUser_information.currentLoginUser.getUser_name() + "<br>"
				+ "电话号码：" + BeanUser_information.currentLoginUser.getPhone_number() + "<br>"
						+ "电子邮箱：" + BeanUser_information.currentLoginUser.getEmail() + "<br>"
								+ "其他联系方式：" + BeanUser_information.currentLoginUser.getOther_contact() + "<html>");
		workPane.add(labelUser);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 160);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnClose.addActionListener(this);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnClose)
			this.setVisible(false);
	}
}
