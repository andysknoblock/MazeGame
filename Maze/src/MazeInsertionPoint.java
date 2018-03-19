

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class MazeInsertionPoint {

	public static void main(String[] args) {
		final int WIDTH=640, HEIGHT=480;
		JFrame frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setCursor( frame.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );
		final MazeLogic game = new MazeLogic(WIDTH, HEIGHT);
		class MouseInfo implements MouseListener
		{
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				game.notifyMouseReleased();
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		}
		class KeyInfo implements KeyListener
		{

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				game.notifyKeyReleased(e);
			}
			
		}
		frame.addMouseListener(new MouseInfo());
		frame.addKeyListener(new KeyInfo());
		frame.add(game);
		frame.setVisible(true);
	}

}
