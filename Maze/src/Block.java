import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
public class Block 
{
	protected char sides;
	protected int x,  y,  w,  h, row, col, yeet=5;
	protected float fill;
	protected boolean top=false, bottom=false, left=false, right=false;
	Block(char sides, int row, int col , int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sides=sides;
		this.row=row;
		this.col=col;
		if (sides == 'n') 
		{
			//do nothing
		}
		else if (sides== 't')
		{
			setTop();
		}
		else if (sides== 'b')
		{
			setBottom();
		}
		else if (sides== 'l')
		{
			setLeft();
		}
		else if (sides== 'r')
		{
			setRight();
		}
		else if (sides== 'a')
		{
			setTop();
			setRight();
		}
		else if (sides== 'c')
		{
			setTop();
			setBottom();
		}
		else if (sides== 'd')
		{
			setTop();
			setLeft();
		}
		else if (sides== 'f')
		{
			setRight();
			setBottom();
		}
		else if (sides== 'g')
		{
			setRight();
			setLeft();
		}
		else if (sides== 'h')
		{
			setBottom();
			setLeft();
		}
		else if (sides== 'i')
		{
			setTop();
			setRight();
			setBottom();
		}
		else if (sides== 'j')
		{
			setTop();
			setRight();
			setLeft();
		}
		else if (sides== 'k')
		{
			setTop();
			setBottom();
			setLeft();
		}
		else if (sides== 'm')
		{
			setRight();
			setBottom();
			setLeft();
		}
		else if (sides== 'o')
		{
			setRight();
			setBottom();
			setLeft();
			setTop();
		}
		else
		{
			//ingore case
		}
	}
	/*s: start
	e: end
	t: top
	b: bottom
	l: left
	r: right
	a: top + right
	c: top + bottom
	d: top + left
	f: right + bottom
	g: right + left
	h: bottom + left
	i: top + right + bottom
	j: top + right + left
	k: top + bottom + left
	m: right + bottom + left
	n: no sides
	o: all sides*/
	public void draw(Graphics2D g2)
	{
		if (yeet<50)
		{
			yeet++;
			fill = (float)(255*5/yeet);
		}
		else
		{
			yeet=5;
		}
		if (bottom)
			drawBottom(g2);
		if (top)
			drawTop(g2);
		if (left)
			drawLeft(g2);
		if (right)
			drawRight(g2);
	}
	protected void drawBottom(Graphics2D g2)
	{
		g2.drawLine(x, y+h, x+w, y+h);
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(fill)));
		g2.drawLine(x, y+h-yeet/5, x+w-1, y+h-yeet/5);
		g2.drawLine(x, y+h-yeet/5, x+w-1, y+h-yeet/5);
		g2.setColor(c);
	}
	protected void drawTop(Graphics2D g2)
	{
		g2.drawLine(x, y, x+w, y);
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(fill)));
		g2.drawLine(x, y+yeet/5, x+w-1, y+yeet/5);
		g2.drawLine(x, y-yeet/5, x+w-1, y-yeet/5);
		g2.setColor(c);
	}
	protected void drawLeft(Graphics2D g2)
	{
		g2.drawLine(x, y, x, y+h);
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(fill)));
		g2.drawLine(x-yeet/5, y, x-yeet/5, y+h-1);
		g2.drawLine(x+yeet/5, y, x+yeet/5, y+h-1);
		g2.setColor(c);
	}
	protected void drawRight(Graphics2D g2)
	{
		g2.drawLine(x+w, y, x+w, y+h);
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(fill)));
		g2.drawLine(x+w-yeet/5, y, x+w-yeet/5, y+h-1);
		g2.drawLine(x+w+yeet/5, y, x+w+yeet/5, y+h-1);
		g2.setColor(c);
	}
	private void setBottom()
	{
		bottom=true;
	}
	private void setTop()
	{
		top=true;
	}
	private void setLeft()
	{
		left=true;
	}
	private void setRight()
	{
		right=true;
	}
	public char getChar()
	{
		return sides;
	}
	public Point getCoords()
	{
		return new Point(row, col);
	}
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	
	public boolean getBottom()
	{
		return bottom;
	}
	public boolean getLeft()
	{
		return left;
	}
	public boolean getTop()
	{
		return top;
	}
	public boolean getRight()
	{
		return right;
	}

}
