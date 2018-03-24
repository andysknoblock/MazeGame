import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Player 
{
	private ArrayList<Pulse> pulses = new ArrayList<Pulse>();
	private int row, col, baseX, baseY, x, y, rows, cols;
	private float xDist, yDist;
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
		for (Pulse p:pulses)
		{
			p.draw(g2);
		}
	
		//g2.drawOval(x, y, xDist, yDist);
		//g2.fillOval(x, y, xDist+1, yDist+1);
		Color c = g2.getColor();
		int count = 20;
		float tempX = x+xDist/2, tempY = y+yDist/2;
		float xInc = xDist/count, yInc = yDist/count;
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 25));
		for (int i=0; i<count; i++)
		{
			g2.fillOval((int)tempX, (int)tempY, (int) (xInc*i+1), (int) (yInc*i+1));
			//g2.drawOval((int)tempX, (int)tempY, (int) (xInc*i), (int) (yInc*i));
			tempX =  (x+xDist/2-(xInc*i+1)/2); tempY = (y+yDist/2-(yInc*i+1)/2);
		}
		g2.setColor(c);
	}
	
	public void update()
	{
		ArrayList<Pulse> pulseeees = new ArrayList<Pulse>();
		for (Pulse p:pulses)
		{
			p.update();
			pulseeees.add(p);
		}
		pulses=pulseeees;
		for (int i=0; i< pulses.size(); i++)
		{
			if (!pulses.get(i).isLiving())
			{
				pulses.remove(i);
				i--;
			}
		}
		x = (int) (baseX+col*xDist);
		y = (int) (baseY+row*yDist);
	}
	public void moveUp()
	{
		makePulse();
		if (row>0)
			row-=1;
		update();
	}
	public void moveDown()
	{
		makePulse();
		if (row<rows-1)
			row+=1;
		update();
	}
	public void moveLeft()
	{
		makePulse();
		if (col>0)
			col-=1;
		update();
	}
	public void moveRight()
	{
		makePulse();
		if (col<cols-1)
			col+=1;
		update();
	}
	public void makePulse()
	{
		Pulse temp = new Pulse(x, y, (int)xDist, (int)yDist);
		pulses.add(temp);
	}
	public Point getCoords()
	{
		return new Point(col, row);
	}

}
