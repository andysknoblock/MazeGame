import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class PressTriangle extends MouseOverObject
{
	private Polygon p = new Polygon();
	private int fill=0;
	private boolean left=false;
	PressTriangle(boolean left, int x, int y, int w)
	{
		super(x, y, w, w);
		this.left=left;
		if (left)
		{
			p.addPoint(x+w, y);
			p.addPoint(x+w, y+h);
			p.addPoint(x, y+h/2);
		}
		else
		{
			p.addPoint(x, y);
			p.addPoint(x, y+h);
			p.addPoint(x+w, y+h/2);
		}
	}
	public void draw(Graphics2D g2)
	{
		g2.drawPolygon(p);
		Color temp = g2.getColor();
		if (fill>0)
		{
			g2.setColor(new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), fill));
			g2.fillPolygon(p);
		}
		g2.setColor(temp);
	}
	public void updateY(int y)
	{
		this.y=y;
		p = new Polygon();
		if (left)
		{
			p.addPoint(x+w, y);
			p.addPoint(x+w, y+h);
			p.addPoint(x, y+h/2);
		}
		else
		{
			p.addPoint(x, y);
			p.addPoint(x, y+h);
			p.addPoint(x+w, y+h/2);
		}
	}
	public void update()
	{
		if (isOver() && fill<200)
		{
			fill+=20;
		}
		else
		{
			if (fill>0 && !isOver())
			{
				fill-=20;
			}
		}
	}
	public boolean notifyMouseReleased()
	{
		if (isOver())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
