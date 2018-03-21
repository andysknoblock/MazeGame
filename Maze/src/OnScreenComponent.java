import java.awt.Graphics2D;
import java.awt.Point;

public interface OnScreenComponent 
{

	public void draw(Graphics2D g2);
	//public void rotateRight();
	//public void rotateLeft();
	public boolean notifyMouseReleased();
	public boolean isSelected();
	public void updateLocation(int rows, int cols, int w, int h);
	public int getID();
	//public void canRotate(boolean lol);
	public int getRow();
	public int getCol();
	public Point getLocation();
	public boolean limitCoordinates(int col, int row);
	//public int getOrientation();
}
