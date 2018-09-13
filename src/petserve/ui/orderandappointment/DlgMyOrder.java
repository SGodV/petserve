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
import petserve.model.BeanOrder_information;
import petserve.model.BeanPet_information;
import petserve.model.BeanProducts_types;
import petserve.model.BeanUser_information;
import petserve.util.BaseException;

public class DlgMyOrder extends JDialog implements ActionListener {
	private JLabel lable = new JLabel();
	private JButton completButton = new JButton("ȷ���ջ�");
	private JButton cancelButton = new JButton("ȡ������");
	
	private JPanel workPanel = new JPanel(); 
	private JPanel toolBar = new JPanel(); 
	
	private Object tblOrderTitle[]=BeanOrder_information.tblOrderTitle;
	private Object tblOrderData[][];
	DefaultTableModel tabOrderModel=new DefaultTableModel();
	public JTable dataOrderType=new JTable(tabOrderModel);
	
	public List<BeanOrder_information> allOrder = null;
	public void reloadOrderTable() {
		try {
			allOrder = PetUtil.orderManager.selectOrder(BeanUser_information.currentLoginUser.getUser_id());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrderData = new Object[allOrder.size()][BeanOrder_information.tblOrderTitle.length];
		for(int i=0;i<allOrder.size();i++){
			for(int j=0;j<BeanOrder_information.tblOrderTitle.length;j++)
				tblOrderData[i][j]=allOrder.get(i).getCell(j);
		}
		tabOrderModel.setDataVector(tblOrderData,tblOrderTitle);
		this.dataOrderType.validate();
		this.dataOrderType.repaint();
	}
	public DlgMyOrder() {
		this.setTitle("�ҵĶ�����Ϣ");
		this.lable.setText("�ҵĶ�����Ϣ");
		this.workPanel.add(lable);
		this.getContentPane().add(workPanel, BorderLayout.NORTH);
		this.toolBar.add(completButton);
		this.toolBar.add(cancelButton);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		this.getContentPane().add(new JScrollPane(this.dataOrderType), BorderLayout.CENTER);
		
		this.reloadOrderTable();
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
				int i = this.dataOrderType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��һ��ԤԼ��Ϣ");
				PetUtil.orderManager.finishOrder(this.allOrder.get(i).getOrder_id());
				JOptionPane.showMessageDialog(null, "���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				this.reloadOrderTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cancelButton) {
			try {
				int i = this.dataOrderType.getSelectedRow();
				if(i<0)
					throw new BaseException("��ѡ��һ��ԤԼ��Ϣ");
				PetUtil.orderManager.cancelOrder(this.allOrder.get(i).getOrder_id());
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.PLAIN_MESSAGE);
				this.reloadOrderTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
}
