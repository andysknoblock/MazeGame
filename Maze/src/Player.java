import java.awt.Graphics2D;
import java.awt.Point;

public class Player 
{
	private int row, col, baseX, baseY, xDist, yDist, x, y, rows, cols;
	public void configure(int row, int col, int baseX, int baseY, int width, int height, int rows, int cols)
	{
		this.row=row;
		this.col=col;
		this.baseX=baseX;
		this.baseY=baseY;
		xDist = width/cols;
		yDist = height/rows;
		this.cols=cols;
		this.rows=rows;
		update();
		
	}
	public void draw(Graphics2D g2)
	{
	
		g2.drawOval(x, y, xDist, yDist);
	}
	
	public void update()
	{
		x = baseX+col*xDist;
		y = baseY+row*yDist;
	}
	public void moveUp()
	{
		if (row>0)
			row-=1;
		update();
	}
	public void moveDown()
	{
		if (row<rows-1)
			row+=1;
		update();
	}
	public void moveLeft()
	{
		if (col>0)
			col-=1;
		update();
	}
	public void moveRight()
	{
		if (col<cols-1)
			col+=1;
		update();
	}
	public Point getCoords()
	{
		return new Point(col, row);
	}

}
