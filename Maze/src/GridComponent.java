import java.awt.Graphics2D;

public interface GridComponent
{
	public void drawSample(Graphics2D g2);
	public boolean notifyMouseReleased();
	public boolean isSelected();
	public void updateLocation(int rows, int cols, int w, int h);
	public int getID();
}
