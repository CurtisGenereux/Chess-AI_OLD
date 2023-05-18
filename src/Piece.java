package chess;

import java.util.Iterator;
import java.util.LinkedList;

public class Piece {
	
	int xPosition;
	int yPosition;
    	int originalX;
    	int originalY;
	LinkedList<Piece> pieces;
	String name;
	boolean isLightPiece;
	public boolean isMoveLegal = true;
	
	public boolean isMoveLegal() {
		return isMoveLegal;
	}
	
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
	
	public void pickUp() {
		this.originalX = this.xPosition;
		this.originalY = this.yPosition;
	}
	
	public void move(int xPosition, int yPosition, boolean isLightPiece) {
		
		this.isLightPiece = isLightPiece;
		
		Iterator<Piece> pieceList = pieces.iterator();
		Piece piece;
		
		while (pieceList.hasNext() == true) {
			piece = pieceList.next();
			if (piece != null && piece != this) {
				if ((piece.xPosition == xPosition) && (piece.yPosition == yPosition)) {
					if ((piece.isLightPiece != isLightPiece)) {
						pieceList.remove(); 
						this.xPosition = xPosition;
						this.yPosition = yPosition;
						isMoveLegal = true;
					} else {
						this.xPosition = originalX;
						this.yPosition = originalY;
						isMoveLegal = false;
						return;
					}
				} else {
					this.xPosition = xPosition;
					this.yPosition = yPosition;
				}
			}
		}	
		
		if ((xPosition >= 0 && yPosition >= 0) && (xPosition <= 7 && yPosition <= 7)) {
			this.originalX = this.xPosition;
			this.originalY = this.yPosition;
		}
		
		isMoveLegal = true;
	}	
	
}
