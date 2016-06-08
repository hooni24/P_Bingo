package bingo.data;

import java.io.Serializable;

public class GameInfo implements Serializable {
	private String keyword; //본인이 선택하거나, 상대방이 선택한 빙고 단어
	private String bingoKeywords [][] = new String[5][5]; //입력한 25개의 빙고 단어 저장
	
	private int bingoResult [][] = new int[5][5];	//빙고 현황(결과)를 담는 배열, 내가 선택하거나 상태방이 선택한 빙고 단어와 일치되는 위치의 값을 1로 초기화 한다.
	private int x, y; //상대방이 선택한 빙고 단어가 내가 입력한 빙고 단어와 일치하는 것이 발견될 경우 해당 단어의 배열(5X5)상의 위치 값
	private User user; //자기자신에 대한 정보를 갖는 User

	public String[][] getBingoKeyword() {
		return bingoKeywords;
	}

	public void setBingoKeywords(String[][] bingoKeyword) {
		this.bingoKeywords = bingoKeyword;
	}
	
	/**
	 * 인자로 전달된 빙고 단어를 가지고 빙고 결과를 담는 배열을 1로 초기화 한다.
	 * 사용자가 입력한 빙고 단어와 일치하는 단어가 있으면 bingoResult 배열의 해당 위치값을 1로 초기화 하고, 해당 배열의 위치 값으로 x,y 변수를 초기화 한다.
	 * 매개변수로 전달된 단어와 일치하는 단어가 있으면 true를, 그렇지 않으면 false를 반환한다.
	 * */
	public boolean markBingoResult(String keyword){
		boolean result = false;
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				if (bingoKeywords[i][j].equalsIgnoreCase(keyword)) {
					bingoResult[i][j] = 1;
					result = true;
					x = i;
					y = j;
				}
			}
		}
		return result;
	}
	
	/**
	 * 빙고 결과를 담는 배열로부터 빙고 개수(가로,세로,대각선 연속하여 5개의 빙고단어를 맞춘 개수) 계산하여 반환한다.
	 * */
	public int checkBingo(){
		int bingoNum = 0;
		int chk = 0;							//줄별로 bingoResult 합 구할 변수
		
		for(int i = 0; i < 5; i++){				//가로 세는거
			for(int j = 0; j < 5; j++){
				chk += bingoResult[i][j];
			}
			if(chk == 5) bingoNum++;
			chk =0;
		}
		
		for(int i = 0; i < 5; i++){				//세로 세는거
			for(int j = 0; j < 5; j++){
				chk += bingoResult[j][i];
			}
			if(chk == 5) bingoNum++;
			chk =0;
		}
		
		int chk2 =0;
		for(int i = 0; i < 5; i++){				//대각 세는거
			for(int j = 0; j < 5; j++){
				if(i == j) chk += bingoResult[i][j];		//좌상에서 우하
				if(i+j == 4) chk2 += bingoResult[i][j];		//우상에서 좌하
			}
		}
		if(chk == 5) bingoNum++;		//좌상에서 우하
		if(chk2 == 5) bingoNum++;		//우상에서 좌하

		return bingoNum;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int[][] getBingoResult() {
		return bingoResult;
	}
	
	
	
//	public static void main(String[] args) {		//실험용 메인메소드
//	GameInfo game = new GameInfo();
//	String[][] test1 = {{"1","2","3","4","5"},{"6","7","8","9","10"},{"11","12","13","14","15"},{"16","17","18","19","20"},{"21","22","23","24","25"}};
//	game.bingoKeywords = test1;
//	System.out.println(game.markBingoResult("25"));
//	
//	int[][] test2 = {{1,1,1,1,1},
//					 {1,0,1,1,0},
//					 {1,0,1,1,0},
//					 {1,1,1,1,1},
//					 {1,0,1,1,0}};
//	game.bingoResult = test2;
//	System.out.println("빙고갯수 : " + game.checkBingo());
//}

	
}
