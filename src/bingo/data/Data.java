package bingo.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Data implements Serializable {
	private String message; //채팅 대화내용
	private int command; //요청 명령 상수
	private GameRoom gameRoom; //게임방 정보
	private GameInfo gameInfo; //게임 정보
	private User user; //Data객체를 발송하는 사용자 정보
	private ArrayList<User> userList; //서버에 접속되어있는 사용자 목록
	private HashMap<String, GameRoom> roomList; //게설된 방 목록
	
	public static final int LOGIN = 10;									//로그인
	public static final int MAKE_ROOM = 20;							//게임방 생성
	public static final int JOIN = 30;										//게임방 입장
	public static final int CHAT_MESSAGE = 40;					//게임방 내 채팅
	public static final int SEND_BINGO_DATA = 50;				//선택한 빙고 단어 전송
	public static final int SEND_WINNING_RESULT = 60;		//5개의 빙고가 완성됐을 시 승리결과 전송
	public static final int GAME_READY = 70;						//25개 빙고단어 입력 완료 후 준비
	public static final int GAME_START = 80;						//방에 참가한 모든 유저가 준비상태가 되어 게임시작을 알림
	public static final int EXIT = -10;										//프로그램 종료
	
	public Data(int command) {
		this.command = command;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCommand() {
		return command;
	}
	
	public void setCommand(int command) {
		this.command = command;
	}

	public GameRoom getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public HashMap<String, GameRoom> getRoomList() {
		return roomList;
	}

	public void setRoomList(HashMap<String, GameRoom> roomList) {
		this.roomList = roomList;
	}

	public GameInfo getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
	
	
	
}
