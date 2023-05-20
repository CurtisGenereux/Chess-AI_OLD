package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import pieces.Pawn;
import pieces.Piece;

public class Main {
	
	public static Image[] images;
	public static LinkedList<Piece> pieces = new LinkedList<>();
	public static Piece[][] board = new Piece[8][8];
	public static Piece selectedPiece = null;
	public static Piece targetPiece = null;
	public boolean isMoveLegal;

	public static int mouseXPixelPosition = 0;
	public static int mouseYPixelPosition = 0;

	public static int boardSize;
	public static int xBorderWidth;
	public static int yBorderHeight;
    
	public static Image assignImages(Piece piece) {

		switch (piece.name.toLowerCase()) {
			case "white-pawn":
				return images[0];
			case "white-knight-left":
				return images[1];
			case "white-knight-right":
				return images[2];
			case "white-rook":
				return images[3];
			case "white-bishop":
				return images[4];
			case "white-queen":
				return images[5];
			case "white-king":
				return images[6];
			case "black-pawn":
				return images[7];
			case "black-knight-left":
				return images[8];
			case "black-knight-right":
				return images[9];
			case "black-rook":
				return images[10];
			case "black-bishop":
				return images[11];
			case "black-queen":
				return images[12];
			case "black-king":
				return images[13];

		} return null;
	}
    
	public static Piece getPiece(int xPosition, int yPosition) {
		if ((xPosition < 0) || (yPosition < 0) || (xPosition > 7) || (yPosition > 7)) {
			return null;
		} else {
			return board[xPosition][yPosition];
		}
	}
    
	public static void main(String[] args) {
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		// board should be 80% of the screen
		int tileSize = (int) Math.round(height*0.1);
		
		ImageLoader imageLoader = new ImageLoader();
		images = new Image[14];
		
		// load piece images
		String[] imageNames = {"pawn", "knight-left", "knight-right", "rook", "bishop", "queen", "king"};
		
		for (int i = 0; i < 7; i++) {
			images[i] = imageLoader.loadImage("black-" + imageNames[i], tileSize);
		} int imageIndex = 0;
		for (int i = 7; i < 14; i++) {
			images[i] = imageLoader.loadImage("white-" + imageNames[imageIndex], tileSize);
			imageIndex++;
		}
	
		// asign pieces
		for (int i = 0; i < 8; i++) {
		    Pawn whitePawn = new Pawn(i, 1, "white-pawn", true, pieces);
		    Pawn blackPawn = new Pawn(i, 6, "black-pawn", false, pieces);
		    pieces.add(whitePawn);
		    pieces.add(blackPawn);
		    board[i][1] = whitePawn;
		    board[i][6] = blackPawn;
		}

		String[] pieceNames = {"rook", "knight-right", "bishop", "queen", "king", "bishop", "knight-left", "rook"};

		for (int i = 0; i < 8; i++) {
			Piece whitePiece = new Piece(i, 0, "white-" + pieceNames[i], true, pieces);
			Piece blackPiece = new Piece(i, 7, "black-" + pieceNames[i], false, pieces);
			pieces.add(whitePiece);
			pieces.add(blackPiece);
			board[i][0] = whitePiece;
			board[i][7] = blackPiece;
		}

		Color baishe = new Color (241, 216, 179); //legal: -50, -0, -25; //current: -0, -125,  -75;
		Color brown = new Color (169, 129, 97); //legal: - 50, -0, -25;
		
		JFrame frame = new JFrame();
		frame.setSize(tileSize*8+16, tileSize*8+38); // y axis is larger because of tab
		frame.setLocationRelativeTo(null);
        
		JPanel panel = new JPanel() {
			
			public void paint(Graphics g) {
				
				Color backgroundColor = new Color(255, 251, 240);
				g.setColor(backgroundColor);
				g.fillRect(0, 0, width, height);
				
				boardSize = tileSize * 8;
				xBorderWidth = (getWidth() - boardSize) / 2;
				yBorderHeight = (getHeight() - boardSize) / 2;
				
				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						if (((x + y) % 2) != 0) {
							g.setColor(brown);
						} else {
							g.setColor(baishe);
						}		
						g.fillRect(xBorderWidth + y * tileSize, yBorderHeight + x * tileSize, tileSize, tileSize);
					}
				}
				
				for (Piece piece: pieces) {
					
					if (piece != selectedPiece) {
						// draw selectedPiece last so it can be on the top layer

						int xPixelPosition = (piece.xPosition * tileSize) + xBorderWidth;
						int yPixelPosition = (piece.yPosition * tileSize) + yBorderHeight;
						// find tile position then add the size of the background to center pieces

						g.drawImage(assignImages(piece), xPixelPosition, yPixelPosition, this);

					}
				}
				
				if (selectedPiece != null) {
					g.drawImage(assignImages(selectedPiece), mouseXPixelPosition-(tileSize/2), mouseYPixelPosition-(tileSize/2), null);
				}
			}
		};
		
		frame.add(panel);
		frame.setVisible(true);
		panel.addMouseListener(new MouseListener() {
			
			int oldXTile = 0;
			int oldYTile = 0;

			@Override
			public void mousePressed(MouseEvent mousePressed) {
				
				mouseXPixelPosition = mousePressed.getX();
				mouseYPixelPosition = mousePressed.getY();
				
				int mouseXTile = Math.round((mouseXPixelPosition-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixelPosition-yBorderHeight)/tileSize);
				
				oldXTile = mouseXTile;
				oldYTile = mouseYTile;
				
				selectedPiece = getPiece(mouseXTile, mouseYTile);

			}
			
			@Override
			public void mouseReleased(MouseEvent mouseReleased) {
				
				mouseXPixelPosition = mouseReleased.getX();
				mouseYPixelPosition = mouseReleased.getY();
				
				int mouseXTile = Math.round((mouseXPixelPosition-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixelPosition-yBorderHeight)/tileSize);
				
				targetPiece = getPiece(mouseXTile, mouseYTile);
				
				if (selectedPiece == null) {
					return;
				}
				
				selectedPiece.move(mouseXTile, mouseYTile, selectedPiece.isLightPiece);

				if (mouseXTile >= 0 && mouseYTile >= 0 && mouseXTile <= 7 && mouseYTile <= 7 && selectedPiece.isMoveLegal) {
					if (selectedPiece.isMoveLegal) {
						selectedPiece.move(mouseXTile, mouseYTile, selectedPiece.isLightPiece);
						if (selectedPiece.isMoveLegal) {
							board[mouseXTile][mouseYTile] = selectedPiece;
							if (mouseXTile != oldXTile || mouseYTile != oldYTile) {
								board[oldXTile][oldYTile] = null;
							}
						}
					}

				} else {
					selectedPiece.move(oldXTile, oldYTile, selectedPiece.isLightPiece);
				}
				
				selectedPiece = null;
				panel.repaint();
				
			}
			
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited (MouseEvent e) {}
			
		}); panel.addMouseMotionListener(new MouseMotionListener() {
			
			public void mouseDragged(MouseEvent mouseDragged) {

				if (selectedPiece == null) {
					return;
				}

				mouseXPixelPosition = mouseDragged.getX();
				mouseYPixelPosition = mouseDragged.getY();

				int mouseXTile = Math.round((mouseXPixelPosition-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixelPosition-yBorderHeight)/tileSize);

				selectedPiece.xPosition = mouseXTile;
				selectedPiece.yPosition = mouseYTile;

				panel.repaint();
			}

			public void mouseMoved(MouseEvent e) {}
		});
	}
}
