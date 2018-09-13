package petserve.ui.pet;

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
import petserve.util.BaseException;

public class DlgDeletePetU extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("ȷ��ɾ��");
	private JButton btnClose = new JButton("�ر�");
	
	private JLabel label1 = new JLabel("�����ţ�");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);

	public DlgDeletePetU() {
		// TODO Auto-generated constructor stub
		this.setTitle("ɾ������");
		
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label1);
		this.workPane.add(edt1);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		
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
			try {
				PetUtil.petManager.deletePet(Integer.parseInt(this.edt1.getText()));
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
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
