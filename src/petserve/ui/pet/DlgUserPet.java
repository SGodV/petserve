package petserve.ui.pet;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import petserve.action.PetUtil;
import petserve.model.BeanPet_information;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.ui.FrmManagerMain;
import petserve.ui.FrmUserMain;
import petserve.ui.user.DlgManagerUser;
import petserve.util.BaseException;

public class DlgUserPet extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton addPetButton = new JButton("添加宠物");
	private JButton petPctButton = new JButton("查看宠物图片");
	private JButton cgPetNameButton = new JButton("修改宠物昵称");
	private JButton cgPetAgeButton = new JButton("修改宠物年龄");
	private JButton cgPetPctButton = new JButton("修改宠物图片");
	private JButton deletePetButton = new JButton("删除宠物");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblPetTitle[]=BeanPet_information.tblPetTitle;
	private Object tblPetData[][];
	DefaultTableModel tabPetModel=new DefaultTableModel();
	public JTable dataPetType=new JTable(tabPetModel);
	
	public List<BeanPet_information> allPet = null;
	public void reloadPetTable() {
		try {
			allPet = PetUtil.petManager.loadPetByUser(BeanUser_information.currentLoginUser.getUser_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPetData =  new Object[allPet.size()][BeanPet_information.tblPetTitle.length];
		for(int i=0;i<allPet.size();i++){
			for(int j=0;j<BeanPet_information.tblPetTitle.length;j++)
				tblPetData[i][j]=allPet.get(i).getCell(j);
		}
		tabPetModel.setDataVector(tblPetData,tblPetTitle);
		this.dataPetType.validate();
		this.dataPetType.repaint();
	}
	public DlgUserPet() {
		this.setTitle("我的宠物信息");
		this.lable.setText("我的宠物信息");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(addPetButton);
		this.toolBar.add(petPctButton);
		this.toolBar.add(cgPetNameButton);
		this.toolBar.add(cgPetAgeButton);
		this.toolBar.add(cgPetPctButton);
		this.toolBar.add(deletePetButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataPetType), BorderLayout.CENTER);
		
		this.reloadPetTable();
		this.setSize(1600, 900);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		this.validate();
		
		this.addPetButton.addActionListener(this);
		this.petPctButton.addActionListener(this);
		this.cgPetNameButton.addActionListener(this);
		this.cgPetAgeButton.addActionListener(this);
		this.cgPetPctButton.addActionListener(this);
		this.deletePetButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addPetButton) {
				DlgAddPet dlgAddPet = new DlgAddPet();
				dlgAddPet.setVisible(true);
		}
		else if(e.getSource() == petPctButton) {
			try {
				if(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetNameButton) {
			try {
				if(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetNameU dlgChangePetNameU = new DlgChangePetNameU();
				dlgChangePetNameU.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetAgeButton) {
			try {
				if(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetAgeU dlgChangePetAgeU = new DlgChangePetAgeU();
				dlgChangePetAgeU.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetPctButton) {
			try {
				if(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetPicture dlgChangePetPicture = new DlgChangePetPicture();
				dlgChangePetPicture.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == deletePetButton) {
			try {
				if(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				PetUtil.petManager.deletePet(FrmUserMain.dlgUserPet.allPet.get(FrmUserMain.dlgUserPet.dataPetType.getSelectedRow()).getPet_id());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
				FrmUserMain.dlgUserPet.reloadPetTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}


}
