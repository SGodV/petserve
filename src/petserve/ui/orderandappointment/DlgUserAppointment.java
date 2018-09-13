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
	private JButton completButton = new JButton("结束预约");
	private JButton cancelButton = new JButton("取消预约");
	
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
		this.setTitle("我的预约信息");
		this.lable.setText("我的预约信息");
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
					throw new BaseException("请选择一条预约信息");
				PetUtil.appointmentManager.finishTime(this.allAppointment.get(i).getAppointment_id());
				JOptionPane.showMessageDialog(null, "完成", "提示", JOptionPane.PLAIN_MESSAGE);
				this.reloadAppointmentTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cancelButton) {
			try {
				int i = this.dataAppointmentType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择一条预约信息");
				PetUtil.appointmentManager.cancelAppointment(this.allAppointment.get(i).getAppointment_id());
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
				this.reloadAppointmentTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
