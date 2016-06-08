package bingo.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 8557828972999498452L;
	private JLabel lbl_title, lbl_id;
	private JTextField tf_id;
	private JButton btn_ok, btn_cancel;
	
	public LoginUI() {
		drawGUI();
	}//const
	
	public void drawGUI(){
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		Font font_title = new Font("Malgun Gothic Semilight", Font.PLAIN, 80);
		Font font_object = new Font("Malgun Gothic Semilight", Font.PLAIN, 50);
		
		lbl_title = new JLabel("빙고게임");	lbl_title.setFont(font_title);	lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setForeground(Color.RED);
		add(lbl_title, BorderLayout.NORTH);
		
		JPanel p_center = new JPanel();	p_center.setBackground(Color.GREEN);
		add(p_center);
		lbl_id = new JLabel("아이디");		lbl_id.setFont(font_object);
		tf_id = new JTextField(10);			tf_id.setFont(font_object);	tf_id.setHorizontalAlignment(SwingConstants.CENTER);
		tf_id.addActionListener(this);
		p_center.add(lbl_id);
		p_center.add(tf_id);
		
		btn_ok = new JButton("입장");		btn_ok.setFont(font_object);		btn_ok.setBackground(Color.WHITE);	btn_ok.setForeground(Color.MAGENTA);
		btn_ok.addActionListener(this);
		btn_cancel = new JButton("닫기");	btn_cancel.setFont(font_object);	btn_cancel.setBackground(Color.WHITE);	btn_cancel.setForeground(Color.MAGENTA);
		btn_cancel.addActionListener(this);
		
		JPanel p_south = new JPanel();
		add(p_south, BorderLayout.SOUTH);
		p_south.add(btn_ok);
		p_south.add(btn_cancel);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//drawGUI()
	
	
	public static void main(String[] args) {
		new LoginUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_ok || source == tf_id){
			if(tf_id.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			}else {
				new BingoGameClient(tf_id.getText());
				dispose();
			}
		}
	}//actionPerformed()

}//class
