import java.awt.Color;
import java.awt.Graphics2D;

public class Pulse 
{
	private int x, y, w, h, fill=100;
	Pulse(int x,int  y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.h=h;
		this.w=w;
	}
	public void draw(Graphics2D g2)
	{
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fill));
		g2.fillOval(x+w/2-w/2*fill/100, y+h/2-h/2*fill/100, w*fill/100, h*fill/100);
		g2.drawOval(x+w/2-w/2*fill/100, y+h/2-h/2*fill/100, w*fill/100-1, h*fill/100-1);
		g2.setColor(c);
	}
	public void update()
	{
		fill-=5;
	}
	public boolean isLiving()
	{
		if (fill>5)
			return true;
		else
			return false;
	}

}
