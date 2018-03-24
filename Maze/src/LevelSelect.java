import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class LevelSelect implements GameState
{
	private int gameState, level=0, maxLevel;
	
	private boolean hasCommunication=false;
	private NumberButton[] buttons = new NumberButton[16];
	private PulsingColor col = new PulsingColor(100,254,100,200);
	private Button menu = new Button("Return To Menu", WIDTH/2-10, 20, 25);
	private Communication command = null;
	LevelSelect (int gameState, int maxLevel)
	{
		this.maxLevel=maxLevel;
		this.gameState=gameState;
		int x=70, y = 50;
		for (int i=0; i<buttons.length; i++)
		{
			buttons[i] = new NumberButton(i+1, x, y, 70, 60);
			x+=120;
			if (x+50>WIDTH)
			{
				x=70;
				y+=80;
			}
		}
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(col.getColor());
		menu.draw(g2);
		for (int i=0; i<buttons.length; i++)
		{
			if (i<maxLevel)
			{
				buttons[i].draw(g2, false);
			}
			else
			{
				buttons[i].draw(g2, true);
			}
		}
	}

	public void update() 
	{
		menu.update();
		col.update();
		for (int i=0; i<maxLevel; i++)
		{
			buttons[i].update();
		}
	}

	public void notifyMouseReleased()
	{
		for (int i=0; i<buttons.length; i++)
		{
			if (buttons[i].isReleased())
			{
				if (buttons[i].getValue()<=maxLevel)
				{
					loadLevel(buttons[i].getValue());
				}
			}
		}
		if (menu.isReleased())
		{
			gameState=MENU;
		}
	}
	public void loadLevel(int level)
	{
		this.level=level;
		hasCommunication=true;
		command = new Communication(PLAY, level);
		gameState=PLAY;
	}
	public int getLevel()
	{
		return level;
	}

	public int getGameState() 
	{
		return gameState;
	}
	@Override
	public void notifyKeyReleased(KeyEvent e) 
	{
		
	}
	@Override
	public void setGameState(int gameState) 
	{
		this.gameState=gameState;
		
	}
	@Override
	public boolean hasCommunication() 
	{
		return hasCommunication;
	}
	@Override
	public Communication getCommunication() 
	{
		return command;
	}
	@Override
	public void load(int message) {
		// TODO Auto-generated method stub
		
	}

}