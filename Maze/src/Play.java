import java.awt.Graphics2D;

public class Play implements GameState
{
	private int gameState;
	private Maze maze;
	private PulsingColor col = new PulsingColor(100,255,100, 200);
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
			maze = new Maze(path,50, 50, WIDTH-100, HEIGHT-100);
			maze.loadMaze();
		}
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(col.getColor());
		maze.draw(g2);
	}
	public void update() 
	{
		col.update();
	}

	public void notifyMouseReleased()
	{
		
	}

	public int getGameState() 
	{
		return gameState;
	}

}
