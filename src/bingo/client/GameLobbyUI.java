package bingo.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bingo.data.GameRoom;
import bingo.data.User;

public class GameLobbyUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -839213678037669986L;
	private JLabel lbl_title, lbl_user, lbl_count;
	private JTable table;
	private JList<String> list;
	private JButton btn_create, btn_exit, btn_join;
	private TableModel tm;
	private Object [][] tableData = new Object[0][0];
	private String [] tableColumns = {"방제목", "빙고주제", "인원"};
	private JScrollPane scrollPane;
	private BingoGameClient bgClient;
	private User user;
	private Font font;
	
	private static GameLobbyUI lobby = new GameLobbyUI();

	private GameLobbyUI() {
		drawGUI();
	}//const
	
	public void drawGUI(){
		setTitle("빙고게임 대기실");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 800);
		Font font_title = new Font("gulim", Font.PLAIN, 100);
		font = new Font("gulim", Font.PLAIN, 50);
		
		lbl_title = new JLabel("석       빙       고");	lbl_title.setFont(font_title);	lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl_title, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		JPanel p_east = new JPanel();
		add(p_east, BorderLayout.EAST);
		p_east.setLayout(new BorderLayout());
		lbl_user = new JLabel("접속자 목록");		lbl_user.setFont(font);		lbl_user.setHorizontalAlignment(SwingConstants.CENTER);
		p_east.add(lbl_user, BorderLayout.NORTH);
		list = new JList<>();
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(list);
		p_east.add(sp, BorderLayout.CENTER);
		lbl_count = new JLabel("접속인원:00명");	lbl_count.setFont(font);	lbl_count.setHorizontalAlignment(SwingConstants.CENTER);
		p_east.add(lbl_count, BorderLayout.SOUTH);
		
		JPanel p_south = new JPanel();
		add(p_south, BorderLayout.SOUTH);
		btn_create = new JButton("방만들기");		btn_create.setFont(font);	btn_create.addActionListener(this);
		p_south.add(btn_create);
		btn_join = new JButton("참가");				btn_join.setFont(font);		btn_join.addActionListener(this);
		p_south.add(btn_join);
		btn_exit = new JButton("종료하기");			btn_exit.setFont(font);		btn_exit.addActionListener(this);
		p_south.add(btn_exit);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()
	
	public void setUserList(ArrayList<User> list){
		this.list.removeAll();
		String[] idList = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			idList[i] = list.get(i).getId();
		}
		this.list.setListData(idList);
	}//setUserList()

	public void setRoomList(HashMap<String, GameRoom> roomList) {
		Set<String> keySet = roomList.keySet();
		ArrayList<GameRoom> grArray = new ArrayList<>();
		for (String key : keySet) {
			grArray.add(roomList.get(key));
		}
		tableData = new Object[grArray.size()][3];
		int k = 0;
		for (int i = 0; i < grArray.size(); i++){
			for (int j = 0; j < 3; j++){
				if(j ==0) tableData[i][j] = grArray.get(k).getTitle();
				if(j ==1) tableData[i][j] = grArray.get(k).getTheme();
				if(j ==2) tableData[i][j] = grArray.get(k++).getMaxUserNum();
			}
		}
		
		tm = new TableModel(tableData, tableColumns);
		table = new JTable(tm);		table.setFont(font);
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setRowHeight(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setColumnSelectionAllowed(true);
		(table.getTableHeader()).setReorderingAllowed(false);
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		
	}//setRoomList()
	
	private class TableModel extends DefaultTableModel {		//inner class로 overriding
		private static final long serialVersionUID = 1380946449329321899L;
		public TableModel(Object [][] defaultRowData,Object [] defaultColumnNames){
			super.setDataVector(defaultRowData,defaultColumnNames);			 
    	}
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_create){
			new MakeRoomUI(this, user, bgClient);
		}
		
	}

	public BingoGameClient getBgClient() {
		return bgClient;
	}
	public void setBgClient(BingoGameClient bgClient) {
		this.bgClient = bgClient;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static GameLobbyUI getLobby() {
		return lobby;
	}
	public static void setLobby(GameLobbyUI lobby) {
		GameLobbyUI.lobby = lobby;
	}
	
}//class
