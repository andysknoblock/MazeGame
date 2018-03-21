import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class FadingMessage extends MouseOverObject
{
	private String str;
	private Font font;
	private int texth, life=80;
	private boolean configured=false;
	FadingMessage(String str, int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		texth = h-2;
		this.str=str;
	}
	FadingMessage(String str, int x, int y, int h) 
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
		font = new Font("Purisa", Font.ITALIC, texth);
		g2.setFont(font);
		FontMetrics fm = g2.getFontMetrics();
		if (w==0)
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
		Color temp = g2.getColor();
		g2.setColor(new Color(255,50,50,life*255/80));
		g2.drawString(str, x, y+texth-h/10-(100-life)/5);
		g2.setColor(temp);
	}
	public void update()
	{
		life--;
	}
	public boolean isLiving()
	{
		if (life<=0)
			return false;
		else
			return true;
	}
}
	
