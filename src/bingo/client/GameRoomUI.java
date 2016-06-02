package bingo.client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameRoomUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 6277097691948036446L;
	private JLabel lbl_dayNight, lbl_remainTime, lbl_myJob, lbl_userlist, lbl_title;
	private JList list_user;
	private JTextArea ta_message;
	private JTextField tf_message;
	private JButton btn_send, btn_ready, btn_start;
	private String room_title, room_pw;
	private ArrayList<String> roomList = new ArrayList<>();
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String id;
	private JScrollPane sp_ta;
	private static GameRoomUI a = new GameRoomUI();
	
	private GameRoomUI() {
		drawGUI();
	}
	
	public static GameRoomUI getGameRoomUI(){
		return a;
	}

	public void drawGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		JPanel p_main = new JPanel();
		p_main.setLayout(new BorderLayout());
		add(p_main);
		JPanel p_main_north = new JPanel();
		lbl_title = new JLabel("빙고게임 Ver.1");
		p_main_north.add(lbl_title);			
		p_main.add(p_main_north, BorderLayout.NORTH);
		JPanel p_main_center = new JPanel();
		p_main_center.setLayout(new BorderLayout());
		p_main.add(p_main_center);
		sp_ta = new JScrollPane();
		ta_message = new JTextArea();
		ta_message.setEditable(false);
		sp_ta.setViewportView(ta_message);
		p_main_center.add(sp_ta);
		JPanel p_main_center_north = new JPanel();
		p_main_center.add(p_main_center_north, BorderLayout.SOUTH);
		tf_message = new JTextField(25);
		btn_send = new JButton("전송");			btn_send.addActionListener(this);
		p_main_center_north.add(tf_message);
		p_main_center_north.add(btn_send, BorderLayout.EAST);
		
		Font font = new Font("D2Coding", Font.PLAIN, 20);
		JPanel p_main_east = new JPanel();
		p_main_east.setLayout(new GridLayout(2, 1));
		p_main.add(p_main_east, BorderLayout.EAST);
		JPanel p_main_east_top = new JPanel();
		p_main_east_top.setLayout(new GridLayout(0, 1));
		p_main_east.add(p_main_east_top);
		lbl_dayNight = new JLabel("123");					lbl_dayNight.setFont(font);			lbl_dayNight.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_remainTime = new JLabel("123");	lbl_remainTime.setFont(font);		lbl_remainTime.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_myJob = new JLabel("123");			lbl_myJob.setFont(font);			lbl_myJob.setHorizontalAlignment(SwingConstants.CENTER);
		p_main_east_top.add(new JLabel());	
		p_main_east_top.add(lbl_dayNight);
		p_main_east_top.add(lbl_remainTime);
		p_main_east_top.add(lbl_myJob);			
		btn_ready = new JButton("레디");		btn_ready.addActionListener(this);
		btn_start = new JButton("스타트");		btn_start.addActionListener(this);
		p_main_east_top.add(btn_ready);	p_main_east_top.add(btn_start);
		JPanel p_main_east_bottom = new JPanel();
		p_main_east_bottom.setLayout(new BorderLayout());
		lbl_userlist = new JLabel("접속자 명단");			lbl_userlist.setFont(font);			lbl_userlist.setHorizontalAlignment(SwingConstants.CENTER);
		p_main_east_bottom.add(lbl_userlist, BorderLayout.NORTH);
		list_user = new JList<>();
		JScrollPane sp_list = new JScrollPane();
		sp_list.setViewportView(list_user);
		p_main_east_bottom.add(sp_list);
		p_main_east.add(p_main_east_bottom);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
