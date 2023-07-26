// project//
package main;
import javax.swing.JFrame;
import java.awt.Color;

public class graphics{
	public JFrame graphics;
	public graphics() {
		graphics = new JFrame();
		graphics.add(new Graphics2());
		graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphics.pack();
		graphics.setVisible(true);
		graphics.setLocationRelativeTo(null);
	}
	
}
