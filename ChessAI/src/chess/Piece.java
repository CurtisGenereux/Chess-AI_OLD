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
	
	public boolean isMoveLegal() {
		return isMoveLegal;
	}
	
	public void pickUp() {
		this.originalX = this.xPosition;
		this.originalY = this.yPosition;
	}
	
	
	public void move(int xPosition, int yPosition, boolean isLightPiece) {
		
		Piece targetPiece = Main.getPiece(xPosition, yPosition);
		
		if (targetPiece == null) {
			return;
		}
		
		if ((targetPiece.xPosition == xPosition) && (targetPiece.yPosition == yPosition)) {
			if ((targetPiece.isLightPiece != isLightPiece)) {
				pieces.remove(targetPiece); 
				isMoveLegal = true;
			} else {
				isMoveLegal = false;
				return;
			}
		} else {
			this.xPosition = xPosition;
			this.yPosition = yPosition;
		}
		
		if ((xPosition >= 0 && yPosition >= 0) && (xPosition <= 7 && yPosition <= 7)) {
			this.originalX = this.xPosition;
			this.originalY = this.yPosition;
			isMoveLegal = true;
		}	
		
	}
	
}
