import java.awt.Graphics2D;
public class Block 
{
	private char sides;
	private int x,  y,  w,  h;
	Block(char sides, int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sides=sides;
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
		if (sides == 'n')
		{
			//do nothing
		}
		else if (sides== 't')
		{
			drawTop(g2);
		}
		else if (sides== 'b')
		{
			drawBottom(g2);
		}
		else if (sides== 'l')
		{
			drawLeft(g2);
		}
		else if (sides== 'r')
		{
			drawRight(g2);
		}
		else
		{
			System.out.println("Invalid maze data");
		}
	}
	private void drawBottom(Graphics2D g2)
	{
		g2.drawLine(x, y+h, x+w, y+h);
	}
	private void drawTop(Graphics2D g2)
	{
		g2.drawLine(x, y, x+w, y);
	}
	private void drawLeft(Graphics2D g2)
	{
		g2.drawLine(x, y, x, y+h);
	}
	private void drawRight(Graphics2D g2)
	{
		g2.drawLine(x+w, y, x+w, y+h);
	}

}
