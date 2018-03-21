import java.awt.Graphics2D;

public class EndComponent extends OnScreenDefault
{
	private CenteredText text;
	EndComponent(int x, int y, int w, int h, int row, int col)
	{
		super(x, y, w, h, row, col);
		ID=3;
		text=new CenteredText("E", x+w/2, y,h-2);
	}

	

	public void draw(Graphics2D g2)
	{
		g2.setColor(normal);
		text.draw(g2);
	}

}
