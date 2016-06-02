package bingo.client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -7641227473956161937L;
	private JLabel lbl_id, lbl_pw;
	private JTextField tf_id, tf_pw;
	private JButton btn_login, btn_signin;
	
	public LoginUI() {
		drawGUI();
	}//const
	
	public void drawGUI(){
		setTitle("로그인");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 170);
		lbl_id = new JLabel("아이디");
		add(lbl_id);
		tf_id = new JTextField(10);			tf_id.addActionListener(this);
		add(tf_id);
		lbl_pw = new JLabel("비밀번호");
		add(lbl_pw);
		tf_pw = new JPasswordField(10);			tf_pw.addActionListener(this);
		add(tf_pw);
		btn_login = new JButton("로그인");	btn_login.addActionListener(this);
		add(btn_login);
		btn_signin = new JButton("가입");	btn_signin.addActionListener(this);
		add(btn_signin);
		
		tf_pw.setEnabled(false);
		btn_signin.setEnabled(false);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == tf_id || source == btn_login){
			if(tf_id.getText().trim().length() > 0){
				new Thread(new BingoGameClient(tf_id.getText().trim())).start();
				dispose();
			}else {
				JOptionPane.showMessageDialog(this, "ID는 1글자 이상");
			}
		}
	}//actionPerformed()
	
	
	public static void main(String[] args) {
		new LoginUI();
	}//main
}
