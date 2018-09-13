package petserve.ui.orderandappointment;

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
import javax.swing.table.DefaultTableModel;

import petserve.action.PetUtil;
import petserve.model.BeanAppointment;
import petserve.model.BeanPet_information;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.ui.FrmManagerMain;
import petserve.util.BaseException;

public class DlgUserAppointment extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton completButton = new JButton("����ԤԼ");
	private JButton cancelButton = new JButton("ȡ��ԤԼ");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblAppointmentTitle[]=BeanAppointment.tblAppointmentTitle;
	private Object tblAppointmentData[][];
	DefaultTableModel tabAppointmentModel=new DefaultTableModel();
	private JTable dataAppointmentType=new JTable(tabAppointmentModel);
	
	private List<BeanAppointment> allAppointment = null;
	public void reloadAppointmentTable() {
		try {
			allAppointment = PetUtil.appointmentManager.selectAppointment(FrmManagerMain.dlgManagerUser.allUser.get(FrmManagerMain.dlgManagerUser.dataUserType.getSelectedRow()).getUser_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAppointmentData =  new Object[allAppointment.size()][BeanAppointment.tblAppointmentTitle.length];
		for(int i=0;i<allAppointment.size();i++){
			for(int j=0;j<BeanAppointment.tblAppointmentTitle.length;j++)
				tblAppointmentData[i][j]=allAppointment.get(i).getCell(j);
		}
		tabAppointmentModel.setDataVector(tblAppointmentData,tblAppointmentTitle);
		this.dataAppointmentType.validate();
		this.dataAppointmentType.repaint();
	}
	public DlgUserAppointment() {
		this.setTitle("�ҵ�ԤԼ��Ϣ");
		this.lable.setText("�ҵ�ԤԼ��Ϣ");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(completButton);
		this.toolBar.add(cancelButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataAppointmentType), BorderLayout.CENTER);
		
		this.reloadAppointmentTable();
		this.setSize(1600, 900);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		this.validate();
		
		this.completButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == completButton) {
			try {
				int i = this.dataAppointmentType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��һ��ԤԼ��Ϣ");
				PetUtil.appointmentManager.finishTime(this.allAppointment.get(i).getAppointment_id());
				JOptionPane.showMessageDialog(null, "���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				this.reloadAppointmentTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cancelButton) {
			try {
				int i = this.dataAppointmentType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��һ��ԤԼ��Ϣ");
				PetUtil.appointmentManager.cancelAppointment(this.allAppointment.get(i).getAppointment_id());
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				this.reloadAppointmentTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
