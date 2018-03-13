import java.awt.Graphics2D;

public class LevelEditor implements GameState
{
	private int gameState;
	LevelEditor (int gameState)
	{
		this.gameState=gameState;
	}
	public void draw(Graphics2D g2) 
	{
		g2.drawString("Editor Mode", WIDTH/2, 50);
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