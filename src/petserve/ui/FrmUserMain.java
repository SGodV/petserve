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
	
	private JMenu userMenu = new JMenu("�û�");
	private JMenu orderMenu = new JMenu("�ҵĶ���");
	private JMenu buyNewMenu = new JMenu("��Ʒ������");

	private JMenuItem userMenuItem_1 = new JMenuItem("�ҵ���Ϣ");
	private JMenuItem userMenuItem_2 = new JMenuItem("�ҵĳ���");
	private JMenuItem userMenuItem_3 = new JMenuItem("ԤԼ��Ϣ��ѯ");
	
	private JMenuItem orderMenuItem_1 = new JMenuItem("δ֧������");
	private JMenuItem orderMenuItem_2 = new JMenuItem("�����ж���");
	private JMenuItem orderMenuItem_3 = new JMenuItem("����ɶ���");
	
	
	
	public FrmUserMain() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("�������ϵͳ");
		
		
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
