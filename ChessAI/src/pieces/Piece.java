package pieces;

import java.util.LinkedList;

import chess.Main;

public class Pawn extends Piece {
	
	public Pawn(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		super(xPosition, yPosition, name, isLightPiece, pieces);
	}
	
	public void fillMoveList() {
		
		legalMoveList.clear();
		
		System.out.println("list is empty " + legalMoveList.isEmpty());
		
		LegalMove move;
		
		if (isLightPiece) {
			if (originalY == 6) {
				move = new LegalMove(originalX, originalY - 2);
				legalMoveList.add(move);
				System.out.println("adding a move!");
			}
			if (Main.getPiece(originalX, originalY - 1) == null) {
				move = new LegalMove(originalX, originalY - 1);
				legalMoveList.add(move);
				System.out.println("adding a move!");
			}
			
			if ((Main.getPiece(originalX + 1, originalY - 1) != null) 
			&& (Main.getPiece(originalX + 1, originalY - 1).isLightPiece == false)) {	
				move = new LegalMove(originalX + 1, originalY - 1);
				legalMoveList.add(move);
				System.out.println("adding a move!");
			}
			if ((Main.getPiece(originalX - 1, originalY - 1) != null) 
			&& (Main.getPiece(originalX - 1, originalY - 1).isLightPiece == false)) {
				move = new LegalMove(originalX - 1, originalY - 1);
				legalMoveList.add(move);
				System.out.println("adding a move!");
			}
 
		}
	
		System.out.println("legalMoveList.size " + legalMoveList.size());

		updateOriginalPosition();
		
	}
}
