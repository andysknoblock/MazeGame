import java.awt.Color;
import java.awt.Graphics2D;

public class Menu implements GameState
{
	private Button[] buttons = new Button[4];
	private int gameState;
	Menu(int gameState)
	{
		this.gameState=gameState;
		buttons[0] = new Button("PLAY", WIDTH/2, 90, 50);
		buttons[1] = new Button("LEVEL SELECTOR", WIDTH/2, 150, 30);
		buttons[2] = new Button("SETTINGS", WIDTH/2, 190, 30);
		buttons[3] = new Button("LEVEL EDITOR", WIDTH/2, 230, 30);
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(new Color(50, 200, 200, 150));
		for (Button button: buttons)
		{
			button.draw(g2);
		}
		
	}
	public void update() 
	{
		for (int i=0; i < buttons.length; i++)
		{
			buttons[i].update();
		}
	}
	public void notifyMouseReleased() 
	{
		for (int i=0; i < buttons.length; i++)
		{
			if (buttons[i].isReleased())
			{
				gameState = i+1;
			}
		}
	}
	
	public int getGameState()
	{
		
		return gameState;
	}
}
