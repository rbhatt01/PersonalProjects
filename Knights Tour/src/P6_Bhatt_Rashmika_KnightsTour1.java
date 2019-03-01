import java.util.Random; 
import java.util.Arrays; 
/* 
 * Rashmika Bhatt Period 6 2/5/18 
 * This lab was pretty difficult. I currently have a plan laid out as to how to solve this problem. Infact the whole main method is completely down but the helper method
 * specifically move is not being carried out currently. My biggest problem is figuring out what places are open for the knight to move once that is done I have a number 
 * of switch cases that the randomizer will randomly choose from and then move rhe knight to an open place. Furthermore I feel my has moves method is also really inefficient
 * and I want to fix that. Once the move method is complete the lab should work as promised. What i have so far took my about 3 hours to work out. 
 * 
 * Rashmika Bhatt Period 6 2/7/18 
 * The lab is now working completely. Some things I realized was instead of having to check all the possible out of bounds errors, I could just catch it and if there is an 
 * out of bounds error then the valid move method would return false. This implementation of a valid moves method also helped my hasmoves method since that is where I was 
 * doing all the crazy out of bounds checks. This helped me worry about the eight possible places a knight can move and then check if it is a valid move and even if one is 
 * then the has moves method would return true and the main method would continue to run. This is valid method also helped in the move method as it would then ensure
 * that the knight only moves to valid spots. 
 */
public class P6_Bhatt_Rashmika_KnightsTour1 {
	public static int [][] chessBoard = new int [8][8]; 
	public static int score = 1; 
	public static void main (String [] args) {
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
		if(isValidMove(row + 2, col + 1)) {
			ans = true; 
		} else if(isValidMove(row + 2, col - 1)) {
			ans = true; 
		} else if(isValidMove(row -2, col + 1)) {
			ans = true; 
		} else if(isValidMove(row - 2, col - 1)) {
			ans = true; 
		} else if(isValidMove(row + 1, col - 2)) {
			ans = true; 
		} else if(isValidMove(row + 1, col + 2)) {
			ans = true; 
		} else if(isValidMove(row - 1, col + 2)) {
			ans = true; 
		} else if(isValidMove(row - 1, col - 2)) {
			ans = true; 
		} 
		 return ans; 
	}
	
	public static void move() { 
		Random ran = new Random(); 
		int movement = ran.nextInt(8) + 1; 
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
		switch (movement) {
		case 1: 
			//move up one right 2 
			if(isValidMove(row - 1, col + 2)) {
				score++;
				chessBoard[row -1][col + 2] = score; 
				break; 
			} 
		case 2:
			//move up one left 2
			if(isValidMove(row - 1, col - 2)) {
				score++;
				chessBoard[row - 1] [col -2] = score; 
				break;
			} 
		case 3: 
			//move up 2 right 1 
			if(isValidMove(row - 2, col + 1)) { 
				score++;
				chessBoard[row -2][col + 1] = score; 
				break; 
			}
		case 4: 
			//move up 2 left 1
			if(isValidMove(row - 2, col - 1)) { 
				score++;
				chessBoard[row -2] [col -1] = score; 
				break; 
			}
		case 5: 
			//move down one right 2
			if(isValidMove(row + 1, col + 2)) {
				score++;
				chessBoard[row + 1] [col + 2] = score; 
				break;
			}
		case 6: 
			//move down one left 2
			if(isValidMove(row + 1, col - 2)) {
				score++;
				chessBoard[row + 1][col - 2] = score; 
				break; 
			}
		case 7: 
			//move down 2 right 1
			if(isValidMove(row + 2, col + 1)) {
				score++;
				chessBoard[row + 2] [col + 1] = score; 
				break; 
			}
		case 8: 
			//move down 2 left 1
			if(isValidMove(row + 2, col - 1)) { 
				score++;
				chessBoard[row + 2][col - 1] = score; 
				break; 
			} else {
				move(); 
			}
		}
		
	}
	
	public static boolean isValidMove(int row, int col) { 
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
	
	
	
	
}
