import java.awt.Graphics2D;

public class LeftAlignedGridLine extends GridComponentDefault
{
	LeftAlignedGridLine(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		ID=0;
	}
	
	public void drawSample(Graphics2D g2) 
	{
		g2.drawLine(x+w/4, y, x+w/4, y+h);
	}
}
