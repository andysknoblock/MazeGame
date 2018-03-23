
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class FloatingSquares 
{
	private int w, h;
	private Random rando = new Random();
	private Square[] squares = new Square[100];
	FloatingSquares(int width, int height)
	{
		w=width;
		h=height;
		for (int i=0; i<squares.length; i++)
		{
			int x = rando.nextInt(width);
			int y = rando.nextInt(height);
			int w = rando.nextInt(5)+3;
			squares[i] = new Square(x, y, w);
		}
		
	}
	public void draw(Graphics2D g2)
	{
		Color temp = g2.getColor();
		g2.setColor(new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150));
		for (Square temporary: squares)
		{
			temporary.draw(g2);
		}
		g2.setColor(temp);
	}
	public void update()
	{
		for (int i=0; i<squares.length; i++)
		{
			squares[i].moveUp();
			if (!squares[i].isOnscreen())
			{
				int x = rando.nextInt(w);
				int y = h+10;
				int w = rando.nextInt(5)+3;
				squares[i] = new Square(x, y, w);
			}
		}
	}

}
