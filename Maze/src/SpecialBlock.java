import java.awt.Graphics2D;

public class SpecialBlock extends Block
{
	private static final int WIDTH = 640, HEIGHT=480;
	private boolean upwards=false, leftwards=false, rightwards=false, downwards=false;
	SpecialBlock(char sides, int row, int col , int x, int y, int w, int h, int rows, int cols)
	{
		super(sides, row, col, x, y, w, h);
		if (col==0)
		{
			upwards=true;
			if (row==0)
				left=true;
			if (row==rows-1)
				right =true;
		}
		else if (col==cols-1)
		{
			
			downwards=true;
			if (row==0)
				left=true;
			if (row==rows-1)
				right =true;
		}
		else if (row==0)
		{
			
			leftwards = true;
			if (col==0)
				top=true;
			if (col==cols-1)
				bottom=true;
		}
		else if (col==col-1)
		{
			System.out.print("EEK");
			leftwards = true;
			if (col==0)
				top=true;
			if (col==cols-1)
				bottom=true;
		}
		else
		{
			System.out.println("FREAKING ERROR special block is not on end of maze");
		}
	}
	public void draw(Graphics2D g2)
	{
		if (upwards)
			drawUpwards(g2);
		if (downwards)
			drawDownwards(g2);
		if (leftwards)
			drawLeftwards(g2);
		if (rightwards)
			drawRightwards(g2);
		if (bottom)
			drawBottom(g2);
		if (top)
			drawTop(g2);
		if (left)
			drawLeft(g2);
		if (right)
			drawRight(g2);
		
	}
	public void drawUpwards(Graphics2D g2)
	{
		for (int tempY=y; tempY>0; tempY-=h)
		{
			g2.drawLine(x, tempY, x, tempY-h);
			g2.drawLine(x+w, tempY, x+w, tempY-h);
		}
	}
	public void drawDownwards(Graphics2D g2)
	{
		for (int tempY=y+h; tempY<HEIGHT; tempY+=h)
		{
			g2.drawLine(x, tempY, x, tempY+h);
			g2.drawLine(x+w, tempY, x+w, tempY+h);
		}
	}
	public void drawRightwards(Graphics2D g2)
	{
		for (int tempX=x+w; tempX<WIDTH; tempX+=w)
		{
			g2.drawLine(tempX, y, tempX+w, y);
			g2.drawLine(tempX, y+h, tempX+w, y+h);
		}
	}
	public void drawLeftwards(Graphics2D g2)
	{
		for (int tempX=x; tempX>0; tempX-=w)
		{
			g2.drawLine(tempX, y, tempX-w, y);
			g2.drawLine(tempX, y+h, tempX-w, y+h);
		}
	}
	

}
