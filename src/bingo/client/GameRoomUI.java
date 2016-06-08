package bingo.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bingo.data.GameRoom;
import bingo.data.User;

public class GameRoomUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 6295942979520775935L;
	private JLabel lbl_title, lbl_user, lbl_time;
	private JTextField[] tf_array = new JTextField[25];
	private JButton[] btn_array = new JButton[25];
	private JButton btn_ready, btn_exit;
	private JTable table;
	private JTextField tf_message;
	private JTextArea ta_message;
	private JScrollPane scrollPane;
	private TableModel tm;
	private Object [][] tableData = new Object[0][0];
	private String [] tableColumns = {"차례", "ID", "상태", "석빙고"};
	private JPanel p_c_c_btn, p_c_c_tf;
	private Cards cards;
	private JPanel p_center;
	private Font font;
	private User host;
	private GameRoom gameroom;
	private BingoGameClient bingogameclient;
	
	private static GameRoomUI roomUI = new GameRoomUI();

	private GameRoomUI() {
		drawGUI();
	}

	private void drawGUI() {
		setTitle("즐석빙고");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 1000);
		
		font = new Font("gulim", Font.PLAIN, 30);
		
		lbl_title = new JLabel("빙고 주제 : wowow");	lbl_title.setFont(font);
		add(lbl_title, BorderLayout.NORTH);
		
		p_center = new JPanel();
		add(p_center, BorderLayout.CENTER);
		p_center.setLayout(new BorderLayout());
		
		p_c_c_tf = new JPanel();			//tf grid panel
		p_c_c_tf.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < tf_array.length; i++) {
			tf_array[i] = new JTextField();
			tf_array[i].setFont(font);
			p_c_c_tf.add(tf_array[i]);
		}
		
		p_c_c_btn = new JPanel();		//btn grid panel
		for (int i = 0; i < btn_array.length; i++) {
			btn_array[i] = new JButton();
			btn_array[i].setFont(font);
			p_c_c_btn.add(btn_array[i]);
		}
		
		cards = new Cards();
		p_center.add(cards, "Center");
		
		JPanel p_c_s = new JPanel();			//하단부 채팅
		p_c_s.setLayout(new BorderLayout());
		p_center.add(p_c_s, BorderLayout.SOUTH);
		ta_message = new JTextArea("Testing");		ta_message.setEditable(false); ta_message.setFont(new Font("gulim", Font.PLAIN, 40));
		JScrollPane sp = new JScrollPane();	sp.setPreferredSize(new Dimension(p_c_s.getWidth(), 300));	
		sp.setViewportView(ta_message);
		p_c_s.add(sp, BorderLayout.CENTER);
		tf_message = new JTextField();		tf_message.setFont(font);	tf_message.addActionListener(this);
		p_c_s.add(tf_message, BorderLayout.SOUTH);

		JPanel p_east = new JPanel();			//전체에서 east panel
		p_east.setLayout(new BorderLayout());
		add(p_east, BorderLayout.EAST);
		
		JPanel p_e_c = new JPanel();			//east - center 테이블부분
		p_east.add(p_e_c, BorderLayout.CENTER);
		p_e_c.setLayout(new BorderLayout());
		lbl_user = new JLabel("참가자");		lbl_user.setFont(font);	lbl_user.setHorizontalAlignment(SwingConstants.CENTER);
		p_e_c.add(lbl_user, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();			//테이블에 모델 붙임
		p_e_c.add(scrollPane, BorderLayout.CENTER);
		tm = new TableModel(tableData, tableColumns);
		table = new JTable(tm);
		table.setFillsViewportHeight(true);
		table.addMouseListener(new TableEventHandler());
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setColumnSelectionAllowed(true);
		(table.getTableHeader()).setReorderingAllowed(false);
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		
		lbl_time = new JLabel("제한시간:30초");	lbl_time.setFont(font);	lbl_time.setHorizontalAlignment(SwingConstants.CENTER);
		p_e_c.add(lbl_time, BorderLayout.SOUTH);
		
		JPanel p_e_s = new JPanel();			//east - south 버튼부분
		p_east.add(p_e_s, BorderLayout.SOUTH);
		btn_ready = new JButton("준비완료");	btn_ready.setFont(font);	btn_ready.addActionListener(this);
		p_e_s.add(btn_ready);
		btn_exit = new JButton("방나가기");		btn_exit.setFont(font);		btn_exit.addActionListener(this);
		p_e_s.add(btn_exit);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()
	
	private class TableModel extends DefaultTableModel {
		public TableModel(Object [][] defaultRowData,Object [] defaultColumnNames){
			super.setDataVector(defaultRowData,defaultColumnNames);			 
    	}
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}		
	}//inner class tableModel
	
	public class TableEventHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent me){
			
		}
	}//inner class mouserClicked!
	
	private class Cards extends JPanel{
		CardLayout layout;
		public Cards() {
			layout = new CardLayout();
			setLayout(layout);
			add(p_c_c_tf);
			add(p_c_c_btn);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_ready){
			p_c_c_btn.setLayout(new GridLayout(5, 5));
			for (int i = 0; i < btn_array.length; i++) {
				btn_array[i].setText(tf_array[i].getText());
				btn_array[i].setFont(font);
				p_c_c_btn.add(btn_array[i]);
			}
			cards.layout.next(cards);
		}
	}//actionPerformed()

	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public GameRoom getGameroom() {
		return gameroom;
	}
	public void setGameroom(GameRoom gameroom) {
		this.gameroom = gameroom;
	}
	public BingoGameClient getBingogameclient() {
		return bingogameclient;
	}
	public void setBingogameclient(BingoGameClient bingogameclient) {
		this.bingogameclient = bingogameclient;
	}
	public static GameRoomUI getRoomUI() {
		return roomUI;
	}
	public static void setRoomUI(GameRoomUI roomUI) {
		GameRoomUI.roomUI = roomUI;
	}

}//class
