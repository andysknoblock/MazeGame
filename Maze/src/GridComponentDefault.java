import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;

public class GridComponentDefault implements GridComponent
{

	protected int orientation=0, state=0, x, y, w, h;
	public boolean selected=false;
	protected Color normal = new Color(150,150,255,100);
	protected Color dank = new Color(255,150,150,100);
	GridComponentDefault(int state, int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.state=state;
	}
	public void draw(Graphics2D g2)
	{
	}
	public void drawSample(Graphics2D g2) 
	{
	}
	public int getState()
	{
		return state;
	}
	public void rotateRight() 
	{
		orientation++;
		if (orientation==4)
			orientation=0;
	}
	public void rotateLeft() 
	{
		orientation--;
		if (orientation==-1)
			orientation=3;
		
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
		tempY = MouseInfo.getPointerInfo().getLocation().y;
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
	@Override
	public void updateState(int state) {
		this.state=state;
		
	}

}
