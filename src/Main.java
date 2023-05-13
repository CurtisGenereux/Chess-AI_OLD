package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {

		LinkedList<Piece> pieces = new LinkedList<>();
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		// board should be 80% of the screen
		int tileSize = (int) Math.round(height*0.1);

		ImageLoader imageLoader = new ImageLoader();
		Image images[]=new Image[14];
		
		// load all images into the array
		images[0] = imageLoader.loadImage("white-pawn", tileSize);
		images[1] = imageLoader.loadImage("white-knight-left", tileSize);
		images[2] = imageLoader.loadImage("white-knight-right", tileSize);
		images[3] = imageLoader.loadImage("white-rook", tileSize);
		images[4] = imageLoader.loadImage("white-bishop", tileSize);
		images[5] = imageLoader.loadImage("white-queen", tileSize);
		images[6] = imageLoader.loadImage("white-king", tileSize);
		images[7] = imageLoader.loadImage("black-pawn", tileSize);
		images[8] = imageLoader.loadImage("black-knight-left", tileSize);
		images[9] = imageLoader.loadImage("black-knight-right", tileSize);
		images[10] = imageLoader.loadImage("black-rook", tileSize);
		images[11] = imageLoader.loadImage("black-bishop", tileSize);
		images[12] = imageLoader.loadImage("black-queen", tileSize);
		images[13] = imageLoader.loadImage("black-king", tileSize);

		Piece[][] board = new Piece[8][8];
	
		for (int i = 0; i < 8; i++) {
		    Piece blackPawn = new Piece(i, 1, "black-pawn", pieces);
		    Piece whitePawn = new Piece(i, 6, "white-pawn", pieces);
		    board[i][1] = blackPawn;
		    board[i][6] = whitePawn;
		}
		
		String[] pieceNames = {"rook", "knight-right", "bishop", "queen", "king", "bishop", "knight-left", "rook"};

		for (int i = 0; i < 8; i++) {
		    Piece blackPiece = new Piece(i, 0, "black-" + pieceNames[i], pieces);
		    Piece whitePiece = new Piece(i, 7, "white-" + pieceNames[i], pieces);
		    board[i][0] = blackPiece;
		    board[i][7] = whitePiece;
		}
		
		Color baishe = new Color (241, 216, 179); // -50, -0, -25;
		Color brown = new Color (169, 129, 97); // - 50, -0, -25;
		
		JFrame frame = new JFrame();
		frame.setSize(tileSize*8+16, tileSize*8+38); // y axis is larger because of tab
        frame.setLocationRelativeTo(null);
        
		JPanel panel = new JPanel() {
		
			public void paint(Graphics g) {
				
				Color backgroundColor = new Color(255, 251, 240);
		        g.setColor(backgroundColor);
		        g.fillRect(0, 0, width, height);
				
				int boardSize = tileSize * 8;
				int startX = (getWidth() - boardSize) / 2;
				int startY = (getHeight() - boardSize) / 2;
				
				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						if (((x + y) % 2) != 0) {
							g.setColor(brown);
						} else {
							g.setColor(baishe);
						}		
						g.fillRect(startX + y * tileSize, startY + x * tileSize, tileSize, tileSize);
					}
				}
				
				for (Piece piece: pieces) {
					int imageIndex = 0;
					
					if (piece.name.equalsIgnoreCase("white-pawn")) {
						imageIndex = 0;
					} else if (piece.name.equalsIgnoreCase("white-knight-left")) {
						imageIndex = 1;
					} else if (piece.name.equalsIgnoreCase("white-knight-right")) {
						imageIndex = 2;
					} else if (piece.name.equalsIgnoreCase("white-rook")) {
						imageIndex = 3;
					}  else if (piece.name.equalsIgnoreCase("white-bishop")) {
						imageIndex = 4;
					}  else if (piece.name.equalsIgnoreCase("white-queen")) {
						imageIndex = 5;
					}  else if (piece.name.equalsIgnoreCase("white-king")) {
						imageIndex = 6;		
					}  else if (piece.name.equalsIgnoreCase("black-pawn")) {
						imageIndex = 7;
					}  else if (piece.name.equalsIgnoreCase("black-knight-left")) {
						imageIndex = 8;
					}  else if (piece.name.equalsIgnoreCase("black-knight-right")) {
						imageIndex = 9;
					}  else if (piece.name.equalsIgnoreCase("black-rook")) {
						imageIndex = 10;
					} 	else if (piece.name.equalsIgnoreCase("black-bishop")) {
						imageIndex = 11;
					} else if (piece.name.equalsIgnoreCase("black-queen")) {
						imageIndex = 12;
					} else if (piece.name.equalsIgnoreCase("black-king")) {
						imageIndex = 13;
					}
					
					int Xpiece = startX + piece.xPosition * tileSize;
			        int Ypiece = startY + piece.yPosition * tileSize;
					
			        g.drawImage(images[imageIndex], Xpiece, Ypiece, this);
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
	}
}
