package bingo.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import bingo.data.Data;
import bingo.data.User;

public class BingoGameClient implements Runnable{
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private User user;
	private Data data;
	private GameLobbyUI lobby;
	private GameRoomUI roomUI;
	
	public BingoGameClient(String id) {
		try {
			socket = new Socket("localhost", 7979);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			user = new User(id, User.NORMAL_PRIVILEGE);		//일반권한을 부여한 User객체 생성
			user.setOos(oos);
			new Thread(this).start();
			lobby = GameLobbyUI.getLobby();
			lobby.setUser(user);
			lobby.setBgClient(this);
			Data data = new Data(Data.LOGIN);
			data.setUser(user);
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//const

	@Override
	public void run() {
		while(true){
			try {
				data = (Data) ois.readObject();
				switch(data.getCommand()){
				case Data.LOGIN: {
					lobby.setUserList(data.getUserList());
					lobby.setRoomList(data.getRoomList());
				}
					break;
					
				case Data.MAKE_ROOM: {
					lobby.setRoomList(data.getRoomList());
				}
					break;
					
				case Data.JOIN: {
					lobby.setRoomList(data.getRoomList());	//로비의 개설된 방 목록 갱신
					//같은방 플레이어 정보 갱신 필요
				}
					break;
					
				case Data.CHAT_MESSAGE:
				case Data.SEND_WINNING_RESULT:
				case Data.GAME_READY:
				case Data.GAME_START:
				case Data.SEND_BINGO_DATA: {
				}
					break;
					
				case Data.EXIT: {
					
				}
					break;
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}//run()

}//class
