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

public class DlgManagerPet extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton petPctButton = new JButton("查看宠物图片");
	private JButton cgPetNameButton = new JButton("修改宠物昵称");
	private JButton cgPetAgeButton = new JButton("修改宠物年龄");
	private JButton cgPetTypeNameButton = new JButton("修改宠物学名");
	private JButton cgPetHealthyButton = new JButton("修改宠物健康状态");
	private JButton deletePetButton = new JButton("删除宠物健康状态");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblPetTitle[]=BeanPet_information.tblPetTitle;
	private Object tblPetData[][];
	DefaultTableModel tabPetModel=new DefaultTableModel();
	public JTable dataPetType=new JTable(tabPetModel);
	
	public List<BeanPet_information> allPet = null;
	private BeanUser_information selectUser = FrmManagerMain.dlgManagerUser.allUser.get(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow());
	public void reloadPetTable() {
		try {
			allPet = PetUtil.petManager.loadPetByUser(selectUser.getUser_id());
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
	public DlgManagerPet() {
		this.setTitle("宠物信息管理");
		this.lable.setText(selectUser.getUser_name()+"的宠物信息");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(petPctButton);
		this.toolBar.add(cgPetNameButton);
		this.toolBar.add(cgPetAgeButton);
		this.toolBar.add(cgPetTypeNameButton);
		this.toolBar.add(cgPetHealthyButton);
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
		
		this.petPctButton.addActionListener(this);
		this.cgPetNameButton.addActionListener(this);
		this.cgPetAgeButton.addActionListener(this);
		this.cgPetTypeNameButton.addActionListener(this);
		this.cgPetHealthyButton.addActionListener(this);
		this.deletePetButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == petPctButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
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
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetNameM dlgChangePetNameM = new DlgChangePetNameM();
				dlgChangePetNameM.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetAgeButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetAgeM dlgChangePetAgeM = new DlgChangePetAgeM();
				dlgChangePetAgeM.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetTypeNameButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangeTypeName dlgChangeTypeName = new DlgChangeTypeName();
				dlgChangeTypeName.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cgPetHealthyButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				DlgChangePetHealthy dlgChangePetHealthy = new DlgChangePetHealthy();
				dlgChangePetHealthy.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}	
		}
		else if(e.getSource() == deletePetButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()<0)
					throw new BaseException("请选择一只宠物");
				PetUtil.petManager.deletePet(DlgManagerUser.dlgManagerPet.allPet.get(DlgManagerUser.dlgManagerPet.dataPetType.getSelectedRow()).getPet_id());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
				DlgManagerUser.dlgManagerPet.reloadPetTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}	
		}
	}


}
