package bingo.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import bingo.data.Data;

public class BingoGameClient implements Runnable {

	private Socket client;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String id;
	private GameLobbyUI lobbyUI;

	public BingoGameClient(String id) {
		this.id = id;
		lobbyUI = GameLobbyUI.getGameLobbyUI();
		try {
			client = new Socket("localhost", 7979);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//const

	@Override
	public void run() {
		while(true){
			try {
				Data data = (Data) ois.readObject();
				switch(data.getCommand()){
				
				}//switch
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}//t-c
		}//while
	}//run()
}//class
