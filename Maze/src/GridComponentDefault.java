import java.awt.Graphics2D;
import java.awt.MouseInfo;

public class GridComponentDefault implements GridComponent
{

	protected int x, y, w, h, ID;
	public boolean selected=false;
	GridComponentDefault(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	public void drawSample(Graphics2D g2) 
	{
	}
	public void updateLocation(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	protected boolean isOver() 
	{
		int tempX, tempY;
		tempX = MouseInfo.getPointerInfo().getLocation().x;
		tempY = MouseInfo.getPointerInfo().getLocation().y-25;
		if (tempX>=x && tempX<=x+w && tempY>=y && tempY<=y+h)
			return true;
		else
			return false;
	}
	public boolean notifyMouseReleased()
	{
		if (isOver())
		{
			selected=true;
			return true;
		}
		else
		{
			selected=false;
			return false;
		}
	}
	@Override
	public boolean isSelected() 
	{
		return selected;
	}
	public int getID()
	{
		return ID;
	}

}
