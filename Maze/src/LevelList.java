import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

public class LevelList implements GameState
{
	private MazeListComponent[] mazes;
	private Color c = new Color(200,200,255,200);
	private int gameState, cameraY=0, baseY=50, maxX=WIDTH-70;
	private boolean playNotEdit = true;
	private String path = "mazes/CustomMazes";
	private File folder;
	private File[] fileList;
	private Button menu = new Button("Return To Menu", 100, 15, 25);
	private Button edit = new Button("Edit", WIDTH-35, 82, 15);
	private Button play = new Button("Play", WIDTH-35, 60, 15);
	private PressTriangle mode = new PressTriangle(false, WIDTH-65, 64, 8);
	private Communication commun = null;
	LevelList (int gameState)
	{
		this.gameState=gameState;
		loadAvailableMazes();
	}
	private void loadAvailableMazes()
	{
		folder = new File(path);
		fileList = folder.listFiles(); 
		mazes = new MazeListComponent[fileList.length];
		for (int i=0; i<fileList.length; i++)
		{
			String temp = fileList[i].getPath().substring(fileList[i].getPath().length()-10,fileList[i].getPath().length()-4);
			mazes[i] = new MazeListComponent(temp, 0, baseY+i*40, maxX, 40);
		}
	}
	public void draw(Graphics2D g2) 
	{
		g2.setColor(c);
		
		for (MazeListComponent temp : mazes)
		{
			temp.draw(g2);
		}
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, WIDTH, baseY);
		g2.setColor(c);
		g2.drawLine(0, baseY, maxX, baseY);
		g2.drawLine(maxX, HEIGHT, maxX, baseY);
		menu.draw(g2);
		edit.draw(g2);
		play.draw(g2);
		mode.draw(g2);
	}

	public void update() 
	{
		for (int i=0; i< mazes.length; i++)
		{
			mazes[i].update();
		}
		menu.update();
		edit.update();
		play.update();
	}

	public void notifyMouseReleased()
	{
		if (menu.isReleased())
		{
			gameState=MENU;
		}
		if (edit.isReleased())
		{
			mode.updateY(edit.y+4);
			playNotEdit = false;
		}
		if (play.isReleased())
		{
			mode.updateY(play.y+4);
			playNotEdit = true;
		}
		for (int i=0; i< mazes.length; i++)
		{
			if (mazes[i].isOver())
			{
				if (playNotEdit)
				{
					gameState = PLAY;
					commun = new Communication(PLAY, Integer.parseInt(1+mazes[i].getID()));
				}
				else
				{
					gameState = LEVELEDITOR;
					commun = new Communication(LEVELEDITOR, Integer.parseInt(mazes[i].getID()));
				}
			}
		}
	}

	public int getGameState() 
	{
		return gameState;
	}
	public void notifyKeyReleased(KeyEvent e) 
	{
		
	}
	@Override
	public void setGameState(int gameState) {
		this.gameState=gameState;
		
	}
	@Override
	public boolean hasCommunication() 
	{
		if (commun!=null)
			return true;
		else
			return false;
	}
	@Override
	public Communication getCommunication() 
	{
		return commun;
	}
	@Override
	public void load(int message) 
	{
		
	}

}