
import java.awt.Graphics2D;
import java.util.Random;

public class Square
{
	private int x, y, w, yvel;
	Square(int x, int y, int w)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		Random rando = new Random();
		yvel = rando.nextInt(3)+2;
	}
	public void draw(Graphics2D g2)
	{
		g2.fillRect(x, y, w, w);	
	}
	public void moveUp()
	{
		y-=yvel;
	}
	public boolean isOnscreen()
	{
		if (y+w<0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
