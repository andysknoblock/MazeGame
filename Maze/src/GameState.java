import java.awt.Graphics2D;

public interface GameState 
{
	public final int WIDTH=640, HEIGHT=480;
	public void draw(Graphics2D g2);
	public void update();
	public void notifyMouseReleased();
	public int getGameState();

}
