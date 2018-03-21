import java.awt.Graphics2D;

public class TopAlignedLine extends OnScreenDefault
{

	TopAlignedLine(int x, int y, int w, int h, int row, int col)
	{
		super(x, y, w, h, row, col);
		ID=1;
	}

	public void draw(Graphics2D g2)
	{
		g2.setColor(normal);
		g2.drawLine(x, y, x+w, y);
	}
	public boolean limitCoordinates(int col, int row)
	{
		if (col==0)
		{
			return true;
		}
		else
			return false;
	}

}
