package bingo.data;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
	private String id;	//아이디
	private int privilege;	//권한
	private GameRoom room; //플레이어가 참가중인 방 정보
	private transient ObjectOutputStream oos;	//소켓을 통해 만들어낸 ObjectOutputStream 객체, 서버에서 해당 유저에게 메세지(Data객체)를 보낼 때 사용
	private String state = "준비중"; //게임방에서의 상태 값, 준비중  or 준비완료의 상태값을 가짐
	
	public static final int HOST_PRIVILEGE = 10;
	public static final int NORMAL_PRIVILEGE = 20;
	
	public static final String READY = "준비중";
	public static final String DONE = "준비완료";
	
	public User(String id, int privilege) {
		this.id = id;
		this.privilege = privilege;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public GameRoom getRoom() {
		return room;
	}

	public void setRoom(GameRoom room) {
		this.room = room;
	}

	public String getId() {
		return id;
	}
	
	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", privilege=" + privilege + ", room=" + room + "]";
	}
	
}
