import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Squarelets 
{
	private int x, y, w, h;
	private ArrayList<Square> square = new ArrayList<>();
	private Random rando = new Random();
	Squarelets(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.h=h;
		this.w=w;
	}
	public void draw(Graphics2D g2)
	{
		for (Square s:square )
		{
			s.draw(g2);
		}
	}
	public void update()
	{
		if (rando.nextInt(50)<5)
		{
			float ang = rando.nextFloat();
			int dist = rando.nextInt(50)+50;
			Square temp = new Square((float) (x+dist*Math.cos(ang)), (float)(y+dist*Math.sin(ang)), ang, dist);
			System.out.println((float) (x+dist*Math.cos(ang)));
			square.add(temp);
		}
		for (int i=0; i<square.size(); i++)
		{
			Square temp = square.get(i);
			if (!temp.isLiving())
			{
				square.remove(i);
				i--;
			}
		}
		ArrayList<Square> tempo = new ArrayList<>();
		for (int i=0; i<square.size(); i++)
		{
			Square temp = square.get(i);
			square.remove(temp);
			i--;
			temp.update();
			tempo.add(temp);
		}
		square=tempo;
	}

}
