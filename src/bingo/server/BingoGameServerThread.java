package bingo.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import bingo.data.Data;

public class BingoGameServerThread implements Runnable {
	private Socket client;
	private BingoGameServer gui;
	private boolean connect = true;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public BingoGameServerThread(BingoGameServer gui, Socket client) throws IOException {
		this.client = client;
		this.gui = gui;
		ois = new ObjectInputStream(client.getInputStream());
		oos = new ObjectOutputStream(client.getOutputStream());
	}//const

	@Override
	public void run() {
		while(connect){
			try {
				Data data = (Data) ois.readObject();
				switch(data.getCommand()){
				
				}
				
				
			} catch (ClassNotFoundException | IOException e) {
				connect = false;
				gui.appendLog(client.getInetAddress() + "클라이언트 접속종료");
			}//t-c
		}//while
	}//run()
	
	public void boradCasting(){
		
	}
	
	public void privateCasting(){
		
	}

}//class
