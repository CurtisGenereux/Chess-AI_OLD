package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessMouseListener implements MouseListener, MouseMotionListener {
    private Main main;

    public ChessMouseListener(Main main) {
        this.main = main;
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		
		/*
		int boardSize = main.tileSize * 8;
		int startX = (main.panel.getWidth() - boardSize) / 2;
		int startY = (main.panel.getHeight() - boardSize) / 2;
		
		main.mouseXPixelPosition = e.getX();
		main.mouseYPixelPosition = e.getY();
		
		int mouseXTile = Math.round((main.mouseXPixelPosition-startX)/tileSize);
		int mouseYTile = Math.round((main.mouseYPixelPosition-startY)/tileSize);
		
		if (main.selectedPiece != null) {
			main.selectedPiece.xPosition = mouseXTile;
			main.selectedPiece.yPosition = mouseYTile;
			main.panel.repaint();
		}	
	*/
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
