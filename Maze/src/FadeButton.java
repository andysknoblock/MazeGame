import java.awt.Color;
import java.awt.Graphics2D;

public class FadeButton extends Button
{
	private int fill2=0;

	FadeButton(String str, int x, int y, int h) 
	{
		super(str, x, y, h);
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
		if (fill2<250)
		{
			fill2+=5;
		}
	}
	public void draw(Graphics2D g2)
	{
		if (!configured)
		{
			load(g2);
		}
		Color c = g2.getColor();
		g2.setColor(new Color(0,0,0,fill2));
		g2.fillRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		g2.setColor(c);
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fill2));
		g2.setFont(font);
		g2.drawRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		g2.drawString(str, x+2, y+texth-h/10);
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fill));
		g2.fillRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		g2.setColor(c);
		
	}

}
