package pieces;

import chess.Main;
import java.util.LinkedList;

public class Piece {
	
	public LinkedList<Piece> pieces;
	public String name;
	public int xPosition;
	public int yPosition;
	public int originalX;
	public int originalY;
	public boolean isLightPiece;
	public boolean isMoveLegal = true;
	public static LinkedList<LegalMove> legalMoveList = new LinkedList<>();
	
	public Piece(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.originalX = xPosition;
		this.originalY = yPosition;
		this.pieces = pieces;
		this.isLightPiece = isLightPiece;
		this.name = name;
		pieces.add(this);
		
	}

	public Piece() {
		// TODO Auto-generated constructor stub
	}

	public boolean isMoveLegal() {
		return isMoveLegal;
	}
	
	public void updateOriginalPosition() {
		this.originalX = this.xPosition;
		this.originalY = this.yPosition;
	}
	
	public void move(int xPosition, int yPosition) {

		isMoveLegal = isMoveLegalCheck();
		if (isMoveLegal) {
			isMoveLegal = checkLegalUniqueMoves();
		}

		legalMoveList.clear();
		
		System.out.println("list has been cleared!");
		
		Piece targetPiece = Main.getPiece(xPosition, yPosition);
		
		if (!isMoveLegal) {
			this.xPosition = originalX;
			this.yPosition = originalY;
			this.xPosition = xPosition;
			this.yPosition = yPosition;
			return;
		}
		
		if (targetPiece == null) {
			this.xPosition = xPosition;
			this.yPosition = yPosition;
			return;
		}
		
		if ((targetPiece.xPosition == xPosition) && (targetPiece.yPosition == yPosition)) {
			pieces.remove(targetPiece);
			this.xPosition = xPosition;
			this.yPosition = yPosition;
		}
		
	}
	
	public void fillMoveList() {
		
	}
	
	public boolean checkLegalUniqueMoves() {
		
		System.out.println("========================================");
		for (LegalMove legalMove : legalMoveList) {
			if (xPosition == legalMove.xPosition && yPosition == legalMove.yPosition) {
				System.out.println("the move to: [" + xPosition + "," + yPosition + "] should be legal");
				return true;
			}
			System.out.println("[" + xPosition + "," + yPosition + "]" + "and " + "[" + legalMove.xPosition + "," + legalMove.yPosition + "]" + "do not match");
		}
		
		System.out.println("is the list empty? " + legalMoveList.isEmpty());
		System.out.println("bruh moment");
		System.out.println(false);
		
		return false;
	}
	
	public boolean isMoveLegalCheck() {
		
		Piece targetPiece = Main.getPiece(xPosition, yPosition);
		
		if (targetPiece == null) {
			if (xPosition < 0 || yPosition < 0 || xPosition > 7 || yPosition > 7) {
				return false;
			} else {
				return true;
			}
		}
		
		if ((targetPiece.xPosition != xPosition) || (targetPiece.yPosition != yPosition)) {
			if (xPosition < 0 || yPosition < 0 || xPosition > 7 || yPosition > 7) {
				return false;
			}
		}
		
		if ((targetPiece.xPosition == xPosition) && (targetPiece.yPosition == yPosition)) {
			if ((targetPiece.isLightPiece != isLightPiece)) {
				return true;
			} else {
				return false;
			}
		}
		
		return false;
		
	}
	
}