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
	
	private JButton btnConfirm = new JButton("�޸�");
	private JButton btnClose = new JButton("�ر�");
	
	private JLabel pwdLabel1 = new JLabel("ԭ���룺    ");
	private JLabel pwdLabel2 = new JLabel("�����룺    ");
	private JLabel pwdLabel3 = new JLabel("ȷ�����룺");
	private JPasswordField edtPwd1 = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JPasswordField edtPwd3 = new JPasswordField(20);
	
	public DlgChangePassword() {
		this.setTitle("�޸�����");
		
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
		
		// ��Ļ������ʾ
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
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == btnClose)
			this.setVisible(false);
	}
}
