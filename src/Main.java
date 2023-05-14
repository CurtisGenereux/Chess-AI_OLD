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
        } else if (piece.name.equalsIgnoreCase("black-rook")) {
            return images[10];
        } else if (piece.name.equalsIgnoreCase("black-bishop")) {
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
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		// board should be 80% of the screen
		int tileSize = (int) Math.round(height*0.1);
		
		// define piece list and board;
		LinkedList<Piece> pieces = new LinkedList<>();
		Piece[][] board = new Piece[8][8];
		
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
		    Pawn whitePawn = new Pawn(i, 1, "white-pawn", pieces);
		    Pawn blackPawn = new Pawn(i, 6, "black-pawn", pieces);
		    pieces.add(whitePawn);
		    pieces.add(blackPawn);
		    board[i][6] = whitePawn;
		    board[i][1] = blackPawn;
		}

		String[] pieceNames = {"rook", "knight-right", "bishop", "queen", "king", "bishop", "knight-left", "rook"};

		for (int i = 0; i < 8; i++) {
			Piece whitePiece = new Piece(i, 0, "white-" + pieceNames[i], pieces);
			Piece blackPiece = new Piece(i, 7, "black-" + pieceNames[i], pieces);
		    pieces.add(whitePiece);
		    pieces.add(blackPiece);
		    board[i][7] = whitePiece;
		    board[i][0] = blackPiece;
		}
		
		// move test
		for (Piece piece : pieces) {
		    if (piece instanceof Pawn) {
		        piece.move(4, 7);
		        break;
		    }
		}
		
		Color baishe = new Color (241, 216, 179); //legal: -50, -0, -25; //current: -0, -125,  -75;
		Color brown = new Color (169, 129, 97); //legal: - 50, -0, -25;
		
		JFrame frame = new JFrame();
		frame.setSize(tileSize*8+16, tileSize*8+38); // y axis is larger because of tab
        frame.setLocationRelativeTo(null);
        
		JPanel panel = new JPanel() {
			
			int width = 0;
			
			
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
		panel.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				int mouseXPos = e.getX();
				int mouseYPos = e.getY();
				
				int pieceXIndex = Math.round(mouseXPos/tileSize);
				int pieceYIndex = Math.round(mouseYPos/tileSize);

				System.out.println("picked up piece at: [" + pieceXIndex + ", " + pieceYIndex + "]");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				int mouseXPos = e.getX();
				int mouseYPos = e.getY();
				
				int pieceXIndex = Math.round(mouseXPos/tileSize);
				int pieceYIndex = Math.round(mouseYPos/tileSize);
				
				System.out.println("dropped up piece at: [" + pieceXIndex + ", " + pieceYIndex + "]");
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
		});
		panel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}
}
