package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {
		
		LinkedList<Piece> ps = new LinkedList<>();
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		int tileSize = (int) Math.round(height*0.1);

		ImageLoader imageLoader = new ImageLoader();
		Image images[]=new Image[14];
		
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
		
		Piece blackRook = new Piece(0, 0, "black-rook", ps);
		Piece blackKnight1 = new Piece(1, 0, "black-knight-right", ps);
		Piece blackBishop1 = new Piece(2, 0, "black-bishop", ps);
		Piece blackQueen = new Piece(3, 0, "black-queen", ps);
		Piece blackKing = new Piece(4, 0, "black-king", ps);
		Piece blackBishop2 = new Piece(5, 0, "black-bishop", ps);
		Piece blackKnight2 = new Piece(6, 0, "black-knight-left", ps);
		Piece blackRook2 = new Piece(7, 0, "black-rook", ps);
		
		Piece blackPawn1 = new Piece(0, 1, "black-pawn", ps);
		Piece blackPawn2 = new Piece(1, 1, "black-pawn", ps);
		Piece blackPawn3 = new Piece(2, 1, "black-pawn", ps);
		Piece blackPawn4 = new Piece(3, 1, "black-pawn", ps);
		Piece blackPawn5 = new Piece(4, 1, "black-pawn", ps);
		Piece blackPawn6 = new Piece(5, 1, "black-pawn", ps);
		Piece blackPawn7 = new Piece(6, 1, "black-pawn", ps);
		Piece blackPawn8 = new Piece(7, 1, "black-pawn", ps);
		
		Piece whitePawn1 = new Piece(0, 6, "white-pawn", ps);
		Piece whitePawn2 = new Piece(1, 6, "white-pawn", ps);
		Piece whitePawn3 = new Piece(2, 6, "white-pawn", ps);
		Piece whitePawn4 = new Piece(3, 6, "white-pawn", ps);
		Piece whitePawn5 = new Piece(4, 6, "white-pawn", ps);
		Piece whitePawn6 = new Piece(5, 6, "white-pawn", ps);
		Piece whitePawn7 = new Piece(6, 6, "white-pawn", ps);
		Piece whitePawn8 = new Piece(7, 6, "white-pawn", ps);
		
		Piece whiteRook = new Piece(0, 7, "white-rook", ps);
		Piece whiteKnight1 = new Piece(1, 7, "white-knight-right", ps);
		Piece whiteBishop1 = new Piece(2, 7, "white-bishop", ps);
		Piece whiteQueen = new Piece(3, 7, "white-queen", ps);
		Piece whiteKing = new Piece(4, 7, "white-king", ps);
		Piece whiteBishop2 = new Piece(5, 7, "white-bishop", ps);
		Piece whiteKnight2 = new Piece(6, 7, "white-knight-left", ps);
		Piece whiteRook2 = new Piece(7, 7, "white-rook", ps);
		
		Color baishe = new Color (241, 216, 179); // -50, -0, -25;
		Color brown = new Color (169, 129, 97); // - 50, -0, -25;
		
		JFrame frame = new JFrame();
		frame.setSize(tileSize*8+16, tileSize*8+38);
        frame.setLocationRelativeTo(null);
        
		JPanel panel = new JPanel() {
		
			public void paint(Graphics g) {
				
				Color backgroundColor = new Color(255, 251, 240);
		        g.setColor(backgroundColor);
		        g.fillRect(0, 0, width, height);
				
				boolean isLightTile = true;
				
				int boardSize = tileSize * 8;
				int startX = (getWidth() - boardSize) / 2;
				int startY = (getHeight() - boardSize) / 2;
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (isLightTile) {
							g.setColor(baishe);
						} else {
							g.setColor(brown);
						}
						
						g.fillRect(startX + j * tileSize, startY + i * tileSize, tileSize, tileSize);
						isLightTile=!isLightTile; // if (light == false) => true; (else) => false;
					}
					isLightTile=!isLightTile;  // alternate for checker pattern;
				}
				
				for (Piece piece: ps) {
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
					
					g.drawImage(images[imageIndex], piece.xPosition*tileSize, piece.yPosition*tileSize, this);
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
	}
}
