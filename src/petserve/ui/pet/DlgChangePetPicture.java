package petserve.ui.pet;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import petserve.action.PetUtil;
import petserve.ui.FrmUserMain;
import petserve.util.BaseException;

public class DlgChangePetPicture extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnchoose = new JButton("选择");
	private JButton btnConfirm = new JButton("修改");
	private JButton btnClose = new JButton("关闭");
	
	private JLabel label2 = new JLabel("图片：");
	private JTextField edt2 = new JTextField(20);

	private byte[] picture;
	public DlgChangePetPicture() {
		// TODO Auto-generated constructor stub
		this.setTitle("修改宠物图片");
		this.toolBar.add(btnchoose);
		this.toolBar.add(btnConfirm);
		this.toolBar.add(btnClose);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.workPane.add(label2);
		this.workPane.add(edt2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);
		
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnConfirm.addActionListener(this);
		this.btnClose.addActionListener(this);
		this.btnchoose.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnConfirm) {
			try {
				PetUtil.petManager.changePetPicture(FrmUserMain.dlgUserPet.allPet.get(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()).getPet_id(), picture);
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.PLAIN_MESSAGE);
				FrmUserMain.dlgUserPet.reloadPetTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == btnchoose) {
			JFileChooser fd = new JFileChooser();
		//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fd.showOpenDialog(null);
		File f = fd.getSelectedFile();
		String s = f.getAbsolutePath();
		this.edt2.setText(s);
		try {
			InputStream fin=new FileInputStream(f);
			picture = new byte[fin.available()];
		    fin.read(picture);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		
		else if(e.getSource() == btnClose)
			this.setVisible(false);
	}

}
