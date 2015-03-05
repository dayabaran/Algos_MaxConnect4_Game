
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/** Class that writes or prints the board and its status
 * @author Dayabaran
 *
 */
public class BoardWriter {
	
	static String colorChoice = null;
	static PlayerIdentifier firstPlayer = null;
	static PlayMode mode = null;
	
	/** Prints the gameboard to the console
	 * @param gameboard
	 */
	public void printGameBoard(GameBoard gameboard) {
		System.out.println("   1 2 3 4 5 6 7   <===  Column nos\n -----------------");
		
		int[][] playBoard = gameboard.getGameBoard();

		for( int i = 0; i < 6; i++ ) {
			System.out.print("_| ");
			for( int j = 0; j < 7; j++ ) {
				if( playBoard[i][j] == 0 ) {
					System.out.print("  ");
				} else {
					if(playBoard[i][j]==1){
						if(mode.equals(PlayMode.HUMAN_COMPUTER)){
						
						if(firstPlayer==PlayerIdentifier.COMPUTER){
							if(colorChoice.equals("R")){
								System.out.print( "G" + " " );
							}else{
								System.out.print( "R" + " " );
							}
						}else{
							System.out.print( colorChoice + " " );
						}
						
						}else{
							System.out.print( playBoard[i][j] + " " );

						}
						
					}else{
						
						if(mode.equals(PlayMode.HUMAN_COMPUTER)){
						
					if(firstPlayer!=PlayerIdentifier.COMPUTER){
							if(colorChoice.equals("R")){
								System.out.print( "G" + " " );
							}else{
								System.out.print( "R" + " " );
							}
						}else{
							System.out.print( colorChoice + " " );
						}
					
						}else{
							System.out.print( playBoard[i][j] + " " );
						}
					}
				}
			}
			System.out.print("|_\n");
		}
		System.out.println(" -----------------\n   1 2 3 4 5 6 7   <===Column nos");
	}

	/** Sets the color choice of the piece chosen by the user
	 * @param option
	 */
	public void setHumanColorChoice(String option){
		char val=option.charAt(0);
		String value = Character.toString(val);
		if((value.equalsIgnoreCase("r"))){
			colorChoice="R";
		}else{
			colorChoice="G";
		}
	}
	public String getHumanColorChoice(){
		return colorChoice;
	}
	/**
	 * this method prints the GameBoard to an output file to be used for
	 * inspection or by another running of the application
	 * @param outputFile the path and file name of the file to be written
	 */
	public void printGameBoardToFile(String outputFile, GameBoard gameboard)
		throws IOException {
		
		BufferedWriter output = new BufferedWriter( new FileWriter( outputFile ) );
		int[][] playBoard = gameboard.getGameBoard();

		for( int i = 0; i < 6; i++ ) {
			for( int j = 0; j < 7; j++ ) {
				output.write( playBoard[i][j] + 48 );
			}
			output.write("\n");
		}
		output.close();
	}

	
	public void setPlayMode(PlayMode playmode){
		mode = playmode;
	}
	/** Sets the player who plays the game first
	 * @param firstPlayer2
	 */
	public void setFirstPlayer(PlayerIdentifier firstPlayer2) {
		firstPlayer = firstPlayer2;
	}
}
