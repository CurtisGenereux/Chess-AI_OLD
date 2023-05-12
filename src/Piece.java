package chess;

import java.util.LinkedList;

public class Piece {
	
	int xPosition;
	int yPosition;
	boolean isLightTile;
	LinkedList<Piece> ps;
	String name;
	
	public Piece(int xPosition, int yPosition, String n, LinkedList<Piece> ps) {
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.isLightTile = isLightTile;
		this.ps = ps;
		name = n;
		ps.add(this);
		
	}
	
	public void move(int xPosition, int yPosition) {
		
		for (Piece piece: ps) {
			if ((piece.xPosition == xPosition) && (piece.yPosition == yPosition)) {
				piece.kill();
				
			}
			
		}
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
	}
	
	public void kill() {
		ps.remove(this);
		
	}
}
