package petserve.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
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
import petserve.util.BaseException;

public class DlgRegister1 extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("    手机号：");
	private JLabel labelPwd = new JLabel("        密码：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JLabel labelEmail = new JLabel("        邮箱：");
	private JLabel labelName = new JLabel("        昵称：");
	private JLabel labelOContect = new JLabel("其他联系方式：");
	private JTextField edtUserPhone = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField edtUserEmail = new JTextField(20);
	private JTextField edtUserName = new JTextField(20);
	private JTextField edtOtherContect = new JTextField(20);
	
	public DlgRegister1(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserPhone);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		workPane.add(labelEmail);
		workPane.add(edtUserEmail);
		workPane.add(labelName);
		workPane.add(edtUserName);
		workPane.add(labelOContect);
		workPane.add(edtOtherContect);
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
			String userid=this.edtUserPhone.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			String userName=this.edtUserName.getText();
			String userEmail=this.edtUserEmail.getText();
			String otherctc=this.edtOtherContect.getText();
			try {
				BeanUser_information user=PetUtil.userManager.reg(userid,pwd1,pwd2,userName,userEmail,otherctc);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.PLAIN_MESSAGE);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
