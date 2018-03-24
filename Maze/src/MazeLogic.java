import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.Timer;

public class MazeLogic extends JComponent
{
	private static final long serialVersionUID = 1L;
	private final int MENU=0, PLAY=1, LEVELSELECT=2, LEVELLIST=3, LEVELEDITOR=4, WIDTH, HEIGHT, FPS=60;
	private int gameState=0, maxLevel=1, animation=0, totalLevels=5, actualFPS=60, frames=0; //frames and actualFPS are time stuff
	private String path = "data/levels.dat";
	private Timer FPStimer, timer2;
	GameState[] gameStates = new GameState[5];
	MazeLogic(int width, int height)
	{
		WIDTH=width;
		HEIGHT=height;
		configureGameStates();
		setTimers();
		loadVars();
	}
	
	private void loadVars()
	{
		try
		{
			Scanner scan = new Scanner(new File(path));
			maxLevel = convert(scan.next());
			scan.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
	private int convert(String next)
	{
		String temp[] = next.split(":");
		return Integer.parseInt(temp[1]);
	}
	
	private void configureGameStates()
	{
		GameState menu = new Menu(MENU);
		gameStates[MENU] = menu;
		gameStates[PLAY] = null;
		gameStates[LEVELSELECT] = null;
		gameStates[LEVELLIST] = null;
		gameStates[LEVELEDITOR] = null;
	}
	
	private void setTimers()
	{
		class TimerListener1 implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				repaint();
				FPStimer.restart();
				frames++;
			}
		}
		FPStimer = new Timer(1000/FPS, new TimerListener1());
		FPStimer.start();
		//FPS STUFF
		class TimerListener2 implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				actualFPS = frames;
				frames=0;
				timer2.restart();
			}
		}
		timer2 = new Timer(1000, new TimerListener2());
		timer2.start();
		//
	}
	
	public void notifyMouseReleased()
	{
		gameStates[gameState].notifyMouseReleased();
	}
	public void notifyKeyReleased(KeyEvent c)
	{
		gameStates[gameState].notifyKeyReleased(c);
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
		g2.drawOval((int)temp.getX(), (int) temp.getY()-25, 10, 10);
		if (animation>-1)
		{
			animation+=5;
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, WIDTH, HEIGHT/2-animation);
			g2.fillRect(0, HEIGHT/2+animation, WIDTH, HEIGHT/2);
			g2.setColor(Color.WHITE);
			g2.drawLine(0, HEIGHT/2-animation, WIDTH, HEIGHT/2-animation);
			g2.drawLine(0, HEIGHT/2+animation, WIDTH, HEIGHT/2+animation);
			if (animation>HEIGHT/2)
			{
				animation=-1;
			}
		}
		//FPS stuff
		g2.setFont(new Font("Purisa", 0, 10));
		g2.setColor(Color.WHITE);
		g2.drawString("FPS: " + actualFPS, 10, 15);
		//
	}
	
	public void update()
	{
		gameStates[gameState].update();
		Communication commun=null;
		if (gameStates[gameState].hasCommunication())
		{
			commun = gameStates[gameState].getCommunication();
			if (gameState==PLAY && commun.getGameState()==PLAY)
			{
				if (commun.getCommand()>maxLevel && commun.getCommand()<20)
				{
					maxLevel=commun.getCommand();
				}
				if (maxLevel>totalLevels)
				{
					maxLevel=totalLevels;
				}
			}
		}
		if (gameStates[gameState].getGameState()!=gameState)
		{
			saveData();
			animation=0;
			gameState=gameStates[gameState].getGameState(); 
			//gameStates[gameState].setGameState(gameState);
			if (commun!=null && gameState == PLAY)
			{
				if (gameState==commun.getGameState())
				{
					if (commun.getCommand()!=-1)
						(gameStates[gameState]) = new Play(PLAY, commun.getCommand());
					else
						(gameStates[gameState]) = new Play(PLAY, maxLevel);

				}
			}
			else
			{
				if (gameState == MENU)
				{
					gameStates[gameState] = new Menu(MENU);
				}
				else if (gameState == PLAY)
				{
					gameStates[gameState] = new Play(PLAY, maxLevel);
				}
				else if (gameState == LEVELSELECT)
				{
					gameStates[gameState] = new LevelSelect(LEVELSELECT, maxLevel);
				}
				else if (gameState == LEVELLIST)				
				{
					gameStates[gameState] = new LevelList(LEVELLIST);
				}
				else if (gameState == LEVELEDITOR)
				{
					gameStates[gameState] = new LevelEditor(LEVELEDITOR);
				}
				else
				{
					System.out.println("Wtf bro gamestate is: " + gameState);
				}
			}
		}



	}
	private void saveData()
	{
		try 
		{
			PrintWriter pw = new PrintWriter(new File(path));
			pw.println("maxLevel:" + maxLevel);
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(e);
		}
	}
}
