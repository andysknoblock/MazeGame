import java.awt.Color;
import java.awt.Graphics2D;

public class Square 
{
	private int w=5, life=100, inc = 2;
	private float ang,  x, y, dist, distInc;
	Square(float x, float y, float ang, float dist)
	{
		this.x=x;
		this.y=y;
		this.ang=ang;
		this.dist=dist;
		distInc=dist/220;
	}
	public void draw(Graphics2D g2)
	{
		Color temp = g2.getColor();
		g2.translate(x, y);
		g2.rotate(ang);
		g2.fillRect((int)x,(int) y, w, w);
		g2.setColor(new Color(temp.getRed(), temp.getGreen(), temp.getBlue()));
		g2.rotate(-ang);
		g2.translate(-x, -y);
		g2.setColor(temp);
	}
	public void update()
	{
		life+=inc;
		if (life==253)
		{
			inc=-1;
		}
		ang+=Math.PI/180;
		dist-=distInc;
		x = (float)(x+dist*Math.cos(ang));
		y = (float)(y+dist*Math.sin(ang));
	}
	public boolean isLiving()
	{
		if (life<0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
