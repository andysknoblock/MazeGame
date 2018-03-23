import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;

public class LevelList implements GameState
{
	private MazeListComponent[] mazes;
	private Color c = new Color(200,200,255,200);
	private int gameState, cameraY=0, baseY=50, maxX=WIDTH-70;
	private String path = "mazes/CustomMazes";
	private File folder;
	private File[] fileList;
	private Button menu = new Button("Return To Menu", 100, 15, 25);
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
	}

	public void update() 
	{
		for (int i=0; i< mazes.length; i++)
		{
			mazes[i].update();
		}
		menu.update();
	}

	public void notifyMouseReleased()
	{
		if (menu.isReleased())
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
		
	}
	@Override
	public void setGameState(int gameState) {
		this.gameState=gameState;
		
	}
	@Override
	public boolean hasCommunication() 
	{
		return false;
	}
	@Override
	public Communication getCommunication() {
		return null;
	}
	@Override
	public void load(int message) 
	{
		
	}
	private int toWorldY(int y)
	{
		return y-cameraY;
	}

}