import java.awt.Color;
import java.awt.Graphics2D;

public class SquareBoi 
{
	private float x, y, w, h, fill;
	SquareBoi(float x, float y, float w, float h, float fill)
	{
		this.x=x;
		this.w=w;
		this.y=y;
		this.h=h;
		this.fill=fill;
		if (fill>200)
			fill=200;
	}
	public void draw(Graphics2D g2)
	{
		if (fill==0)
		{
			fill=200;
		}
		else
		{
			fill-=5;
		}
		Color col = g2.getColor();
		g2.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), (int)(200-fill)/2));
		g2.fillRect((int)(x+w/2-w/2*fill/200),(int)(y+h/2-h/2*fill/200),(int)(w*fill/200),(int)(h*fill/200));
		g2.setColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), (int)(200-fill)));
		g2.drawRect((int)(x+w/2-w/2*fill/200),(int)(y+h/2-h/2*fill/200),(int)(w*fill/200),(int)(h*fill/200));
		g2.setColor(col);
	}
	

	
}
