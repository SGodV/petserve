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
import petserve.ui.user.DlgManagerUser;
import petserve.util.BaseException;

public class DlgManagerPet extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton petPctButton = new JButton("�鿴����ͼƬ");
	private JButton cgPetNameButton = new JButton("�޸ĳ����ǳ�");
	private JButton cgPetAgeButton = new JButton("�޸ĳ�������");
	private JButton cgPetTypeNameButton = new JButton("�޸ĳ���ѧ��");
	private JButton cgPetHealthyButton = new JButton("�޸ĳ��｡��״̬");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblUserTitle[]=BeanUser_information.tblUserTitle;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	private JTable dataUserType=new JTable(tabUserModel);
	
	private List<BeanPet_information> allPet = null;
	private BeanUser_information selectUser = FrmManagerMain.dlgManagerUser.allUser.get(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow());
	public void reloadPdtTypeTable() {
		try {
			allPet = PetUtil.petManager.loadPetByUser(selectUser.getUser_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[allPet.size()][BeanProducts_types.tblPdtTypeTitle.length];
		for(int i=0;i<allPet.size();i++){
			for(int j=0;j<BeanProducts_types.tblPdtTypeTitle.length;j++)
				tblUserData[i][j]=allPet.get(i).getCell(j);
		}
		tabUserModel.setDataVector(tblUserData,tblUserTitle);
		this.dataUserType.validate();
		this.dataUserType.repaint();
	}
	public DlgManagerPet() {
		this.setTitle("������Ϣ����");
		this.lable.setText(selectUser.getUser_name()+"�ĳ�����Ϣ");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(petPctButton);
		this.toolBar.add(cgPetNameButton);
		this.toolBar.add(cgPetAgeButton);
		this.toolBar.add(cgPetTypeNameButton);
		this.toolBar.add(cgPetHealthyButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataUserType), BorderLayout.CENTER);
		
		this.reloadPdtTypeTable();
		
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == petPctButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataUserType.getSelectedRow()<0)
					throw new BaseException("��ѡ��һֻ����");
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cgPetNameButton) {
			try {
				if(DlgManagerUser.dlgManagerPet.dataUserType.getSelectedRow()<0)
					throw new BaseException("��ѡ��һֻ����");
				DlgChangePetName dlgChangePetName = new DlgChangePetName();
				dlgChangePetName.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cgPetAgeButton) {
			
		}
		else if(e.getSource() == cgPetTypeNameButton) {
			
		}
		else if(e.getSource() == cgPetHealthyButton) {
			
		}
	}


}
