package pieces;

import java.util.LinkedList;
import java.util.Iterator;

import chess.Main;

public class Pawn extends Piece {
	
	public Pawn(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		super(xPosition, yPosition, name, isLightPiece, pieces);
	}
	
	LinkedList<int[]> legalMoveList = new LinkedList<>();
	
	public boolean checkLegalUniqueMoves() {
		
		System.out.println("N: [" + xPosition + "," + yPosition + "]");
		System.out.println("O: [" + originalX + "," + originalY + "]");
		
		int legalMoveCount = 0;
		
		int[] legalMoves = {};
		
		Piece targetPiece = Main.getPiece(xPosition, yPosition);
		
		if (isLightPiece) {
			if (originalY == 6) {
				if ((Main.getPiece(originalX, originalY - 2) == null)
				|| (!Main.getPiece(originalX, originalY - 2).isLightPiece)) {
					int[] move = {originalX, originalY - 2};
					legalMoveList.add(move);
				} else {
				if (Main.getPiece(originalX, originalY - 1) == null
				|| (!Main.getPiece(originalX, originalY - 1).isLightPiece)) {
					}
				}
			}

		} else {
			if ((Main.getPiece(originalX, originalY + 2) == null)
			|| (Main.getPiece(originalX, originalY + 2).isLightPiece)) {
				int[] move = {originalX, originalY + 2};
				legalMoveList.add(move);
			} else {
			if (Main.getPiece(originalX, originalY + 1) == null
			|| (Main.getPiece(originalX, originalY + 1).isLightPiece)) {
				}
			}
		}
		
		updateOriginalPosition();
		
		for (int[] move : legalMoveList) {
			System.out.println(move);
		}
		

		return true;
		
	}
}
		
