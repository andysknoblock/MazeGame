import java.awt.Graphics2D;
import java.awt.Point;

public class MazeExit 
{
	private int x,y,w,h, row, col;
	public void setEnd(int x, int y, int w, int h, int col, int row)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.col=col;
		this.row=row;
	}
	public Point getCoords()
	{
		return new Point(col, row);
	}
	public void draw(Graphics2D g2)
	{
		g2.drawRect(x+w/4, y+h/4, w/2, h/2);
	}

}
