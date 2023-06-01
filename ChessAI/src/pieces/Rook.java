package pieces;

import java.util.LinkedList;

import chess.Main;

public class Rook extends Piece {

	public Rook(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		super(xPosition, yPosition, name, isLightPiece, pieces);
		// TODO Auto-generated constructor stub
	}
	
	public void checkUniqueMoves() {
			
		LegalMove move;

		for (int tempXPos = originalX; tempXPos <= 7; tempXPos++) {
			if (Main.getPiece(tempXPos, originalY) != null) {
				if (Main.getPiece(tempXPos, originalY).isLightPiece != isLightPiece) {
					move = new LegalMove(tempXPos, originalY);
					legalMoveList.add(move);
					break;
				} else {
					break;
				}
			} else {
				move = new LegalMove(tempXPos, originalY);
				legalMoveList.add(move);
			}
		}
		
		for (int tempXPos = originalX; tempXPos >= 0; tempXPos--) {
			if (Main.getPiece(tempXPos, originalY) != null) {
				if (Main.getPiece(tempXPos, originalY).isLightPiece != isLightPiece) {
					move = new LegalMove(tempXPos, originalY);
					legalMoveList.add(move);
					break;
				} else {
					break;
				}
			} else {
				move = new LegalMove(tempXPos, originalY);
				legalMoveList.add(move);
			}
		}
		

		for (int tempYPos = originalX; tempYPos <= 7; tempYPos++) {
			if (Main.getPiece(xPosition, yPosition) != null) {
				if (Main.getPiece(xPosition, yPosition).isLightPiece != isLightPiece) {
					move = new LegalMove(tempYPos, originalY);
					legalMoveList.add(move);
					break;
				} else {
					break;
				}
			} else {
				move = new LegalMove(tempYPos, originalY);
				legalMoveList.add(move);
			}
		}
		
		for (int tempYPos = originalX; tempYPos >= 0; tempYPos--) {
			if (Main.getPiece(xPosition, yPosition) != null) {
				if (Main.getPiece(xPosition, yPosition).isLightPiece != isLightPiece) {
					move = new LegalMove(tempYPos, originalY);
					legalMoveList.add(move);
					break;
				} else {
					break;
				}
			} else {
				move = new LegalMove(tempYPos, originalY);
				legalMoveList.add(move);
			}
		}
		
		updateOriginalPosition();
	}
}
