package pieces;

public class LegalMove {
	
	int xPosition;
	int yPosition;
	
	public LegalMove(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
}
