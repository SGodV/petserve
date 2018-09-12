package petserve.ui.pet;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.util.BaseException;

public class DlgChangePetHealthy extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnConfirm = new JButton("ÐÞ¸Ä");
	private JButton btnClose = new JButton("¹Ø±Õ");
	
	private JLabel label1 = new JLabel("³èÎï±àºÅ£º");
	private JLabel label2 = new JLabel("½¡¿µ×´Ì¬£º");
	private JRadioButton s1=new JRadioButton("½¡¿µ");
	private JRadioButton s2=new JRadioButton("²»½¡¿µ",true);
	private JTextField edt1 = new JTextField(20);
	
	private ButtonGroup bg = new ButtonGroup();

	public DlgChangePetHealthy() {
		// TODO Auto-generated constructor stub
		this.setTitle("ÐÞ¸Ä³èÎï½¡¿µ×´Ì¬");
		this.setVisible(true);
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label1);
		this.workPane.add(edt1);
		this.workPane.add(label2);
		this.workPane.add(s1);
		this.workPane.add(s2);
		this.bg.add(s1);
		this.bg.add(s2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		
		// ÆÁÄ»¾ÓÖÐÏÔÊ¾
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
				short healthy = (short) -1;
				if (s1.isSelected())
					healthy = (short) 0;
				else if(s2.isSelected())
					healthy = (short) 1;
				PetUtil.petManager.changePetHealthy(Integer.parseInt(this.edt1.getText()), healthy);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "ÐÞ¸Ä³É¹¦", "ÌáÊ¾", JOptionPane.PLAIN_MESSAGE);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == btnClose)
			this.setVisible(false);
	}
}
