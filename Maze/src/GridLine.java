import java.awt.Graphics2D;
import java.awt.MouseInfo;

public class GridLine implements GridComponent 
{
	private int orientation=0, state=0, x, y, w, h;
	public final int ID = 0;
	public boolean selected=false;
	GridLine(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		state=0;
	}
	public void draw(Graphics2D g2)
	{
		if (orientation==0)
		{
			g2.drawLine(x, y, x, y+h);
		}
		else if (orientation==1)
		{
			g2.drawLine(x, y, x+w, y);
		}
		else if (orientation==2)
		{
			g2.drawLine(x+w, y, x+w, y+h);
		}
		else
		{
			g2.drawLine(x, y+h, x+w, y+h);
		}
	}
	public void drawSample(Graphics2D g2) 
	{
		g2.drawLine(x+w/2, y, x+w/2, y+h);
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
	public void updateState()
	{
		state=1;
	}
	private boolean isOver() 
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
	

}
