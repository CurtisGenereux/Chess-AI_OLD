package pieces;

import java.util.LinkedList;

public class Pawn extends Piece {
	
	public Pawn(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		super(xPosition, yPosition, name, isLightPiece, pieces);
	}
	
	public boolean checkLegalUniqueMoves() {
		
		System.out.println(yPosition);
		System.out.println(isLightPiece);
		
		if (isLightPiece && yPosition >= 5) {
			
			System.out.println("here");
			return true;

			
		} else if (!isLightPiece && yPosition <= 2) {
			return true;
		}
	
		return false;
		
	}

}
