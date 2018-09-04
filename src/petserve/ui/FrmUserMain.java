package petserve.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FrmUserMain extends JFrame implements ActionListener{
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
	
	
	
	public FrmUserMain() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("宠物服务系统");
		
		
		setJMenuBar(menuBar);
		
		menuBar.add(userMenu);
		menuBar.add(orderMenu);
		menuBar.add(buyNewMenu);
	
		userMenu.add(userMenuItem_1);
		userMenu.add(userMenuItem_2);
		userMenu.add(userMenuItem_3);
		
		orderMenu.add(orderMenuItem_1);
		orderMenu.add(orderMenuItem_2);
		orderMenu.add(orderMenuItem_3);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
