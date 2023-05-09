import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	public static void main(String[] args) {
		
		Color baishe = new Color (241, 216, 179); 
		Color brown = new Color (169, 129, 97);
		
		JFrame frame = new JFrame();
		frame.setBounds(10, 10, 512, 512);
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				
				boolean white = true;
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (white) {
							g.setColor(baishe);
						} else {
							g.setColor(brown);
						}
						
						g.fillRect(j*108, i*108, 108, 108);
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
