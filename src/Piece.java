package chess;

import java.util.LinkedList;

public class Piece {
	
	int xPosition;
	int yPosition;
	LinkedList<Piece> pieces;
	String name;
	
	public Piece(int xPosition, int yPosition, String name, LinkedList<Piece> pieces) {
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.pieces = pieces;
		this.name = name;
		pieces.add(this);
		
	}
	
	public void move(int xPosition, int yPosition) {
		
		for (Piece piece: pieces) {
			if ((piece.xPosition == xPosition) && (piece.yPosition == yPosition)) {
				piece.kill();
				
			}
			
		}
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
	}
	
	public void kill() {
		pieces.remove(this);
		
	}
}
