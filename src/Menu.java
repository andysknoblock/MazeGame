import java.awt.Graphics2D;

public class Menu implements GameState
{
	private int gameState;
	Menu(int gameState)
	{
		this.gameState=gameState;
	}
	
	public void draw(Graphics2D g2) 
	{
		
	}
	
	public void update() 
	{
		
	}

	public void notifyMouseReleased() 
	{
		
	}
	
	public int getGameState()
	{
		return gameState;
	}
}
