import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Settings implements GameState
{
	private int gameState;
	Settings (int gameState)
	{
		this.gameState=gameState;
	}
	public void draw(Graphics2D g2) 
	{
		g2.drawString("Settings Mode", WIDTH/2, 50);
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
	public void notifyKeyReleased(KeyEvent e) 
	{
		
	}

}