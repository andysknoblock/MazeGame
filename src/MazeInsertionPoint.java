

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MazeInsertionPoint {

	public static void main(String[] args) {
		final int WIDTH=640, HEIGHT=480;
		JFrame frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MazeLogic game = new MazeLogic(WIDTH, HEIGHT);
		class MouseInfo implements MouseListener
		{
			public void mouseClicked(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		}
		game.addMouseListener(new MouseInfo());
		frame.add(game);
		frame.setVisible(true);
	}

}
