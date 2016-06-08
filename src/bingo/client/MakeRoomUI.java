package bingo.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bingo.data.Data;
import bingo.data.GameRoom;
import bingo.data.User;

public class MakeRoomUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = -1351230597004960547L;
	private JFrame gameLobbyUI;
	private JLabel lbl_title, lbl_subject, lbl_size, lbl_p;
	private JTextField tf_title, tf_subject;
	private JComboBox<String> combo_size;
	private JButton btn_ok, btn_cancel;
	private User user;
	private GameRoom gameroom;
	private BingoGameClient bgClient;
	private GameRoomUI roomUI;
	
	public MakeRoomUI(){
		drawGUI();
	}
	
	public MakeRoomUI(JFrame gameLobbyUI, User user, BingoGameClient bgClient) {
		this.gameLobbyUI = gameLobbyUI;
		this.user = user;
		this.bgClient = bgClient;
		drawGUI();
	}//const
	
	
	public void drawGUI(){
		setTitle("방만들기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 400);
		setModal(true);
		setLayout(new GridLayout(4, 1));
		Font font = new Font("gulim", Font.PLAIN, 50);
		
		JPanel p_1 = new JPanel();	p_1.setBackground(Color.PINK);
		JPanel p_2 = new JPanel();	p_2.setBackground(Color.PINK);
		JPanel p_3 = new JPanel();	p_3.setBackground(Color.PINK);
		JPanel p_4 = new JPanel();	p_4.setBackground(Color.PINK);
		
		lbl_title = new JLabel("방제");		lbl_title.setFont(font);
		tf_title = new JTextField(10);		tf_title.setFont(font);
		p_1.add(lbl_title);
		p_1.add(tf_title);
		add(p_1);
		
		lbl_subject = new JLabel("주제");	lbl_subject.setFont(font);
		tf_subject = new JTextField(10);	tf_subject.setFont(font);
		p_2.add(lbl_subject);
		p_2.add(tf_subject);
		add(p_2);
		
		lbl_size = new JLabel("인원");		lbl_size.setFont(font);
		combo_size = new JComboBox<>(new String[] {"2     ", "3     ", "4     ", "5     ", "6     "});		combo_size.setFont(font);
		lbl_p = new JLabel("명");			lbl_p.setFont(font);
		p_3.add(lbl_size);
		p_3.add(combo_size);
		p_3.add(lbl_p);
		add(p_3);
		
		btn_ok = new JButton("확인");		btn_ok.setFont(font);		btn_ok.addActionListener(this);
		btn_cancel = new JButton("취소");	btn_cancel.setFont(font);	btn_cancel.addActionListener(this);
		p_4.add(btn_ok);
		p_4.add(btn_cancel);
		add(p_4);
		
		setLocationRelativeTo(gameLobbyUI);
		setVisible(true);
	}//drawGUI()

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_cancel){
			dispose();
		}else if (source == btn_ok){
			gameroom = new GameRoom(user.getId()+"현재시간", tf_title.getText(), tf_subject.getText(), Integer.parseInt(((String) combo_size.getSelectedItem()).trim()));
			user.setPrivilege(User.HOST_PRIVILEGE);		//권한을 방장으로
			gameroom.addUser(user);		//User 등록
			gameroom.setMaxUserNum(Integer.parseInt(((String) combo_size.getSelectedItem()).trim()));	//참여인원수 수정
			user.setRoom(gameroom);	//User에 GameRoom세팅
			Data data = new Data(Data.MAKE_ROOM);	//make_room명령의 데이터 생성
			data.setUser(user);
			data.setGameRoom(gameroom);
			try {
				user.getOos().writeObject(data);	//데이터 발송
				dispose();		//만들기 디스포즈
				gameLobbyUI.dispose();	//로비 디스포즈
				roomUI = GameRoomUI.getRoomUI();		//게임룸 불러오고 값 세팅(static)
				roomUI.setHost(user);
				roomUI.setGameroom(gameroom);
				roomUI.setBingogameclient(bgClient);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}//actionPerformed()
	
	

}//class
