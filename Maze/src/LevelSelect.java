import java.awt.Graphics2D;

public class LevelSelect implements GameState
{
	private int gameState;
	LevelSelect (int gameState)
	{
		this.gameState=gameState;
	}
	public void draw(Graphics2D g2) 
	{
		g2.drawString("Level Select Mode", WIDTH/2, 50);
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