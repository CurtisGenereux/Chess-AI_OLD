package chess;

import java.util.Iterator;
import java.util.LinkedList;

public class Piece {
	
	int xPosition;
	int yPosition;
	LinkedList<Piece> pieces;
	String name;
	boolean isLightPiece;
	
	public Piece(int xPosition, int yPosition, String name, boolean isLightPiece, LinkedList<Piece> pieces) {
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.pieces = pieces;
		this.isLightPiece = isLightPiece;
		this.name = name;
		pieces.add(this);
		
	}
	
	public void move(int xPosition, int yPosition, boolean isLightPiece) {
		
		int oldX = xPosition;
		int oldY = yPosition;
		
		this.isLightPiece = isLightPiece;
		
		Iterator<Piece> pieceList = pieces.iterator();
		Piece piece;
		
		while (pieceList.hasNext() == true) {
			piece = pieceList.next();
			if (piece != null) {
				this.xPosition = xPosition;
				this.yPosition = yPosition;
			}
			if ((piece.xPosition == xPosition) && (piece.yPosition == yPosition) && (piece != null) && (piece.isLightPiece != isLightPiece)) {
				pieceList.remove(); 
				this.xPosition = xPosition;
				this.yPosition = yPosition;
			} else {
				// snap piece back to start tile
			}
			
		}
		
	}
	
}
