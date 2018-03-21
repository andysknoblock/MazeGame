import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Play implements GameState
{
	private int gameState;
	private Maze maze;
	private PulsingColor col = new PulsingColor(100,255,100, 200);
	private Player player = new Player();
	private final int UP=1, RIGHT=2, DOWN=3, LEFT=4;
	Play (int gameState, int level)
	{
		this.gameState=gameState;
		init(level);
		
	}
	public void init(int level)
	{
		if (level<=30)
		{
			String path = "mazes/level" + level + ".maz";
			maze = new Maze(path,50, 50, WIDTH-100, HEIGHT-100);
			maze.loadMaze();
			Point startCoords = maze.getStartCoords();
			Point dimensions = maze.getDimensions();
			player.configure(startCoords.x, startCoords.y, 50, 50, WIDTH-100, HEIGHT-100, dimensions.x, dimensions.y);
		}
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(col.getColor());
		maze.draw(g2);
		player.draw(g2);
	}
	public void update() 
	{
		col.update();
		if(maze.checkWin(player.getCoords()))
		{
			System.out.println("FREAKING WINNING");
		}
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
		int movement = e.getKeyCode();
		if (movement==38)
		{
			move(UP);
		}
		if (movement==39)
		{
			move(RIGHT);
		}
		if (movement==40)
		{
			move(DOWN);
		}
		if (movement==37)
		{
			move(LEFT);
		}
	}
	private void move(int direction)
	{
		if (direction==LEFT)
		{
			if (maze.canMoveLeft(player.getCoords()))
			{
				player.moveLeft();
			}
		}
		else if (direction==RIGHT)
		{
			if (maze.canMoveRight(player.getCoords()))
			{
				player.moveRight();
			}
		}
		else if (direction==UP)
		{
			if (maze.canMoveUp(player.getCoords()))
			{
				player.moveUp();
			}
		}
		else if (direction==DOWN)
		{
			if (maze.canMoveDown(player.getCoords()))
			{
				player.moveDown();
			}
		}
		else
		{
			System.out.println("Unknown movement direction");
		}
	}

}
