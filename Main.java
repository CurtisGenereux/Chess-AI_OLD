import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
	public static void main(String[] args) {
		
		Color baishe = new Color (241, 216, 179); 
		Color brown = new Color (169, 129, 97);
		
		// grab user resolution
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		int height = screenSize.height;
		int width = screenSize.width;
		
		int tileSize = (int) Math.round(height*0.1);
		
		JFrame frame = new JFrame();
		frame.setSize(tileSize*8+16, tileSize*8+32);
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
						isLightTile=!isLightTile; // if (white == false) => true; (else) => true;
					}
					isLightTile=!isLightTile;  // alternate for checker pattern;
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}
}
