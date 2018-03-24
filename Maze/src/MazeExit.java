
import java.awt.Graphics2D;
import java.awt.Point;

public class MazeExit 
{
	private int row, col;
	private SquareBoi[] square = new SquareBoi[5];
	public void setEnd(int x, int y, int w, int h, int col, int row)
	{
		
		this.col=col;
		this.row=row;
		for (int i=0; i<square.length; i++)
		{
			square[i] = new SquareBoi(x, y, w, h, 200/(square.length)*i);
		}
	}
	public Point getCoords()
	{
		return new Point(col, row);
	}
	public void draw(Graphics2D g2)
	{
		for (int i=0; i<square.length; i++)
		{
			square[i].draw(g2);
		}
	}

}
