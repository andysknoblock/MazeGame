import java.awt.Graphics2D;

public class Play implements GameState
{
	private int gameState;
	private Maze maze;
	Play (int gameState, int level)
	{
		this.gameState=gameState;
		init(level);
		
	}
	public void init(int level)
	{
		if (level<=30)
		{
			String path = "src/level" + level + ".maz";
			maze = new Maze(path);
			maze.loadMaze();
		}
	}
	public void draw(Graphics2D g2) 
	{
		g2.drawString("Play Mode", WIDTH/2, 50);
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
