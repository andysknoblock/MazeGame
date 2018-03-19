import java.awt.Color;
import java.awt.Graphics2D;

public class GridSelector 
{
	private int x, y, w, h;
	private final int SIZE=40;
	private GridComponent[] parts = new GridComponent[1];
	private GridComponent selected = null;
	private Color cool = new Color(150,150,255);
	GridSelector(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		parts[0] = new GridLine(x, y, SIZE, SIZE);
	}
	public void draw(Graphics2D g2)
	{
		Color temporary = g2.getColor();
		Color temporary2 = new Color(temporary.getRed(), temporary.getGreen(), temporary.getBlue(), 50);
		g2.drawLine(x, y, x+w, y);
		g2.drawLine(x, y, x, y+h);
		int tempX=x+SIZE;
		for (GridComponent temp: parts)
		{
			g2.setColor(cool);
			if (temp.getState()==0)
			{
				temp.drawSample(g2);
				g2.setColor(temporary);
				g2.drawLine(tempX, y, tempX, y+h);
				if (temp.isSelected())
				{
					g2.setColor(temporary2);
					g2.fillRect(tempX-SIZE, y, SIZE, SIZE);
				}
				tempX+=SIZE;
			}
			else
			{
				temp.draw(g2);
			}
		}
		g2.setColor(temporary);
	}
	public void update()
	{
	}
	public void notifyMouseReleased()
	{
		if (selected!=null)
		{
			
		}
		for (int i=0; i<parts.length; i++)
		{
			if(parts[i].notifyMouseReleased())
			{
				selected = parts[i];
			}
		}
	}

}
