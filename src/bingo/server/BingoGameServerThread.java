package bingo.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import bingo.data.Data;
import bingo.data.GameRoom;
import bingo.data.User;

public class BingoGameServerThread implements Runnable {
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean exit;
	private BingoGameServer parent;
	static ArrayList<User> connectedUserList = new ArrayList<>(); //서버에 접속된 클라이언트, 각 클라이언트의 ObjectOutputStream이 저정되어 있음
	static HashMap<String, GameRoom> gameRoomList = new HashMap<>();
	private Data data;
	private User me;
	
	public BingoGameServerThread(BingoGameServer parent, Socket client) {
		this.parent = parent;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(!exit){
			try {
				data = (Data)ois.readObject();
				switch(data.getCommand()){
					case Data.LOGIN: {
						data.getUser().setOos(oos);
						connectedUserList.add(data.getUser());
						data.setUserList(connectedUserList);
						data.setRoomList(gameRoomList);
						me = data.getUser();
						broadCasting();
					}
						break;
						
					case Data.MAKE_ROOM: {
						gameRoomList.put(data.getGameRoom().getRoomID(), data.getGameRoom());	//게임룸ID를 키로, 게임룸객체를 밸류로 put
						data.setRoomList(gameRoomList);
						broadCasting();
					}
						break;
						
					case Data.JOIN: {
						
					}
						break;
						
					case Data.CHAT_MESSAGE:
					case Data.SEND_WINNING_RESULT:
					case Data.GAME_READY:
					case Data.GAME_START:
					case Data.SEND_BINGO_DATA: {
						sendDataRoommate(data.getGameRoom().getRoomID());
					}
						break;
						
					case Data.EXIT: {
						
					}
						break;
				}//switch
				
			} catch (IOException e) {
				exit = true;
				connectedUserList.remove(me);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}//while
	}//run
	
	/**
	 * 같은 방에 있는 유저에게 Data객체 전송
	 * */
	public void sendDataRoommate(String roomID){
		for (User user : connectedUserList) {
			try {
				if(user.getRoom().getRoomID().equals(roomID)){
					user.getOos().writeObject(data);
					user.getOos().reset();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 모든 유저에게 Data객체 전송
	 * */
	public void broadCasting(){
		for (User user : connectedUserList) {
			try {
				user.getOos().writeObject(data);
				user.getOos().reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//broadCasting()
	

}
