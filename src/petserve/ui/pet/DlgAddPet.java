package petserve.ui.pet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DlgAddPet extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("×¢²á");
	private Button btnCancel = new Button("È¡Ïû");
	
	private JLabel labelPetName = new JLabel("³èÎïêÇ³Æ£º        ");
	private JLabel labelPetAge = new JLabel("³èÎïÄêÁä£º        ");
	private JLabel labelPetHealthy = new JLabel("³èÎï½¡¿µ×´Ì¬£º");
	private JLabel labelPetPicture = new JLabel("³èÎïÍ¼Æ¬£º        ");
	private JTextField edtPetName = new JTextField(20);
	private JTextField edtPetAge = new JTextField(20);
	private JTextField edtPetHealthy = new JTextField(20);
	private JTextField edtPetPicture = new JTextField(20);
	
	public DlgAddPet() {
		// TODO Auto-generated constructor stub
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPetName);
		workPane.add(edtPetName);
		workPane.add(labelPetAge);
		workPane.add(edtPetAge);
		workPane.add(labelPetHealthy);
		workPane.add(edtPetHealthy);
		workPane.add(labelPetPicture);
		workPane.add(edtPetPicture);
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
		if (e.getSource() == btnOk) {
			
		}
	}

}
