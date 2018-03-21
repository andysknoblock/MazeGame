import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

public class OnScreenDefault implements OnScreenComponent
{

	protected int x, y, w, h, ID, row, col;
	public boolean selected=false; //canRotate=true;
	protected Color normal = new Color(150,150,255);
	protected Color dank = new Color(255,150,150);
	OnScreenDefault(int x, int y, int w, int h, int row, int col)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.row=row;
		this.col=col;
	}
	public void draw(Graphics2D g2)
	{
	}
	/*public void rotateRight() 
	{
		if (canRotate)
		{
			orientation++;
			if (orientation==4)
				orientation=0;
		}
	}
	public void rotateLeft() 
	{
		if (canRotate)
		{
			orientation--;
			if (orientation==-1)
				orientation=3;
		}
		
	}*/
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
	public int getID()
	{
		return ID;
	}
	@Override
	public int getRow() 
	{
		return row;
	}
	@Override
	public int getCol() 
	{
		return col;
	}
	@Override
	public Point getLocation() 
	{
		return new Point(row, col);
	}
	@Override
	public boolean limitCoordinates(int col, int row) 
	{
		return false;
	}

}