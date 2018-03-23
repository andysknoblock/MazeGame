import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Play implements GameState
{
	private int gameState, level;
	private Maze maze;
	private boolean win = false, gameLevel=false;;
	private Communication commun = null;
	private PulsingColor col = new PulsingColor(100,255,100, 200);
	private Player player = new Player();
	private BounceText winn = null;
	private FadeButton over = new FadeButton("RETURN TO LEVEL SELECTOR", WIDTH/2, HEIGHT/2+50, 30);
	private FadeButton backToMenu = new FadeButton("RETURN TO MAIN MENU", WIDTH/2, HEIGHT/2+90, 30);
	private final int UP=1, RIGHT=2, DOWN=3, LEFT=4;
	Play (int gameState, int level)
	{
		this.gameState=gameState;
		init(level);
	}
	public void init(int level)
	{
		this.level=level;
		if (level<=30)
		{
			gameLevel=true;
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
		if (winn!=null)
		{
			if (gameLevel)
			{
				commun = new Communication(PLAY, level+1);
			}
			winn.draw(g2);
			over.draw(g2);
			backToMenu.draw(g2);
		}
	}
	public void update() 
	{
		col.update();
		if(maze.checkWin(player.getCoords()) && !win)
		{
			winn = new BounceText("LEVEL COMPLETE", WIDTH/2, HEIGHT/2, 50);
			win=true;
		}
		if (win)
		{
			over.update();
			backToMenu.update();
		}
	}

	public void notifyMouseReleased()
	{
		if (over.isReleased())
		{
			gameState=LEVELSELECT;
		}
		if (backToMenu.isReleased())
		{
			gameState=MENU;
		}
	}

	public int getGameState() 
	{
		return gameState;
	}
	public void notifyKeyReleased(KeyEvent e) 
	{
		if (!win)
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
	@Override
	public void setGameState(int gameState) {
		this.gameState=gameState;
		
	}
	@Override
	public boolean hasCommunication() {
		if (commun!=null)
		{
			return true;
		}
		else
			return false;
	}
	@Override
	public Communication getCommunication() {
		return commun;
	}
	@Override
	public void load(int message) 
	{
		init(message);
	}

}
