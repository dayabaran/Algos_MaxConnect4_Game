
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class HumanPlayer implements Player {

	@Override
	public Integer getBestPlay(GameBoard currentGame, int depthLevel) {
		BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		String userInput = null;
		Character userChar = 'x';
		int playColumn = -1;
		boolean validPlayFound = false;
		do {
			
			System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
			try {
				userInput = br.readLine();
			} catch (Exception e) {
			}
			
			// Handle empty input
			while( ( userInput.equals("") ) ) {
				System.out.println("\nInvalid Input\n--Try again" );
				System.out.print("\nChoose a Column ( 1 - 7 ) -->:");
				// try getting input fromt the user again
				try {
					userInput = br.readLine();
				} catch (Exception e) {
				}
			}
			
			// we only care about the first character
			userChar = userInput.charAt( 0 );
				
			
			// check to see if the input is a digit						
			if( Character.isDigit( userChar )) {
				// parse the user input into something we can use
				playColumn = Character.getNumericValue( userChar ) - 1;
				if( currentGame.isValidPlay( playColumn ) ) {
					validPlayFound = true;								
				} else {
					System.out.println("That was an invalid play\n--Try again!");
				}
				
				
			} else {
				System.out.println("\nThat was not a valid digit.\n--Try again");
			} // end digit check
		} while ( !validPlayFound );
		
		return playColumn;
	}

}
