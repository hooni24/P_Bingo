package bingo.client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MakeRoomUI extends JDialog implements ActionListener{
	private static final long serialVersionUID = -8697420348772298075L;
	private JLabel lbl_title, lbl_pw;
	private JTextField tf_title, tf_pw;
	private JButton btn_create;
	private JCheckBox chkbox;
	private boolean isPasswordOn;
	private GameRoomUI gameRoomUI;
	private JFrame lobby;
	
	public MakeRoomUI(JFrame lobby) {
		this.lobby = lobby;
		setTitle("방 만들기");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setLayout(new FlowLayout());
		setSize(300,130);
		lbl_title = new JLabel("방제");
		add(lbl_title);
		tf_title = new JTextField(20);
		add(tf_title);
		lbl_pw = new JLabel("비번");
		add(lbl_pw);
		tf_pw = new JTextField(15);
		tf_pw.setEnabled(isPasswordOn);
		add(tf_pw);
		chkbox = new JCheckBox("비밀방");		chkbox.addActionListener(this);
		add(chkbox);
		btn_create = new JButton("만들기");		btn_create.addActionListener(this);
		add(btn_create);
		setLocationRelativeTo(lobby);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == btn_create){
			lobby.dispose();
			dispose();
			gameRoomUI = GameRoomUI.getGameRoomUI();
		}
		
	}

}
