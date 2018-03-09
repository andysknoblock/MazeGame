import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class MazeLogic extends JComponent
{
	private static final long serialVersionUID = 1L;
	/*
	menu: 0
	play: 1
	settings: 2
	editor: 3
	*/
	private final int MENU=0, LEVELSELECT=1, SETTINGS=2, EDITOR=3, PLAY=4, WIDTH, HEIGHT, FPS=45;
	private int gameState=0;
	private Timer FPStimer;
	GameState[] gameStates = new GameState[5];
	MazeLogic(int width, int height)
	{
		WIDTH=width;
		HEIGHT=height;
		configureGameStates();
		
		setTimers();
	}
	
	private void configureGameStates()
	{
		GameState menu = new Menu(MENU);
		gameStates[MENU] = menu;
	}
	
	private void setTimers()
	{
		class TimerListener1 implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				repaint();
				FPStimer.restart();
			}
		}
		FPStimer = new Timer(1000/FPS, new TimerListener1());
		FPStimer.start();
	}
	
	public void notifyMouseReleased()
	{
		gameStates[gameState].notifyMouseReleased();
	}
	
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		update();
		gameStates[gameState].draw(g2);
	}
	
	public void update()
	{
		gameStates[gameState].update();
		gameState=gameStates[gameState].getGameState();

	}
}
