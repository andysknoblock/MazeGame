import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface GameState 
{
	public final int WIDTH=640, HEIGHT=480;
	public final int MENU=0, PLAY=1, LEVELSELECT=2, LEVELLIST=3, LEVELEDITOR=4;
	public void draw(Graphics2D g2);
	public void update();
	public void notifyMouseReleased();
	public int getGameState();
	public void setGameState(int gameState);
	public void notifyKeyReleased(KeyEvent e);
	public boolean hasCommunication();
	public Communication getCommunication();
	public void load(int message);

}
