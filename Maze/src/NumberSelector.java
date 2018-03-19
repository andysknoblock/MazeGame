import java.awt.Graphics2D;

public class NumberSelector
{
	private boolean configured = false;
	private int offset=20, x, y, val;
	private LeftAlignedText disp;
	private String str;
	private PressTriangle[] tris = new PressTriangle[2];
	NumberSelector(int val, String str,int x, int y)
	{
		disp = new LeftAlignedText((str + val), x+offset, y, 15);
		this.x=x; 
		this.y=y;
		this.val=val;
		this.str=str;
	}
	public void draw(Graphics2D g2)
	{
		disp.draw(g2);
		if (!configured)
		{
			tris[0] = new PressTriangle(true, x, y, 15);
			tris[1] = new PressTriangle(false, x + disp.getMaxX()+offset+5, y, 15);
			configured=true;
		}
		tris[0].draw(g2);
		tris[1].draw(g2);
	}
	public int update()
	{
		tris[0].update();
		tris[1].update();
		return val;
	}
	public void notifyMouseReleased()
	{
		if (tris[0].notifyMouseReleased()==true && val>2)
		{
			val--;
			updateDisp();
		}
		if (tris[1].notifyMouseReleased()==true)
		{
			val++;
			updateDisp();
		}
	}
	public void updateDisp()
	{
		disp = new LeftAlignedText((str + val), x+offset, y, 15);
		configured=false;
	}

}
