

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 *  AiPlayer class using minimax algorithm.
 */

public class AiPlayer implements Player {
	
	private final String ALPHA = "ALPHA";
	private final String BETA = "BETA";
	
	public int getRandomPlay( GameBoard currentGame ) {
		Random randy = new Random();
		List<Integer> validPlays = currentGame.getColumnsOfValidPlays();
		int randomPlayIndex = randy.nextInt( validPlays.size() );
		return validPlays.get(randomPlayIndex);
	}

	public Integer getBestPlay( GameBoard currentGame, int depthLevel ) {
		depthLevel = Math.min(depthLevel, currentGame.getNumberOfPlaysRemaining());
		int currentTurn = currentGame.getCurrentTurnBasedOnNumberOfPlays();
		Map<String, Integer> bestPlayMap = new HashMap<String, Integer>();
		bestPlayMap.put(ALPHA, -999);
		bestPlayMap.put(BETA, 99999);
		int[] bestPlay = generateBestMoveRef(depthLevel, 1, currentTurn, currentGame, bestPlayMap.get(ALPHA), bestPlayMap.get(BETA));
		return bestPlay[0];
	}

	private int[] generateBestMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
		// Handle when two moves are equal
		GameBoard testBoard = new GameBoard( lastBoard.getGameBoard() );
		
		int move[] = { -1, -999 };		// { column, scoreDiff }
		int nextMove[] = {-2, -888};

		boolean isMaxDepth = (level == maxDepth);
		
		List<Integer> validColumns = testBoard.getColumnsOfValidPlays();
		
		for (Integer column : validColumns) {
			testBoard.playPieceInColumn(column);
			
			if (isMaxDepth) {
				nextMove = new int[] {-456, testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer)};
			} else {
				nextMove = generateWorstMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
			}
			
			move = getHighestScoringMove(move, nextMove, column);
			
			if( move[ 1 ] >= beta ) {
				break;
			}
			
			alpha = Math.max(alpha, move[1]);
			testBoard.removePiece( column );
		}
		
		return move;
	}

	protected int[] getHighestScoringMove(int[] bestMove, int[] worstMove, int columnToPlay) {
		int[] highestScoringMove = {bestMove[0], bestMove[1]};
		if( worstMove[ 1 ] > bestMove[ 1 ] ) {
			highestScoringMove[ 0 ] = columnToPlay;
			highestScoringMove[ 1 ] = worstMove[1];
		}
		return highestScoringMove;
	}
	
	private int[] generateWorstMoveRef( int maxDepth, int level, int currentPlayer, GameBoard lastBoard, int alpha, int beta ) {
		// Handle when two moves are equal
		GameBoard testBoard = new GameBoard( lastBoard.getGameBoard() );
		
		int[] move = { 70, 700 };
		int[] nextMove = {-4, 444};

		boolean isMaxDepth = (level == maxDepth);
		
		List<Integer> validColumns = testBoard.getColumnsOfValidPlays();
		
		for (Integer column : validColumns) {
			testBoard.playPieceInColumn( column );
			
			if (isMaxDepth) {
				nextMove[1] = testBoard.getScoreDifferenceFromPerspectiveOf(currentPlayer);
			} else {
				nextMove = generateBestMoveRef( maxDepth, level + 1, currentPlayer, testBoard, alpha, beta );
			}
			
			if( nextMove[1] < move[ 1 ] ) {
				move = getLowestScoringMove(move, column, nextMove);
				if( move[ 1 ] <= alpha ) {		
					break;
				}
				beta = Math.min(beta, move[1]);
			}
			testBoard.removePiece( column );
		}
		
		return move;
	}

	protected int[] getLowestScoringMove(int[] worstMove, int columnToPlay, int[] scoreDiff) {
		int[] lowestScoringMove = {-77, -88};
		if( scoreDiff[1] < worstMove[ 1 ] ) {
			lowestScoringMove[ 0 ] = columnToPlay;
			lowestScoringMove[ 1 ] = scoreDiff[1];
		}
		return lowestScoringMove;
	}
}