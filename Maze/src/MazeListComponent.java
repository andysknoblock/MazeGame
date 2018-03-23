import java.awt.Color;
import java.awt.Graphics2D;

public class MazeListComponent extends MouseOverObject
{
	private String ID;
	private CenteredText ct;
	private int fill=0;
	
	MazeListComponent(String ID, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.ID=ID;
		ct = new CenteredText("MAZE ID: " + ID, x+w/2, y+h/4, h/2);
	}
	public String getID()
	{
		return ID;
	}
	public void draw(Graphics2D g2)
	{
		g2.drawLine(x, y+h, x+w, y+h);
		ct.draw(g2);
		if (fill!=0)
		{
			Color temp = g2.getColor();
			g2.setColor(new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), fill));
			g2.fillRect(x, y, w, h);
			g2.setColor(temp);
		}
	}
	public void update()
	{
		if (isOver() && fill<160)
		{
			fill+=20;
		}
		else if (!isOver())
		{
			if (fill>0)
			{
				fill-=20;
			}
		}
	}
	public void updateY(int y)
	{
		this.y=y;
		
	}

}
