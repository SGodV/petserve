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
import petserve.ui.FrmUserMain;
import petserve.ui.user.DlgManagerUser;
import petserve.util.BaseException;

public class DlgChangePetNameU extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("�޸�");
	private JButton btnClose = new JButton("�ر�");
	
	private JLabel label2 = new JLabel("���ǳƣ�");
	private JTextField edt2 = new JTextField(20);

	public DlgChangePetNameU() {
		// TODO Auto-generated constructor stub
		this.setTitle("�޸ĳ����ǳ�");
		
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label2);
		this.workPane.add(edt2);
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
				PetUtil.petManager.changePetName(FrmUserMain.dlgUserPet.allPet.get(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()).getPet_id(), this.edt2.getText());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				FrmUserMain.dlgUserPet.reloadPetTable();
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
