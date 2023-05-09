import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args) {
	JFrame frame = new JFrame();		
	frame.setBounds(10, 10, 512, 512);
	JPanel panel = new JPanel() {
		@Override
		public void paint(Graphics g) {
			
			boolean white = true;
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (white) {
						g.setColor(Color.white);
					} else {
						g.setColor(Color.black);
					}
					
					g.fillRect(j*64, i*64, 64, 64);
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