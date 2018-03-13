import java.awt.Color;

public class PulsingColor 
{
	private int r, g,  b, trans, rinc=1, ginc=0, binc=0;
	PulsingColor(int r, int g, int b, int trans)
	{
		this.r=r;
		this.g=g;
		this.b=b;
		this.trans=trans;
	}
	public void update()
	{
		r+=rinc;
		g+=ginc;
		b+=binc;
		if (r==255)
		{
			rinc=-1;
			ginc=0;
			binc=1;
		}
		if (b==255)
		{
			rinc=0;
			ginc=1;
			binc=-1;
		}
		if (g==255)
		{
			rinc=1;
			ginc=-1;
			binc=0;
		}
		
	}
	public Color getColor()
	{
		return new Color(r,b,g, trans);
	}

}
