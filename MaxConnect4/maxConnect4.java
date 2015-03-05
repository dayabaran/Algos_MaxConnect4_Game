import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class of the game
 * 
 * @author Dayabaran
 * 
 */
public class maxConnect4 {

	/**
	 * @param args
	 */
	PlayMode playMode;
	String playFirst = null;
	String color = null;
	String input = null;
	static PlayerIdentifier openPlay;

	public static void main(String[] args) {

		maxConnect4 game = new maxConnect4();
		try {
			game.play(args);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void play(String args[]) throws IOException {
		if(!(args.length>0)){
			System.out.println("\nMode not entered. \n Please enter the mode and required input or output files");
			System.exit(0);
		}
		String game_mode = args[0].toString();
		playMode = parsePlayMode(game_mode);
		BoardWriter bpnew = new BoardWriter();
		bpnew.setPlayMode(playMode);
		validateArgs(args,playMode);
		input = args[1].toString();

		// Initialize GameBoard
		BoardReader boardReader = new BoardReader();
		GameBoard currentGame = boardReader.readGame(input);

		// Read other input arguments

		if (playMode.equals(PlayMode.HUMAN_COMPUTER)) {
			Scanner in = new Scanner(System.in);
			System.out.println("================================");
			System.out.println("Would you like to play first?");
			System.out
					.println("Yes/No (Computer plays first if No is selected)");
			playFirst = in.nextLine();
			System.out.println("Choose your color: - Red (R)/ Green(G)");
			BoardWriter bp = new BoardWriter();
			bp.setHumanColorChoice(in.nextLine());

		}
		// create the Ai Player
		Player calculon = new AiPlayer();
		Player human = new HumanPlayer();

		// variables to keep up with the game
		int playColumn = 99; // the players choice of column to play
		switch (playMode) {
		case HUMAN_COMPUTER:
			PlayerIdentifier nextTurnEnum = getFirstPlayer(playFirst);
			openPlay = getFirstPlayer(playFirst);
			printInitialGameBoardState(currentGame);
			System.out.println("\nPlayer "
					+ currentGame.getCurrentTurnBasedOnNumberOfPlays()
					+ "'s Turn");
			while (currentGame.getCountOfPiecesPlayed() < 42) {
				System.out
						.println("\n--------------------------------------------------------------------------------\n");

				switch (nextTurnEnum) {
				case HUMAN:
					playColumn = human.getBestPlay(currentGame, 7);
					currentGame.playPieceInColumn(playColumn);
					nextTurnEnum = PlayerIdentifier.COMPUTER;
					break;

				case COMPUTER:
					System.out.println("\n\n Computer playing as player: "
							+ currentGame.getCurrentTurnBasedOnNumberOfPlays());

					playColumn = calculon.getBestPlay(currentGame, 7);

					System.out.println(" Computer's move in column no: "
							+ (playColumn + 1));

					// play the piece
					currentGame.playPieceInColumn(playColumn);

					nextTurnEnum = PlayerIdentifier.HUMAN;
					break;

				default:
					System.out.println("Couldn't decipeher whose turn it is");
					System.exit(0);
				}
				printCurrentGameBoardAndScores(currentGame);

				// reset playColumn and playMade
				playColumn = 99;

			} // end while
			System.out.println("\nThe Board is Full\n\nGame Over");
			// the game board is full.
			printTheFinalGameState(currentGame);
			break;
		case COMPUTER_COMPUTER:
			// get the output file name
			String output = args[2].toString(); // the output game file

			printInitialGameBoardState(currentGame);

			if (currentGame.getCountOfPiecesPlayed() < 42) {

				// Tell the user which player the computer is playing for
				System.out.println("\n\n Playing as player: "
						+ currentGame.getCurrentTurnBasedOnNumberOfPlays());
				playColumn = calculon.getBestPlay(currentGame, new Random().nextInt(8-1)+1);
				currentGame.playPieceInColumn(playColumn);

			} else {
				System.out
						.println("\nI can't play.\nThe Board is Full\n\nGame Over");
			}

			printCurrentGameBoardAndScores(currentGame);

			BoardWriter boardWriter = new BoardWriter();
			boardWriter.printGameBoardToFile(output, currentGame);
			break;
		default:
			break;
		}
	}

	private void printInitialGameBoardState(GameBoard currentGame) {
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println("\nMax Connect - " + playMode + " Mode\n");
		printBoardAndScores(currentGame);
	}

	protected void printBoardAndScores(GameBoard currentGame) {
		BoardWriter boardWriter = new BoardWriter();
		boardWriter.printGameBoard(currentGame);
		printCurrentScores(currentGame);
	}

	protected void printCurrentScores(GameBoard currentGame) {
		if (playMode == PlayMode.HUMAN_COMPUTER) {
			if (openPlay == PlayerIdentifier.HUMAN) {
				System.out.println("Scores:\n Player(me): "
						+ currentGame.getScore(1) + "\n Computer: "
						+ currentGame.getScore(2) + "\n ");

			} else {
				System.out.println("Scores:\n Computer: "
						+ currentGame.getScore(1) + "\n Player(me): "
						+ currentGame.getScore(2) + "\n ");
			}
		} else {
			System.out.println("Scores:\n Player1: " + currentGame.getScore(1)
					+ "\n Player2: " + currentGame.getScore(2) + "\n ");
		}
	}

	public void setHumanColorOption(String option) {
		if ((option.equalsIgnoreCase("red")) || (option.startsWith("r"))) {
			color = "R";
		} else {
			color = "G";
		}
	}

	public String getHumanColorOption() {
		return color;
	}

	private PlayerIdentifier getFirstPlayer(String option) {
		PlayerIdentifier firstPlayer = null;
		if ((option.equalsIgnoreCase("Yes")) || (option.startsWith("Y"))) {
			firstPlayer = PlayerIdentifier.HUMAN;

		} else {
			firstPlayer = PlayerIdentifier.COMPUTER;
		}
		BoardWriter bp = new BoardWriter();
		bp.setFirstPlayer(firstPlayer);
		return firstPlayer;
	}

	private void validateArgs(String[] args, PlayMode mode) {
		if(mode.equals(PlayMode.HUMAN_COMPUTER)){
		if (args.length != 2) {
			System.out
					.println("A command-line argument is missing:\n"
							+ "Usage: java [program name] human-computer [input_file] \n");

			System.exit(0);
		}
		}else{
			if(args.length!=3){
				System.out
				.println("A command-line argument is missing:\n"
						+ "Usage: java [program name] computer-computer [input_file] [output-file]\n");

		System.exit(0);
			}
		}

	}

	protected void printCurrentGameBoardAndScores(GameBoard currentGame) {
		System.out.println("\nThe layout/status of the board now is: \n");
		printBoardAndScores(currentGame);
	}

	private void printTheFinalGameState(GameBoard currentGame) {
		System.out.println("Here is the final game state\n");
		printBoardAndScores(currentGame);
		if (currentGame.getScore(1) > currentGame.getScore(2)) {
			System.out.println("Player 1 wins!");
		} else if (currentGame.getScore(1) < currentGame.getScore(2)) {
			System.out.println("Player 2 wins !");
		} else {
			System.out.println("Match Drawn!");
		}
	}

	protected PlayMode parsePlayMode(String input) {
		validateGameModeArgument(input);
		PlayMode mode = (input.equalsIgnoreCase("human-computer")) ? PlayMode.HUMAN_COMPUTER
				: PlayMode.COMPUTER_COMPUTER;
		return mode;
	}

	protected void validateGameModeArgument(String playMode) {
		if (!(playMode.equalsIgnoreCase("human-computer") || playMode
				.equalsIgnoreCase("computer-computer"))) {
			throw new IllegalArgumentException(playMode);
		}
	}
}
