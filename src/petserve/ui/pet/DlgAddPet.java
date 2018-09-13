package petserve.ui.pet;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import antlr.ByteBuffer;
import petserve.action.PetUtil;
import petserve.ui.user.DlgManagerUser;
import petserve.util.BaseException;

public class DlgAddPet extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ע��");
	private Button btnCancel = new Button("ȡ��");
	private Button btnPicture = new Button("ѡ��ͼƬ");
	
	private JLabel labelPetName = new JLabel("�����ǳƣ�        ");
	private JLabel labelPetTypeName = new JLabel("����ѧ����        ");
	private JLabel labelPetAge = new JLabel("�������䣺        ");
	private JLabel labelPetHealthy = new JLabel("���｡��״̬��");
	private JLabel labelPetPicture = new JLabel("����ͼƬ��        ");
	private JTextField edtPetName = new JTextField(20);
	private JTextField edtPetTypeName = new JTextField(20);
	private JTextField edtPetAge = new JTextField(20);
	private JTextField edtPetHealthy = new JTextField(20);
	private JTextField edtPetPicture = new JTextField(20);
	
	private byte[] picture;
	public DlgAddPet() {
		// TODO Auto-generated constructor stub
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(this.btnCancel);
		toolBar.add(this.btnPicture);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPetName);
		workPane.add(edtPetName);
		workPane.add(labelPetTypeName);
		workPane.add(edtPetTypeName);
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
		this.btnPicture.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnOk) {
			try {
				
				PetUtil.petManager.addPet(this.edtPetName.getText(), this.edtPetTypeName.getText(), picture, 
						Short.parseShort(this.edtPetAge.getText()), Short.parseShort(this.edtPetHealthy.getText()));
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				DlgManagerUser.dlgManagerPet.reloadPetTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if (e.getSource() == btnPicture) {
			JFileChooser fd = new JFileChooser();
			//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			String s = f.getAbsolutePath();
			this.edtPetPicture.setText(s);
			try {
				InputStream fin=new FileInputStream(f);
				picture = new byte[fin.available()];
			    fin.read(picture);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == btnCancel) {
			this.setVisible(false);
		}
	}
	
}
