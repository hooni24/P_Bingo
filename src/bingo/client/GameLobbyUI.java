package bingo.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameLobbyUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -8630797700330321232L;
	private JList<Object> list_room;		//방제목만 나올 예정
	private String id;
	private JButton btn_connect, btn_create;
	private static GameLobbyUI me = new GameLobbyUI();

	private GameLobbyUI() {
		drawGUI();
	}
	
	public static GameLobbyUI getGameLobbyUI(){
		return me;
	}
	
	private void drawGUI() {
		setTitle("대기실");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		JScrollPane sp_list = new JScrollPane();
		list_room = new JList<>();
		sp_list.setViewportView(list_room);
		add(sp_list);
		btn_connect = new JButton("접속");			btn_connect.addActionListener(this);
		JPanel p_north = new JPanel();
		p_north.setLayout(new FlowLayout());
		p_north.add(btn_connect);
		btn_create = new JButton("방 만들기");		btn_create.addActionListener(this);
		p_north.add(btn_create);
		add(p_north, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_create){
			new MakeRoomUI(this);
		}
		
	}
}
