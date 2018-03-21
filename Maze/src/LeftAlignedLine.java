import java.awt.Graphics2D;

public class LeftAlignedLine extends OnScreenDefault
{

	LeftAlignedLine(int x, int y, int w, int h, int row, int column) 
	{
		super(x, y, w, h, row, column);
		ID=0;
	}
	public void draw(Graphics2D g2)
	{
		g2.setColor(normal);
		g2.drawLine(x, y, x, y+h);
	}
	public boolean limitCoordinates(int col, int row)
	{
		if (row==0)
		{
			return true;
		}
		else
			return false;
	}

}
