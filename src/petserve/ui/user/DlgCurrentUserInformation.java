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
	private JButton btnClose = new JButton("�ر�");
	
	private JLabel labelUser = new JLabel();
	private JLabel labelPwd = new JLabel();
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	
	public DlgCurrentUserInformation() {
		this.setTitle("�ҵ���Ϣ");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.labelUser.setText("<html>�ǳƣ�" + BeanUser_information.currentLoginUser.getUser_name() + "<br>"
				+ "�绰���룺" + BeanUser_information.currentLoginUser.getPhone_number() + "<br>"
						+ "�������䣺" + BeanUser_information.currentLoginUser.getEmail() + "<br>"
								+ "������ϵ��ʽ��" + BeanUser_information.currentLoginUser.getOther_contact() + "<html>");
		workPane.add(labelUser);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 160);
		
		// ��Ļ������ʾ
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
