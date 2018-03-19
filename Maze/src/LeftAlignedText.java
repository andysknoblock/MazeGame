import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class LeftAlignedText extends MouseOverObject
{
	private String str;
	private Font font;
	private int texth, textw;
	private boolean configured=false;
	LeftAlignedText(String str, int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		texth = h-2;
		this.str=str;
	}
	LeftAlignedText(String str, int x, int y, int h) 
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
		if (w==0)
		{
			w = fm.stringWidth(str)+3;
			textw = fm.stringWidth(str);
		}
		configured=true;
	}
	public void draw(Graphics2D g2)
	{
		if (!configured)
		{
			load(g2);
		}
		g2.setFont(font);
		g2.drawString(str, x, y+texth-h/10);
		
	}
	public int getMinX()
	{
		return x;
	}
	public int getMaxX()
	{
		return textw;
	}
	

}