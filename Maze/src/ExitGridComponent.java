import java.awt.Graphics2D;

public class ExitGridComponent extends GridComponentDefault
{
	private CenteredText text;

	ExitGridComponent(int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		ID=3;
		text = new CenteredText("Exit", x+w/2, y+h/2-7, 15);
	}
	public void drawSample(Graphics2D g2) 
	{
		text.draw(g2);
	}

}
