import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
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
	private final int MENU=0, PLAY=1, LEVELSELECT=2, SETTINGS=3, LEVELEDITOR=4, WIDTH, HEIGHT, FPS=60;
	private int gameState=0, level=1;
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
		GameState play = new Play(PLAY, level);
		gameStates[PLAY] = play;
		GameState levelSelect = new LevelSelect(LEVELSELECT);
		gameStates[LEVELSELECT] = levelSelect;
		GameState settings = new Settings(SETTINGS);
		gameStates[SETTINGS] = settings;
		GameState levelEditor = new LevelEditor(LEVELEDITOR);
		gameStates[LEVELEDITOR] = levelEditor;
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
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		g2.setColor(Color.WHITE);
		update();
		gameStates[gameState].draw(g2);
		//cursor
		g2.setColor(Color.WHITE);
		Point temp = MouseInfo.getPointerInfo().getLocation();
		g2.drawOval((int)temp.getX(), (int) temp.getY(), 10, 10);
	}
	
	public void update()
	{
		gameStates[gameState].update();
		gameState=gameStates[gameState].getGameState();

	}
}
