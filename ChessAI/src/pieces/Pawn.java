package pieces;

import java.util.LinkedList;
import java.util.Iterator;

import chess.Main;

public class Pawn extends Piece {
	
	public Pawn(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		super(xPosition, yPosition, name, isLightPiece, pieces);
	}
	
	public void checkUniqueMoves() {
		
		LegalMove move;

		if (isLightPiece) {
			if (originalY == 6) {
				if (xPosition == originalX && yPosition == originalY - 2) {
					move = new LegalMove(originalX, originalY - 2);
					legalMoveList.add(move);
				}
			}
			if (xPosition == originalX && yPosition == originalY - 1) {
				move = new LegalMove(originalX, originalY - 1);
				legalMoveList.add(move);
			}

		} else {
			if (originalY == 1) {
				if (xPosition == originalX && yPosition == originalY + 2) {
					move = new LegalMove(originalX, originalY + 2);
					legalMoveList.add(move);
				}
			}
			if (xPosition == originalX && yPosition == originalY + 1) {
				move = new LegalMove(originalX, originalY + 1);
				legalMoveList.add(move);
			}
		}
		
		updateOriginalPosition();
		
		System.out.println("b4");
		
	}
}
