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
	private JButton completButton = new JButton("确认收货");
	private JButton cancelButton = new JButton("取消订单");
	
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
		this.setTitle("我的订单信息");
		this.lable.setText("我的订单信息");
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
					throw new BaseException("请选择一条预约信息");
				PetUtil.orderManager.finishOrder(this.allOrder.get(i).getOrder_id());
				JOptionPane.showMessageDialog(null, "完成", "提示", JOptionPane.PLAIN_MESSAGE);
				this.reloadOrderTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == cancelButton) {
			try {
				int i = this.dataOrderType.getSelectedRow();
				if(i<0)
					throw new BaseException("请选择一条预约信息");
				PetUtil.orderManager.cancelOrder(this.allOrder.get(i).getOrder_id());
				JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);
				this.reloadOrderTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
}
