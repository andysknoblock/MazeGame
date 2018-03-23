import java.awt.Graphics2D;

public class Slider 
{
	private int x, y, w, h, yHeight, maxyHeight, curY=0;
	float inc;
	Slider(int yHeight, int maxyHeight, int x, int y, int w, int h)
	{
		this.yHeight=yHeight;
		this.maxyHeight=maxyHeight;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	public void draw (Graphics2D g2)
	{
		g2.drawLine(x+w/2, y, x+w/2, y+h);
	}
	public int getY()
	{
		return curY;
	}

}
