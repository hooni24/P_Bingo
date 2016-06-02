package bingo.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom implements Serializable {
	private String roomID;	//방  ID, 방장ID+시스템시간 형식으로 생성
	private String title; //방제목
	private String theme; //빙고주제
	private int maxUserNum; //최대 플레이어 수
	private int nowUserNum; //현재 플레이어 수
	private HashMap<String, User> userList = new HashMap<>(); //방에 참가중인 플레이어 목록
	private ArrayList<String> turnUserList = new ArrayList<>(); //방에 참가한 플레이어 순서대로 ID를 저장, 빙고를 선택할 유저의 순저를 정하기 위해 사용(참가한 순서가 게임을 진행할 순서가 됨)
	private User turnUser; //빙고 버튼을 누를 차례가 된 User
	private int pointer = 0; //방에 참가한 플레이어의 다음 턴을 가지게 될 User객체를 가리킬 인텍스를 계산할 때 사용될 값
	private HashMap<String, Integer> bingoNumList = new HashMap<>(); //userID : 빙고 갯수
	
	public GameRoom(String roomId, String title, String theme, int maxUserNum) {
		this.roomID = roomId;
		this.title = title;
		this.theme = theme;
		this.maxUserNum = maxUserNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getMaxUserNum() {
		return maxUserNum;
	}

	public void setMaxUserNum(int maxUserNum) {
		this.maxUserNum = maxUserNum;
	}

	public int getNowUserNum() {
		return nowUserNum;
	}

	public void setNowUserNum(int nowUserNum) {
		this.nowUserNum = nowUserNum;
	}

	public HashMap<String, User> getUserList() {
		return userList;
	}
	
	public void addUser(User user){
		userList.put(user.getId(), user);
		turnUserList.add(user.getId());
	}
	
	public void setUserList(HashMap<String, User> userList){
		this.userList = userList;
	}
	
	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
	public User getTurnUser() {
		return turnUser;
	}

	public void setTurnUser(){
		int index = pointer % nowUserNum;
		pointer++;
		String userID = turnUserList.get(index);
		turnUser = userList.get(userID);
		System.out.println(index+" : "+userID);
	}
	
	public ArrayList<String> getTurnUserList() {
		return turnUserList;
	}
	
	public void updateBingoNum(String id, int num){
		bingoNumList.put(id, num);
	}
	
	public HashMap<String, Integer> getBingoNumList() {
		return bingoNumList;
	}

	@Override
	public String toString() {
		return "GameRoom [roomID=" + roomID + ", title=" + title + ", theme=" + theme + ", maxUserNum=" + maxUserNum
				+ ", nowUserNum=" + nowUserNum + ", userList=" + userList + "]";
	}
	
}
