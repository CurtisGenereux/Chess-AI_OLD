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
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		int tileSize;
		
		if (width < 1920 || height < 1080) {
			tileSize = 64;
		} else {
			tileSize = 128; // >1080p => 128px;
		}
		
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 512, 512);
        frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel() {
			
			@Override
			public void paint(Graphics g) {
				
				boolean white = true; // or baishe
				
                int boardSize = tileSize * 8;
                int startX = (getWidth() - boardSize) / 2;
                int startY = (getHeight() - boardSize) / 2;
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (white) {
							g.setColor(baishe);
						} else {
							g.setColor(brown);
						}
						
						g.fillRect(startX + j * tileSize, startY + i * tileSize, tileSize, tileSize);
						white=!white; // if (white == false) => true; (else) => true;
					}
					white=!white;  // alternate for checker pattern;
				}
			}
		};
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}
}
