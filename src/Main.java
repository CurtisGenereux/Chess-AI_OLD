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
	
    public static Image[] images;
    
    public static Image assignPieces(Piece piece) {
        if (piece.name.equalsIgnoreCase("white-pawn")) {
            return images[0];
        } else if (piece.name.equalsIgnoreCase("white-knight-left")) {
            return images[1];
        } else if (piece.name.equalsIgnoreCase("white-knight-right")) {
            return images[2];
        } else if (piece.name.equalsIgnoreCase("white-rook")) {
            return images[3];
        } else if (piece.name.equalsIgnoreCase("white-bishop")) {
            return images[4];
        } else if (piece.name.equalsIgnoreCase("white-queen")) {
            return images[5];
        } else if (piece.name.equalsIgnoreCase("white-king")) {
            return images[6];
        } else if (piece.name.equalsIgnoreCase("black-pawn")) {
            return images[7];
        } else if (piece.name.equalsIgnoreCase("black-knight-left")) {
            return images[8];
        } else if (piece.name.equalsIgnoreCase("black-knight-right")) {
            return images[9];
        } else if (piece.name.equalsIgnoreCase("black-bishop")) {
            return images[10];
        } else if (piece.name.equalsIgnoreCase("black-rook")) {
            return images[11];
        } else if (piece.name.equalsIgnoreCase("black-queen")) {
            return images[12];
        }  else if (piece.name.equalsIgnoreCase("black-king")) {
            return images[13];
        } else {
    		return null;
        }
    }
	
	public static void main(String[] args) {

		LinkedList<Piece> pieces = new LinkedList<>();
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		// board should be 80% of the screen
		int tileSize = (int) Math.round(height*0.1);
		
		Piece[][] board = new Piece[8][8];
		
		ImageLoader imageLoader = new ImageLoader();
		images = new Image[14];
	
		// asign pieces
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
		
		// load piece images
		String[] uniquePieceNames = {"pawn", "rook", "knight-left", "knight-right", "bishop", "queen", "king"};
		
		for (int i = 0; i < 7; i++) {
			images[i] = imageLoader.loadImage("black-" + uniquePieceNames[i], tileSize);
		}
		int imageIndex = 0;
		for (int i = 7; i < 14; i++) {
			images[i] = imageLoader.loadImage("white-" + uniquePieceNames[imageIndex], tileSize);
			imageIndex++;
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
					
					int Xpiece = startX + piece.xPosition * tileSize;
			        int Ypiece = startY + piece.yPosition * tileSize;
					
			        g.drawImage(assignPieces(piece), Xpiece, Ypiece, this);
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
	}
}
