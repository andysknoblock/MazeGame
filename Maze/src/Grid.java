import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Grid extends MouseOverObject
{
	private int rows, cols;
	private float xStep, yStep;
	private Color greyish = new Color(200,200,200,100);
	Set<GridComponent> gridData = new HashSet<>();
	Grid(int x, int y, int width, int height, int rows, int cols)
	{
		super(x,y,width,height);
		this.rows=rows;
		this.cols=cols;
		xStep = (float)(w)/cols;
		yStep = (float)(h)/rows;
		
	}
	public void draw (Graphics2D g2)
	{
		Color col=g2.getColor();
		g2.setColor(Color.WHITE);
		g2.drawLine(x, y, x+w, y);
		g2.drawLine(x, y+h, x+w, y+h);
		g2.drawLine(x, y, x, y+h);
		g2.drawLine(x+w, y, x+w, y+h);
		for (GridComponent grd: gridData)
		{
			grd.draw(g2);
		}
		g2.setColor(greyish);
		for (int i=0; i<rows; i++)
		{
			g2.drawLine(x, toY(i), x+w, toY(i));
		}
		for (int i=0; i<cols; i++)
		{
			g2.drawLine(toX(i), y, toX(i), y+h);
		}
		g2.setColor(col);
		
	}
	public Point mouseOver()
	{
		if (isOver())
		{
			Point p = MouseInfo.getPointerInfo().getLocation();
			double tempX=p.x;
			double tempY=p.y;
			tempX-=x;
			tempY-=y;
			tempX=tempX/xStep;
			tempY=tempY/yStep;
			p = new Point((int) tempX, (int) tempY);
			return p;
		}
		else
		{
			return null;
		}
	}
	public void updateRowsCols(int r, int c)
	{
		rows=r;
		cols=c;
		xStep = (float)(w)/cols;
		yStep = (float)(h)/rows;
	}
	public int toX(int colX)
	{
		float ans = x+xStep*colX;
		return (int)ans;
	}
	public int toY(int colY)
	{
		float ans = y+yStep*colY;
		return (int)ans;
	}
	public void addGridComponent(GridComponent grdComp, int row, int column)
	{
		GridComponent temp = grdComp;
		temp.updateLocation(toX(rows), toY(column), (int) xStep, (int) yStep);
		System.out.println(toX(rows));
		gridData.add(temp);
	}
	

}
