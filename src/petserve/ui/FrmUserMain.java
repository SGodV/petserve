package petserve.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import petserve.model.BeanUser_information;

public class FrmUserMain extends JFrame implements ActionListener{
	public static BeanUser_information currentLoginUser = null;
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu userMenu = new JMenu("用户");
	private JMenu orderMenu = new JMenu("我的订单");
	private JMenu buyNewMenu = new JMenu("商品及服务");

	private JMenuItem userMenuItem_1 = new JMenuItem("我的信息");
	private JMenuItem userMenuItem_2 = new JMenuItem("我的宠物");
	private JMenuItem userMenuItem_3 = new JMenuItem("预约信息查询");
	
	private JMenuItem orderMenuItem_1 = new JMenuItem("未支付订单");
	private JMenuItem orderMenuItem_2 = new JMenuItem("进行中订单");
	private JMenuItem orderMenuItem_3 = new JMenuItem("已完成订单");
	
	
	
	private JPanel titlePane = new JPanel();
	private JPanel workPane = new JPanel();
	
	
	public void reloadMainTable() {
		
	}
	
	public FrmUserMain() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("宠物服务系统");
		
		
		setJMenuBar(menuBar);
		
		this.menuBar.add(userMenu);
		this.menuBar.add(orderMenu);
		this.menuBar.add(buyNewMenu);
	
		this.userMenu.add(userMenuItem_1);
		this.userMenu.add(userMenuItem_2);
		this.userMenu.add(userMenuItem_3);
		
		this.orderMenu.add(orderMenuItem_1);
		this.orderMenu.add(orderMenuItem_2);
		this.orderMenu.add(orderMenuItem_3);
		
		
		this.setVisible(true);
		
		this.userMenuItem_1.addActionListener(this);
		this.userMenuItem_2.addActionListener(this);
		this.userMenuItem_3.addActionListener(this);
		this.orderMenuItem_1.addActionListener(this);
		this.orderMenuItem_2.addActionListener(this);
		this.orderMenuItem_3.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.userMenuItem_1) {
			this.reloadMainTable();
		}
		else if(e.getSource() == this.userMenuItem_2) ;
		else if(e.getSource() == this.userMenuItem_3) ;
		else if(e.getSource() == this.orderMenuItem_1) ;
		else if(e.getSource() == this.orderMenuItem_2) ;
		else if(e.getSource() == this.orderMenuItem_3) ;
	}

}
