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

public class DlgAppointmentPet extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton chooseButton = new JButton("ȷ��ѡ��");
	private JButton cancelButton = new JButton("ȡ��");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblPetTitle[]=BeanUser_information.tblUserTitle;
	private Object tblPetData[][];
	DefaultTableModel tabPetModel=new DefaultTableModel();
	private JTable dataPetType=new JTable(tabPetModel);
	
	private List<BeanPet_information> allPet = null;
	public void reloadPetTable() {
		try {
			allPet = PetUtil.petManager.loadPetByUser(BeanUser_information.currentLoginUser.getUser_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPetData =  new Object[allPet.size()][BeanProducts_types.tblPdtTypeTitle.length];
		for(int i=0;i<allPet.size();i++){
			for(int j=0;j<BeanProducts_types.tblPdtTypeTitle.length;j++)
				tblPetData[i][j]=allPet.get(i).getCell(j);
		}
		tabPetModel.setDataVector(tblPetData,tblPetTitle);
		this.dataPetType.validate();
		this.dataPetType.repaint();
	}
	public DlgAppointmentPet() {
		this.setTitle("������Ϣ����");
		this.lable.setText("�ҵĳ�����Ϣ");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(chooseButton);
		this.toolBar.add(cancelButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataPetType), BorderLayout.CENTER);
		
		this.reloadPetTable();
		this.setSize(1600, 900);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		this.validate();
		
		this.chooseButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == chooseButton) {
			try {
				if(this.dataPetType.getSelectedRow()<0)
					throw new BaseException("��ѡ��һֻ����");
				PetUtil.appointmentManager.submitAppointment(allPet.get(this.dataPetType.getSelectedRow()).getPet_id(), 
						FrmUserMain.dlgServeViewU.allServeIfm.get(FrmUserMain.dlgServeViewU.dataTableServeIfm.getSelectedRow()).getProduct_id());
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "ԤԼ�ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				FrmUserMain.dlgServeViewU.setVisible(false);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cancelButton) {
			this.setVisible(false);
		}
	
	}


}
