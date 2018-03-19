import java.awt.Graphics2D;

public class GridLine extends GridComponentDefault
{
	public final int ID=0;
	GridLine(int state, int x, int y, int w, int h)
	{
		super(state, x, y, w, h);
	}
	public void draw(Graphics2D g2)
	{
		if (!selected)
			g2.setColor(normal);
		else
			g2.setColor(dank);
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
}
