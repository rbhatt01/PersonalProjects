/* 
 * Rashmika Bhatt period 6 2/9/18 
 * This lab was pretty difficult but it was much easier since we had knight's tour one done. Currently my lab is not working because I am receiving an unkown error. Other than that for the 
 * accessibility array, at first my program would have the same score and pattern each time because when the lowest accessible numbers were the same 
 * it would always pick the same one. I tried to add a random picking method into this in which all the smallest accessible moves are put into an Arraylist from which a random index is picked
 * which corresponds to a case in the move method and thus moves the knight there. That is where I am having the problem now. I input in the move function the integer at a random index of 
 * the arraylist which hold all the possible moves with the smallest accessibility and it is giving an unknown error.  
 */

import java.util.Random; 
import java.util.ArrayList; 
public class P6_Bhatt_Rashmika_KnightsTour2  {
	public static int [] [] accessibilityArray = new int [8][8]; 
	public static int [][] chessBoard = new int [8][8]; 
	public static int score = 1; 
	public static void main (String [] args) {
		createAccessArray(); 
		createBoard(); 
		while(hasMoves()) {
			move();  
		}
		if(score == 64) {
			System.out.println("Success!");
		} else {
			System.out.println("Failed. Score: " + score); 
		}
		for(int i = 0; i <8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(chessBoard[i][j] + " " );
			}
			System.out.println(); 
		}
		
		for(int i = 0; i <8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(accessibilityArray[i][j] + " " );
			}
			System.out.println(); 
		}
	
	}
	
	public static void createBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				chessBoard[i][j] = 0; 
			}
		}
		chessBoard[0][0] = 1; 
	}
	public static boolean hasMoves() { 
		int row = 0; 
		int col = 0; 
		for(int i = 0; i < 8; i++) { 
			for(int j = 0; j < 8; j++) {
				if(chessBoard[i][j] == score) {
					row = i; 
					col = j;
					break; 
				}
			}
		}
		boolean ans = false; 
		if(isValidMoveChess(row + 2, col + 1)) {
			ans = true; 
		} else if(isValidMoveChess(row + 2, col - 1)) {
			ans = true; 
		} else if(isValidMoveChess(row -2, col + 1)) {
			ans = true; 
		} else if(isValidMoveChess(row - 2, col - 1)) {
			ans = true; 
		} else if(isValidMoveChess(row + 1, col - 2)) {
			ans = true; 
		} else if(isValidMoveChess(row + 1, col + 2)) {
			ans = true; 
		} else if(isValidMoveChess(row - 1, col + 2)) {
			ans = true; 
		} else if(isValidMoveChess(row - 1, col - 2)) {
			ans = true; 
		} 
		 return ans; 
	}
	
	public static void move() { 
		int row = 0; 
		int col = 0; 
		for(int i = 0; i < 8; i++) { 
			for(int j = 0; j < 8; j++) {
				if(chessBoard[i][j] == score) {
					row = i; 
					col = j;
					break; 
				}
			}
		}
		findMove(row , col); 
	}
	
	//checks if the move in the chessboard is valid 
	public static boolean isValidMoveChess(int row, int col) { 
		try { 
			if(chessBoard[row][col] == 0) {
				return true;
			} else { 
				return false; 
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false; 
		}
	}
	
	public static void createAccessArray() { 
		accessibilityArray[0][0] = 2; 
		accessibilityArray[0][1] = 3; 
		for(int i = 2; i <= 5; i++) {
			accessibilityArray[0][i]= 4;
		}
		accessibilityArray[0][6] = 3; 
		accessibilityArray[0][7] = 2; 
		accessibilityArray[1][0] = 3;
		accessibilityArray[1][1] = 4; 
		for(int i = 2; i <=5 ; i++) { 
			accessibilityArray[1][i] = 6;
		}
		accessibilityArray[1][6]= 4;
		accessibilityArray[1][7] = 3; 
		for(int i = 2; i <=5; i++) { 
			accessibilityArray[i][0]= 4;
			accessibilityArray[i][1] = 6;
		}
		
		for(int i = 2; i<= 5; i++) { 
			for(int j = 2; j<= 5; j++) {
				accessibilityArray[i][j] = 8; 
			}
		}
		for(int i = 2; i <= 5; i ++) {
			accessibilityArray[i][6] = 4;
			accessibilityArray[i][7] = 4;
		}
		accessibilityArray[6][0] = 3; 
		accessibilityArray[6][1] = 4; 
		for(int i = 2; i <=5; i ++) {
			accessibilityArray[6][i] = 6; 
		}
		accessibilityArray[6][6] = 4;
		accessibilityArray[6][7] = 3;
		accessibilityArray[7][0] = 2;
		accessibilityArray[7][1] = 3; 
		for(int i = 2; i <= 5; i++) { 
			accessibilityArray[7][i] = 4;
		}
		accessibilityArray[7][6] = 3; 
		accessibilityArray[7][7] = 2; 
	}
	
	public static void findMove(int row, int col) {
		int min = 8; 
		accessibilityArray[row][col] = 0; 
		if(isValidMoveAccess(row + 2, col + 1)) {
			accessibilityArray[row + 2] [col + 1]--;
			if(accessibilityArray[row + 2][col + 1] < min) { 
				min = accessibilityArray[row + 2][col + 1]; 
			}
		} else if(isValidMoveAccess(row + 2, col - 1)) {
			accessibilityArray[row + 2] [col - 1]--; 
			if(accessibilityArray[row + 2][col - 1] < min) { 
				min = accessibilityArray[row + 2][col - 1]; 
			}
			
		} else if(isValidMoveAccess(row -2, col + 1)) {
			accessibilityArray[row - 2] [col + 1]--; 
			if(accessibilityArray[row - 2][col + 1] < min) { 
				min = accessibilityArray[row - 2][col + 1]; 
			}
		} else if(isValidMoveAccess(row - 2, col - 1)) {
			accessibilityArray[row - 2] [col - 1]--; 
			if(accessibilityArray[row - 2][col - 1] < min) { 
				min = accessibilityArray[row - 2][col - 1]; 
			}
		} else if(isValidMoveAccess(row + 1, col - 2)) {
			accessibilityArray[row + 1][col - 2]--; 
			if(accessibilityArray[row + 1][col - 2] < min) { 
				min = accessibilityArray[row + 1][col - 2]; 
			}
		} else if(isValidMoveAccess(row + 1, col + 2)) {
			accessibilityArray[row + 1][col + 2]--; 
			if(accessibilityArray[row + 1][col + 2] < min) { 
				min = accessibilityArray[row + 1][col + 2];
			}
		} else if(isValidMoveAccess(row - 1, col + 2)) {
			accessibilityArray[row - 1][col + 2]--; 
			if(accessibilityArray[row - 1][col + 2] < min) { 
				min = accessibilityArray[row -1][col + 2]; 
			}
		} else if(isValidMoveAccess(row - 1, col - 2)) {
			accessibilityArray[row - 1] [col - 2]--; 
			if(accessibilityArray[row - 1][col - 2] < min) { 
				min = accessibilityArray[row - 1][col - 2]; 
			}
		} 
		ArrayList <Integer> randomCase = new ArrayList<Integer> (); 
		if(isValidMoveChess(row + 2, col + 1) && min == accessibilityArray[row + 2][col + 1]) { 
			randomCase.add(7); 
		}
		if(isValidMoveChess(row + 2, col - 1) && min == accessibilityArray[row + 2][col - 1]) {
			randomCase.add(8); 
		}
		if(isValidMoveChess(row - 2, col + 1) && min == accessibilityArray[row - 2] [col + 1]) {
			randomCase.add(3); 
		}
		if(isValidMoveChess(row - 2, col - 1) && min == accessibilityArray[row - 2][col - 1]) {
			randomCase.add(4); 
		}
		if(isValidMoveChess(row + 1, col - 2) && min == accessibilityArray[row + 1][col - 2]) {
			randomCase.add(2); 
		}
		if(isValidMoveChess(row + 1, col + 2) && min == accessibilityArray[row + 1][col + 2]) { 
			randomCase.add(5); 
		} 
		if(isValidMoveChess(row - 1, col + 2) && min == accessibilityArray[row - 1][col + 2]) {
			randomCase.add(1); 
		}
		if(isValidMoveChess(row - 1, col - 2) && min == accessibilityArray[row - 1] [col - 2]) {
			randomCase.add(6); 
		}
		
		Random ran = new Random(); 
		int index = ran.nextInt(randomCase.size()); 
		
		int ans = randomCase.get(index); 
		move(ans);
		
	}
	public static void move(int caseNumber) { 
		int row = 0; 
		int col = 0; 
		for(int i = 0; i < 8; i++) { 
			for(int j = 0; j < 8; j++) {
				if(chessBoard[i][j] == score) {
					row = i; 
					col = j;
					break; 
				}
			}
		}
		switch (caseNumber) {
		case 1: 
			//move up one right 2 
				score++;
				chessBoard[row -1][col + 2] = score; 
				break; 
		
		case 2:
			//move up one left 2
				score++;
				chessBoard[row - 1] [col -2] = score; 
				break;
		case 3: 
			//move up 2 right 1  
				score++;
				chessBoard[row -2][col + 1] = score; 
				break; 
		case 4: 
			//move up 2 left 1
				score++;
				chessBoard[row -2] [col -1] = score; 
				break; 
		case 5: 
			//move down one right 2
				score++;
				chessBoard[row + 1] [col + 2] = score; 
				break;
		case 6: 
			//move down one left 2
				score++;
				chessBoard[row + 1][col - 2] = score; 
				break; 
		case 7: 
			//move down 2 right 1
				score++;
				chessBoard[row + 2] [col + 1] = score; 
				break; 
		case 8: 
			//move down 2 left 1
				score++;
				chessBoard[row + 2][col - 1] = score; 
				break; 
		}
	}
	public static boolean isValidMoveAccess(int row, int col) { 
		try { 
			if(accessibilityArray[row][col] != 0) {
				return true;
			} else { 
				return false; 
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false; 
		}
	}
	
}
