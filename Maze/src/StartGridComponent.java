import java.awt.Graphics2D;

public class StartGridComponent extends GridComponentDefault
{
	private CenteredText text;

	StartGridComponent(int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		ID=2;
		text = new CenteredText("Start", x+w/2, y+h/2-7, 15);
	}
	public void drawSample(Graphics2D g2) 
	{
		text.draw(g2);
	}

}
