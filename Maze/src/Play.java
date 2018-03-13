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
		maze.draw(g2, 50, 50, WIDTH-100, HEIGHT-100);
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
