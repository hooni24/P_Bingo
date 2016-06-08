package bingo.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import bingo.data.User;

public class BingoGameServer extends JFrame implements ActionListener, Runnable{
	private static final long serialVersionUID = 4202116736685476684L;
	private JLabel lbl_log, lbl_user;
	private JList<String> list_log, list_user;
	private DefaultListModel<String> model_log, model_user;
	private JButton btn_onOff;
	
	private boolean isOpen;
	private ServerSocket server;
	
	public BingoGameServer() {
		drawGUI();
	}//const
	
	public void drawGUI(){
		setTitle("빙고하자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 1200);
		Font font_balck = new Font("guiim", Font.PLAIN, 50);
		Font font_log = new Font("gulim", Font.PLAIN, 30);
		
		JPanel p_center = new JPanel();		p_center.setBackground(Color.CYAN);
		p_center.setLayout(new BorderLayout());
		add(p_center);
		
		lbl_log = new JLabel("서버 로그");		lbl_log.setFont(font_balck);	lbl_log.setHorizontalAlignment(SwingConstants.CENTER);	lbl_log.setForeground(Color.RED);
		p_center.add(lbl_log, BorderLayout.NORTH);
		JScrollPane sp_log = new JScrollPane();
		list_log = new JList<>();				list_log.setFont(font_log);	list_log.setBackground(Color.MAGENTA);	list_log.setForeground(Color.BLACK);
		sp_log.setViewportView(list_log);
		p_center.add(sp_log, BorderLayout.CENTER);
		model_log = new DefaultListModel<>();
		
		JPanel p_east = new JPanel();		p_east.setBackground(Color.PINK);
		p_east.setPreferredSize(new Dimension(500, 1000));
		p_east.setLayout(new BorderLayout());
		add(p_east, BorderLayout.EAST);
		
		lbl_user = new JLabel("유저 리스트");	lbl_user.setFont(font_balck);	lbl_user.setHorizontalAlignment(SwingConstants.CENTER);	lbl_user.setForeground(Color.RED);
		p_east.add(lbl_user, BorderLayout.NORTH);
		JScrollPane sp_user = new JScrollPane();
		list_user = new JList<>();				list_user.setFont(font_log);	list_user.setBackground(Color.ORANGE);
		sp_user.setViewportView(list_user);
		p_east.add(sp_user, BorderLayout.CENTER);
		
		btn_onOff = new JButton("서버 열기 (현재 닫힘)");	btn_onOff.setFont(font_balck);	btn_onOff.setBackground(Color.BLACK); btn_onOff.setForeground(Color.WHITE);
		btn_onOff.addActionListener(this);
		add(btn_onOff, BorderLayout.SOUTH);
		
		setVisible(true);
	}//drawGUI()
	
	public void setLogModel(String message){
		model_log.addElement(message);
		list_log.setModel(model_log);
	}//setLogModel()
	
	public void spSetting(){		//2개 리스트 모두 스크롤페인 갱신
		
	}
	
	public void setUserModel(ArrayList<User> users){		//유저 리스트 갱신
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_onOff){
			if(isOpen){
				btn_onOff.setText("서버 열기 (현재 닫힘)");
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				btn_onOff.setText("서버 닫기 (현재 열림)");
				isOpen = true;
				new Thread(this).start();
			}
		}
	}//actionPerformed()
	
	@Override
	public void run() {
		try {
			server = new ServerSocket(7979);
			setLogModel(String.format("[%s %s] 빙고게임 서버 시작!", "날짜", "현재시간"));
			while(isOpen){
				Socket client = server.accept();
				setLogModel(String.format("[%s %s] %s 에서 접속!", "날짜", "현재시간", client.getInetAddress()));
				new Thread(new BingoGameServerThread(this, client)).start();
			}
		} catch (IOException e) {
			isOpen = false;
		} finally {
			setLogModel(String.format("[%s %s] 빙고게임 서버 종료!", "날짜", "현재시간"));
		}
	}
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		new BingoGameServer();
	}
	
}//class
