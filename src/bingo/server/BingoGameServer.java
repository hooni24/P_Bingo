package bingo.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class BingoGameServer extends JFrame{
	private static final long serialVersionUID = -409944801852721136L;
	private JLabel lbl_userList, lbl_userTable, lbl_log;
	private JTextArea ta_log;
	private JList list_user;
	private JTable table_user;
	private ServerSocket server;

	public BingoGameServer() {
		drawGUI();
		serverOpen();
	}//constructor
	
	/**
	 * Server GUI 그림
	 */
	public void drawGUI(){
		setTitle("Bingo Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLayout(new GridLayout(1, 3));
		JPanel p_left = new JPanel();	p_left.setLayout(new BorderLayout());
		JPanel p_center = new JPanel();	p_center.setLayout(new BorderLayout());
		JPanel p_right = new JPanel();	p_right.setLayout(new BorderLayout());
		add(p_left);
		add(p_center);
		add(p_right);
		
		Font font = new Font("D2Coding", Font.BOLD, 30);
		lbl_log = new JLabel("SERVER LOG");
		lbl_log.setFont(font);
		lbl_log.setHorizontalAlignment(SwingConstants.CENTER);
		ta_log = new JTextArea();
		JScrollPane sp_ta = new JScrollPane();
		p_left.add(lbl_log, BorderLayout.NORTH);
		sp_ta.setViewportView(ta_log);
		p_left.add(sp_ta);
		
		lbl_userList = new JLabel("LOGIN USER");
		lbl_userList.setFont(font);
		lbl_userList.setHorizontalAlignment(SwingConstants.CENTER);
		list_user = new JList<>();
		JScrollPane sp_list = new JScrollPane();
		p_center.add(lbl_userList, BorderLayout.NORTH);
		sp_list.setViewportView(list_user);
		p_center.add(sp_list);
		
		lbl_userTable = new JLabel("USER INFO");
		lbl_userTable.setFont(font);
		lbl_userTable.setHorizontalAlignment(SwingConstants.CENTER);
		table_user = new JTable();
		JScrollPane sp_table= new JScrollPane();
		p_right.add(lbl_userTable, BorderLayout.NORTH);
		sp_table.setViewportView(table_user);
		p_right.add(sp_table);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()
	
	/**
	 * ServerSocket 생성하고 accept되면 Socket을 던지며 스레드 실행. 이후 다시 accept대기
	 */
	public void serverOpen(){
		try {
			server = new ServerSocket(7979);
			appendLog("서버 열림");
			while(true){
				appendLog("대기중");
				Socket client = server.accept();
				new Thread(new BingoGameServerThread(this, client)).start();
				appendLog(client.getInetAddress() + "에서 접속했고, 소켓 스레드로 넘김");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}//t-c
	}//serverOpen()
	
	/**
	 * LOG창에 새로운 메세지 append
	 * @param 로그내용
	 */
	public void appendLog(String str){
		ta_log.append(str + "\n");
	}//appendLog()
	
}//class
