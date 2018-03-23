import java.awt.Color;
import java.awt.Graphics2D;

public class NumberButton extends Button
{
	private int value;
	private CenteredText levelNum;

	NumberButton(int val, int x, int y, int h, int w) 
	{
		super(Integer.toString(val), x, y, h,w );
		value=val;
		levelNum=new CenteredText(str, x, y, w, h-10);
	}
	public void draw(Graphics2D g2, boolean greyed)
	{
		if (!configured)
		{
			load(g2);
		}
		g2.setFont(font);
		if (greyed)
		{
			g2.setColor(new Color(200,200,200,50));
			g2.fillRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		}
		g2.drawRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		levelNum.draw(g2);
		
		Color c = g2.getColor();
		g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), fill));
		g2.fillRoundRect(x-fill/40, y-fill/40, w+fill/20, h+fill/20, h/3, h/3);
		g2.setColor(c);
	}
	public int getValue()
	{
		return value;
	}

}
