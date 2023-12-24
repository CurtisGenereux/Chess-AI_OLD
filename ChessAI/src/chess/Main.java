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

import pieces.LegalMove;
import pieces.Pawn;
import pieces.Rook;
import pieces.Piece;

public class Main {
	
	public static Image[] images;
	public static LinkedList<Piece> pieces = new LinkedList<>();
	public LinkedList<LegalMove> legalMoveList;
	public static Piece[][] board = new Piece[8][8];
	public static Piece selectedPiece = null;
	public static Piece targetPiece = null;
	public static boolean isMousePressed = false;
	public boolean isMoveLegal;

	public static int mouseXPixel = 0;
	public static int mouseYPixel = 0;

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
			images[i] = imageLoader.loadImage("white-" + imageNames[i], tileSize);
		} int imageIndex = 0;
		for (int i = 7; i < 14; i++) {
			images[i] = imageLoader.loadImage("black-" + imageNames[imageIndex], tileSize);
			imageIndex++;
		}
	
		// asign pieces
		for (int i = 0; i < 8; i++) {
		    Pawn whitePawn = new Pawn(i, 6, "white-pawn", true, pieces);
		    Pawn blackPawn = new Pawn(i, 1, "black-pawn", false, pieces);
		    pieces.add(whitePawn);
		    pieces.add(blackPawn);
		    board[i][6] = whitePawn;
		    board[i][1] = blackPawn;
		}

		String[] pieceNames = {"rook", "knight-right", "bishop", "queen", "king", "bishop", "knight-left", "rook"};
		
		/*
		Piece whiteRook = new Rook(3, 3, "white-rook", true, pieces);
		pieces.add(whiteRook);
		board[3][3] = whiteRook;
		*/
		
		for (int i = 0; i < 8; i++) {
			Piece whitePiece = new Piece(i, 7, "white-" + pieceNames[i], true, pieces);
			Piece blackPiece = new Piece(i, 0, "black-" + pieceNames[i], false, pieces);
			pieces.add(whitePiece);
			pieces.add(blackPiece);
			board[i][7] = whitePiece;
			board[i][0] = blackPiece;
		}

		Color baishe = new Color (241, 216, 179);
		Color brown = new Color (169, 129, 97);
		
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
				
				Color gold = new Color (250, 204, 55);
				Color greenBaishe = new Color (241 - 50, 216, 179 - 25);
				Color greenBrown = new Color (169 - 50, 129, 97 - 25);
				
				if (isMousePressed) {
					
					if (selectedPiece != null) {
						
						int x = selectedPiece.originalX;
						int y = selectedPiece.originalY;
						
						if (isMousePressed) {
							g.setColor(gold);
							g.fillRect(xBorderWidth + x * tileSize, yBorderHeight + y * tileSize, tileSize, tileSize);
						}
						
					}
					Piece legalPiece = new Piece();
					
					for (LegalMove possibleMove: legalPiece.legalMoveList) {
						
						int x = possibleMove.getXPosition();
						int y = possibleMove.getYPosition();
						
						if (((x + y) % 2) != 0) {
								g.setColor(greenBrown);
								g.fillRect(xBorderWidth + x * tileSize, yBorderHeight + y * tileSize, tileSize, tileSize);
						} else {
							g.setColor(greenBaishe);
							g.fillRect(xBorderWidth + x * tileSize, yBorderHeight + y * tileSize, tileSize, tileSize);
				
						}

					}
				}

				for (Piece piece: pieces) {

					if (piece != selectedPiece) {
						
						if ((piece.xPosition * tileSize) == 0 && (piece.yPosition * tileSize) == 0) {
							System.out.println("piece is the piece bruh wth");
						}
						// draw selectedPiece last so it can be on the top layer
							
						int xPixelPosition = (piece.xPosition * tileSize) + xBorderWidth;
				        int yPixelPosition = (piece.yPosition * tileSize) + yBorderHeight;
				        // find tile position then add the size of the background to center pieces
				        
				        g.drawImage(assignImages(piece), xPixelPosition, yPixelPosition, this);

					}
				}
				
				if (selectedPiece != null) {
					g.drawImage(assignImages(selectedPiece), mouseXPixel-(tileSize/2), mouseYPixel-(tileSize/2), null);
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

				System.out.println("mouse pressed!");
				
				isMousePressed = true;
				
				mouseXPixel = mousePressed.getX();
				mouseYPixel = mousePressed.getY();
				
				int mouseXTile = Math.round((mouseXPixel-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixel-yBorderHeight)/tileSize);
				
				oldXTile = mouseXTile;
				oldYTile = mouseYTile;
				
				selectedPiece = getPiece(mouseXTile, mouseYTile);
			
				if (selectedPiece != null) {
					selectedPiece.fillMoveList();
					selectedPiece.fillMoveList();
					panel.repaint();
				}
				
				

			}
			
			@Override
			public void mouseReleased(MouseEvent mouseReleased) {
				
				System.out.println("mouse released!");
				
				isMousePressed = false;
				
				mouseXPixel = mouseReleased.getX();
				mouseYPixel = mouseReleased.getY();
				
				int mouseXTile = Math.round((mouseXPixel-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixel-yBorderHeight)/tileSize);
				
				targetPiece = getPiece(mouseXTile, mouseYTile);
				
				if (selectedPiece == null) {
					return;
				}
				
				selectedPiece.move(mouseXTile, mouseYTile);
				if (selectedPiece.isMoveLegal) {
					board[mouseXTile][mouseYTile] = selectedPiece;
					if (mouseXTile != oldXTile || mouseYTile != oldYTile) {
						board[oldXTile][oldYTile] = null;
						pieces.remove(targetPiece);
					}

				} else {
					selectedPiece.move(oldXTile, oldYTile);
				}
				
				selectedPiece.clearMoveList();
				
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

				mouseXPixel = mouseDragged.getX();
				mouseYPixel = mouseDragged.getY();

				int mouseXTile = Math.round((mouseXPixel-xBorderWidth)/tileSize);
				int mouseYTile = Math.round((mouseYPixel-yBorderHeight)/tileSize);

				selectedPiece.xPosition = mouseXTile;
				selectedPiece.yPosition = mouseYTile;

				panel.repaint();
			}

			public void mouseMoved(MouseEvent e) {}
		});
	}
}


