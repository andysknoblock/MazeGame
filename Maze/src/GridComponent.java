import java.awt.Graphics2D;

public interface GridComponent
{
	public void draw(Graphics2D g2);
	public void drawSample(Graphics2D g2);
	public int getState();
	public void rotateRight();
	public void rotateLeft();
	public boolean notifyMouseReleased();
	public boolean isSelected();
	public void updateLocation(int rows, int cols, int w, int h);
	public void updateState(int state);

}
