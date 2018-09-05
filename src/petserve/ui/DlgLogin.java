package petserve.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;

public class DlgLogin extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("µÇÂ½");
	//private JButton btnCancel = new JButton("ÍË³ö");
	private JButton btnRegister1 = new JButton("ÓÃ»§×¢²á");
	private JButton btnRegister2 = new JButton("¹ÜÀíÔ±×¢²á");
	
	private JLabel labelUser = new JLabel("µÇÂ¼Ãû£º");
	private JLabel labelPwd = new JLabel("    ÃÜÂë£º");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	
	public DlgLogin() {
		this.setTitle("µÇÂ¼");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnLogin);
		//toolBar.add(btnCancel);
		toolBar.add(this.btnRegister1);
		toolBar.add(this.btnRegister2);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		//btnCancel.addActionListener(this);
		this.btnRegister1.addActionListener(this);
		this.btnRegister2.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});				
		
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnLogin) {
//			String userid=this.edtUserId.getText();
//			String pwd=new String(this.edtPwd.getPassword());
//			try {
//				FrmUserMain.currentLoginUser = PetUtil.userManager.login(userid, pwd);
//			} catch (BaseException e1) {
//				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			this.setVisible(false);
//			if (FrmUserMain.currentLoginUser.getAuthority() == 1)
//				new FrmUserMain();
//			else if (FrmUserMain.currentLoginUser.getAuthority() == 0)
//				;
//			else
//				System.exit(0);
			this.setVisible(false);
			new FrmUserMain();
		}
			
		else if (e.getSource() == this.btnRegister1) {
			DlgRegister1 dlg1=new DlgRegister1(this,"×¢²á",true);
			dlg1.setVisible(true);
		}
		else if (e.getSource() == this.btnRegister2) {
			DlgRegister2 dlg2=new DlgRegister2(this,"×¢²á",true);
			dlg2.setVisible(true);
		}
	}

}
