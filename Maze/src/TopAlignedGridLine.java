import java.awt.Graphics2D;

public class TopAlignedGridLine extends GridComponentDefault
{
	TopAlignedGridLine(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		ID=1;
	}
	public void drawSample(Graphics2D g2) 
	{
		g2.drawLine(x, y+h/4, x+w, y+h/4);
	}
}
