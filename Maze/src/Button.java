import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Button extends MouseOverObject
{
	private String str;
	private Font font;
	private int texth, fill=0;
	private boolean configured=false;
	Button(String str, int x, int y, int h) 
	{
		super(0, 0, 0, 0);
		texth = h-2;
		this.h=h;
		this.y=y;
		this.x=x;
		this.str=str;
	}
	public void load(Graphics2D g2)
	{
		font = new Font("Purisa", Font.BOLD, texth);
		g2.setFont(font);
		FontMetrics fm = g2.getFontMetrics();
		w = fm.stringWidth(str)+3;
		x = x-w/2;
		configured=true;
	}
	public void draw(Graphics2D g2)
	{
		if (!configured)
		{
			load(g2);
		}
		g2.setFont(font);
		g2.drawRoundRect(x, y, w, h, h/3, h/3);
		g2.drawString(str, x+2, y+texth-h/10);
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fill));
		g2.fillRoundRect(x, y, w, h, h/3, h/3);
		g2.setColor(c);
		
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
	public boolean isReleased()
	{
		return isOver();
	}
	

}
